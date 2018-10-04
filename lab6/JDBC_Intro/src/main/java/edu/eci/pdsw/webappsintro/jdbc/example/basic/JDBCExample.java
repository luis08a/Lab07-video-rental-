/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.webappsintro.jdbc.example.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
        	String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="bdprueba";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2137559;
            //registrarNuevoProducto(con, suCodigoECI, "Michael Preciado", 99999999);
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        //Asignar parámetros
        //usar 'execute'
    	PreparedStatement iProduct=null;
    	String iString="insert into bdprueba.ORD_PRODUCTOS (codigo,nombre,precio) values (?, ?, ?);";
    	try {
			con.setAutoCommit(false);
			iProduct=con.prepareStatement(iString);
			iProduct.setInt(1, codigo);
			iProduct.setString(2, nombre);
			iProduct.setInt(3, precio);
			iProduct.execute();
		} catch (SQLException e ) {
	        System.out.println(e.getMessage());
	        if (con != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                con.rollback();
	            } catch(SQLException excep) {
	            	System.out.println(excep.getMessage());
	            }
	        }
	    } finally {
	        if (iProduct != null) {
	            iProduct.close();
	        }
	        con.setAutoCommit(true);
	    }
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     * @throws SQLException 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla
        List<String> np=new LinkedList<>();
        PreparedStatement productQuery= null;
        String query="select nombre as nn from bdprueba.ORD_PRODUCTOS join bdprueba.ORD_DETALLES_PEDIDO on ? = pedido_fk and producto_fk=codigo;";
        try {
			productQuery =con.prepareStatement(query);
			productQuery.setInt(1, codigoPedido);
			ResultSet rs=productQuery.executeQuery();
			while(rs.next()) {
				np.add(rs.getString("nn"));
				rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     * @throws SQLException 
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException{
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultado del ResultSet
    	PreparedStatement totalQuery=null;
    	String q="select sum(cantidad*precio) as suma from ORD_PRODUCTOS join ORD_DETALLES_PEDIDO on (codigo=producto_fk and ? = pedido_fk) group by pedido_fk;";
    	totalQuery=con.prepareStatement(q);
    	totalQuery.setInt(1, codigoPedido);
    	ResultSet rs=totalQuery.executeQuery();
        rs.first();
        return rs.getInt("suma");
    }
}
