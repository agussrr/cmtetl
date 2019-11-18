package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable
//@Entity
public class FooKey implements Serializable {
//	@Id @Column(name = "ROWID") private Integer rowId;
	private Integer numComitente;
	private String descComitente;
	private String grilla;
	private String disponibilidad;
	private Integer comercial;
	private String tpInstrumento;
	private String tpEspecie;
	private Integer codigoInstrumento;
	private String descInstrumento;
	private String abrevInstrumento;
	private String isinCode;
	private String monedaEmision;
	private Double cantidad;
	private Double cotizacion;
	private String monedaCotizacion;
	private Double tipoDeCambio;
	private Double montoMonOrigen;
	private Double montoMonPaisApl;
	private String tipoInstrumento;	
}
