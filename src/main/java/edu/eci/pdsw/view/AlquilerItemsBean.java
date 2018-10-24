package edu.eci.pdsw.view;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean (name ="alquilerBean")
public class AlquilerItemsBean extends BasePageBean{
	
	@Inject
	private ServiciosAlquiler sa;
	
	private long idcl;
	
	public void setIdcl(long id) {
		idcl=id;
	}
	public long getIdcl() {
		return idcl;
	}
	
	public List<Cliente> getClientes() throws Exception {
		try{
			return sa.consultarClientes();
		}catch(ExcepcionServiciosAlquiler e) {
			throw e;
		}
	}
	public void insertarCliente(String nombre,long documento,String telefono,String direccion,String email) throws Exception{
		try {
			sa.registrarCliente(new Cliente(nombre,documento,telefono,direccion,email));
		} catch (ExcepcionServiciosAlquiler e) {
			throw e;
		}
		
	}

	public List<ItemRentado> getItems() throws Exception{
		try {
			List<ItemRentado> rentados=sa.consultarItemsCliente(idcl);
			List<ItemRentado> NoDevueltos= new ArrayList<ItemRentado>();
			Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			for(ItemRentado ir: rentados) {
				if(ir.getFechafinrenta().getTime()<currentDate.getTime()) {
					NoDevueltos.add(ir);
				}
			}
			return NoDevueltos;
			
		} catch (ExcepcionServiciosAlquiler e) {
			throw e;
		}
	}
	
	public void registrarItem(int item,int dias) throws Exception{
		LocalDate localDate = LocalDate.now();
		try {
			Item it=sa.consultarItem(item);
			sa.registrarAlquilerCliente(Date.valueOf(localDate), idcl, it, dias);;
			
		} catch (ExcepcionServiciosAlquiler e) {
			throw e;
		}
	}

	public long consultarMulta(int iditem) throws Exception{
		try {
			LocalDate localDate = LocalDate.now();
			return sa.consultarMultaAlquiler(iditem, Date.valueOf(localDate));
		} catch (ExcepcionServiciosAlquiler e) {
			throw e;
		}
	}
}
