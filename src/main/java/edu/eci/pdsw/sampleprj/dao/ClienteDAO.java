package edu.eci.pdsw.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;

public interface ClienteDAO {

   public void save(Cliente c) throws PersistenceException;

   public Cliente load(long id) throws PersistenceException;

   public void vetar(long docu, boolean estado) throws PersistenceException;

   public void agregarItemRentadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException;

   public List<Cliente> loadAll() throws PersistenceException;

   public List<ItemRentado> consultarItemsCliente(long idcliente)throws PersistenceException;

   public List<Item> consultarItemsNoDevueltos(long docu, Date date);

}
