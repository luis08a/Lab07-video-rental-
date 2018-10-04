package edu.eci.pdsw.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{
	public ExcepcionServiciosAlquiler (String msg, Exception e) {
		super(msg, e);
	}
	public ExcepcionServiciosAlquiler (String msg) {
		super(msg);
	}
}
