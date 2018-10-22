package edu.eci.pdsw.view;

import com.google.inject.Inject;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
			return sa.consultarItemsCliente(idcl);
			
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
}
