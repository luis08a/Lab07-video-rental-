package edu.eci.pdsw.view;

import com.google.inject.Inject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

public class AlquilerItemsBean extends BasePageBean{
	private static final long serialVersionUID = 3594009161252782831L;

	@Inject
	private ServiciosAlquiler blogServices;
}
