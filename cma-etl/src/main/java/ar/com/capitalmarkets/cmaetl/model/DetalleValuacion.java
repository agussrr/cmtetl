package ar.com.capitalmarkets.cmaetl.model;

public class DetalleValuacion {
	
	private Double valuacion;
	private Double tipoCambio;
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
	 * @return the tipoCambio
	 */
	public Double getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	/**
	 * 
	 */
	public DetalleValuacion() {
		this.tipoCambio=0.0;
		this.valuacion=0.0;
	}
	
}
