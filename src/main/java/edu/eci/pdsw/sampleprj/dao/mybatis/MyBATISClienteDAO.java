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
		clienteMapper.insertarCliente(ct);
	}
	
	@Override
	public Cliente load(long id) throws PersistenceException {
		return clienteMapper.consultarCliente((int)id);
	}

	@Override
	public void vetar(long docu, boolean estado) throws PersistenceException {
		int k= estado?1:0;
		clienteMapper.vetar(docu,k);
	}

	@Override
	public void agregarItemRentadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException {
		Date k=new Date(date.getDate()+numdias);
		clienteMapper.agregarItemRentadoACliente((int)docu, item.getId(), date,k);
	}

	@Override
	public List<Cliente> loadAll() throws PersistenceException {
		return clienteMapper.consultarClientes();
	}

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) {
		return clienteMapper.consultarItemsCliente(idcliente).getRentados();
	}

}