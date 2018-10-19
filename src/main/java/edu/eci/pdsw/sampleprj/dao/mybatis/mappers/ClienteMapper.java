package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClienteMapper {
<<<<<<< HEAD
    public void insertarCliente(@Param("ct")Cliente ct);
	public Cliente consultarCliente(@Param("idcli") int id);
=======
    public void insertarCliente(Cliente ct);
	public Cliente consultarCliente(@Param("idcli") long id);
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
>>>>>>> 64e84d3957c69498c4c2cc150842d30e6e33ea93
    public void agregarItemRentadoACliente(@Param("idc") int idc, 
    		@Param("idi") int idi, 
    		@Param("fini") Date fechainicio,
    		@Param("ffin") Date fechafin);
    public List<Cliente> consultarClientes();
	public void vetar(@Param("doc") long docu, @Param("estado") int estado);
	public Cliente consultarItemsCliente(@Param("doc") long idcliente);
}