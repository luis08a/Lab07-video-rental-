package edu.eci.pdsw.view;

import com.google.inject.Inject;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.entities.*;

@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean (name ="alquilerBean")
public class AlquilerBean extends BasePageBean{
	@Inject
	private ServiciosAlquiler sa;
	
	private int codigoItem;
	private int diasARentar;
	
	public void setCodigoItem(int codigo) {
		codigoItem=codigo;
	}
	public void setDiasARentar(int dias) {
		diasARentar=dias;
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
	public void registrarItemACliente(){
	}
}
