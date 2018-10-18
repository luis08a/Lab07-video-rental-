package edu.eci.pdsw.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Item;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;

   public long tarifaxDia(int itemId) throws PersistenceException;

   public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException;

	public void setTarifaItem(int id, long tarifa) throws PersistenceException;
	
	public List<Item> consultarItemsDisponibles() throws PersistenceException;
	
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws PersistenceException;

	public void updateTarifaItem(int id, long tarifa);

}