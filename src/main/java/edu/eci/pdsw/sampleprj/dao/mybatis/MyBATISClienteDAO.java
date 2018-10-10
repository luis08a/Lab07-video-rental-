package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISClienteDAO implements ClienteDAO{
	
	@Inject
	private ClienteMapper clienteMapper;    
	
	@Override
	public void save(Cliente ct) throws PersistenceException{
		try{
		clienteMapper.insertarCliente(ct);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el cliente "+ct.toString(),e);
		}
	}
	
	@Override
	public Cliente load(long id) throws PersistenceException {
		try{
			return clienteMapper.consultarCliente((int)id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}

	@Override
	public void vetar(long docu, boolean estado) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarItemRentadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> loadAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) {
		// TODO Auto-generated method stub
		return null;
	}

}