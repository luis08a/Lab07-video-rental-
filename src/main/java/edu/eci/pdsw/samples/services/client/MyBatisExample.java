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
package edu.eci.pdsw.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     * @throws ExcepcionServiciosAlquiler 
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
    	ServiciosAlquiler services = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    	System.out.println(services.consultarMultaAlquiler(101, Date.valueOf("2017-03-21")));
        //SqlSessionFactory sessionfact = getSqlSessionFactory();
        //SqlSession sqlss = sessionfact.openSession();
        //List<Cliente> cc=cm.consultarClientes();
        //for(Cliente c:cc) {
        //	System.out.println(c.toString());
        //}
        //System.out.println("--------------------------");
        //Cliente cliente=cm.consultarCliente(12345);
        //System.out.println(cliente.toString());
        //cm.agregarItemRentadoACliente(idc, idi, fechainicio, fechafin);
        //List<ItemRentado> itsr=cm.consultarItemsCliente(12345).getRentados();
        //for(ItemRentado ir: itsr) {
        //	System.out.println(ir.toString());
        //}
        //Cliente ct=new Cliente("gggg", 666, "31313","momo", "gggg@momo.index");
        //cm.insertarCliente(ct);
        //System.out.println("--------------------------");
        //cm.vetar(12345, estado);
        //cm.agregarItemRentadoACliente(12345, 2137559, new Date(2018,03,15), new Date(2018,03,17));
        /*TipoItem ti=new TipoItem(1,"Video");
        Item i=new Item(ti,777,"El cienpies humano","personas anidadas",new Date(2009,8,30),1000,"DVD","familiar");
        //im.insertarItem(i);
        
        i=im.consultarItem(777);
        System.out.println(i.toString());
        System.out.println("--------------------------");
        List<Item> ii=im.consultarItems();
        for(Item itm:ii) {
        	System.out.println(itm.toString());
        }
        System.out.println("--------------------------");
        */
        //sqlss.commit();
        //sqlss.close();
    }


}
