package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClienteMapper {
    public void insertarCliente(@Param("ct")Cliente ct);
	public Cliente consultarCliente(@Param("idcli") int id);
	public Cliente consultarCliente(@Param("idcli") long id);
    public void agregarItemRentadoACliente(@Param("idc") long idc, 
    		@Param("idi") int idi, 
    		@Param("fini") Date fechainicio,
    		@Param("ffin") Date fechafin);
    public List<Cliente> consultarClientes();
	public void vetar(@Param("doc") long docu, @Param("estado") int estado);
	public Cliente consultarItemsCliente(@Param("doc") long idcliente);
}