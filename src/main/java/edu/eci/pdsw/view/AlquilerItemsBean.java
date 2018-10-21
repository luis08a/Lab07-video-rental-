package edu.eci.pdsw.view;

import com.google.inject.Inject;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.entities.*;
@SuppressWarnings("deprecation")
@ManagedBean (name ="alquilerItemsBean")
@SessionScoped

public class AlquilerItemsBean extends BasePageBean{
	private static final long serialVersionUID = 3594009161252782831L;
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
