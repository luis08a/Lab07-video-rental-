package edu.eci.pdsw.view;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.entities.*;

@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean (name ="alquilerBean")
public class AlquilerItemsBean extends BasePageBean{
	
	@Inject
	private ServiciosAlquiler sa;
	
	private int codigoItem;
	private int diasARentar;
	
	private long idcl;

	public void setCodigoItem(int codigo) {
		codigoItem=codigo;
	}
	public void setDiasARentar(int dias) {
		diasARentar=dias;
	}
	public void setIdcl(long id) {
		idcl=id;
	}
	public long getIdcl() {
		return idcl;
	}
	public int getCodigoItem() {
		return codigoItem;
	}
	public int getDiasARentar() {
		return diasARentar;
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
	
	public long costoAlquiler() throws Exception{
		try {
			return sa.consultarCostoAlquiler(codigoItem, diasARentar);
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
	public void registrarItemACliente(){
		
	}
}
