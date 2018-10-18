package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;
   @Inject
   private TipoItemDAO tipoItemDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
	   try {
		return (int)itemDAO.tarifaxDia(itemId);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("Error al cosultar item "+itemId,e);
	}
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
		return clienteDAO.load(docu);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("Error al consultar cliete: "+docu,e);
	}
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
	   try {
		return clienteDAO.consultarItemsCliente(idcliente);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("Error al consultar los items del cliente: "+Long.toString(idcliente),e);
	}
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
		return clienteDAO.loadAll();
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("Error al consultar clientes",e);
	}
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() {
		return itemDAO.consultarItemsDisponibles();
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   try {
		return itemDAO.consultarMultaAlquiler(iditem,fechaDevolucion);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("Error al consultar multa por alquiler de item "+iditem+" con fecha de devolucion "+fechaDevolucion,e);
	}
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
		return tipoItemDAO.load(id);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
	   try {
		return tipoItemDAO.loadAll();
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		clienteDAO.agregarItemRentadoACliente(date,docu,item,numdias);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
	   try {
		clienteDAO.save(c);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
		try {
			return itemDAO.consultarCostoAlquiler(iditem,numdias);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler("");
		}
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
	   try {
		itemDAO.updateTarifaItem(id,tarifa);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
		itemDAO.save(i);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
	  
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
	   try {
		clienteDAO.vetar(docu,estado);
	} catch (PersistenceException e) {
		throw new ExcepcionServiciosAlquiler("");
	}
      
   }
}