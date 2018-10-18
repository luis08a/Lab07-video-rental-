package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISItemDAO implements ItemDAO{

	@Inject
	private ItemMapper itemMapper;    
	
	@Override
	public void save(Item it) throws PersistenceException{
		try{
			itemMapper.insertarItem(it);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item "+it.toString(),e);
		}        
		
	}
	
	@Override
	public Item load(int id) throws PersistenceException {
		try{
			return itemMapper.consultarItem(id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}

	@Override
	public long tarifaxDia(int itemId) throws PersistenceException {
		try {
			return load(itemId).getTarifaxDia();
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar la tarifa por dia del item "+itemId,e);
		}
	}

	@Override
	public long consultarCostoAlquiler(int idItem, int numdias) throws PersistenceException {
		try {
			return itemMapper.consultarItem(idItem).getTarifaxDia()*numdias;
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el costo de alquilar el item "+idItem+" por "+numdias+"dias",e);
		}
	}

	@Override
	public void setTarifaItem(int id, long tarifa) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> consultarItemsDisponibles() throws PersistenceException {
		try {
			return itemMapper.consultarItems();
		} catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar los items ",e);
		}
	}

	@Override
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws PersistenceException {
		return itemMapper.consultarFechafinReta(iditem);
	}

	@Override
	public void updateTarifaItem(int id, long tarifa) throws PersistenceException {
		try {
			itemMapper.actualizarTarifaItem(id, tarifa);
		} catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al actualizar tarifa a "+tarifa,e);
		}	
	}
}