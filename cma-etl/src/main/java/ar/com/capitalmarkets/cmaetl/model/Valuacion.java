package ar.com.capitalmarkets.cmaetl.model;

import java.util.Date;
import java.util.HashMap;

public class Valuacion {
	
	private Double valuacion;
	private String moneda;
	private HashMap<String,DetalleValuacion> detalleValuacion;
	
	public Valuacion(Integer numComitente,String moneda, Boolean esPorConcertacion, Date fecha) {
		this.valuacion = 0.0;
		this.moneda=moneda;
		this.detalleValuacion=new HashMap<String,DetalleValuacion>();
	}
	private Valuacion() {};

	/**
	 * @return the valuacion
	 */
	public Double getValuacion() {
		return valuacion;
	}
	/**
	 * @param valuacion the valuacion to set
	 */
	public void setValuacion(Double valuacion) {
		this.valuacion = valuacion;
	}
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the detalleValuacion
	 */
	public HashMap<String, DetalleValuacion> getDetalleValuacion() {
		return detalleValuacion;
	}
	/**
	 * @param detalleValuacion the detalleValuacion to set
	 */
	public void setDetalleValuacion(HashMap<String, DetalleValuacion> detalleValuacion) {
		this.detalleValuacion = detalleValuacion;
	}
}
