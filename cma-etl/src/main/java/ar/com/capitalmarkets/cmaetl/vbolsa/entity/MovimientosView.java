package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="vwTituOperaciones")
@Immutable
public class MovimientosView {
	
	@Embeddable
	@Data
	public static class CompositePk implements Serializable {
		@Column(name="Comitente") private Integer numComitente;
		@Column(name="Fecha") private Date fecha;
		@Column(name="Cuenum") private Integer cuenum;
		
	}
	@EmbeddedId private CompositePk id;
	@Column(name="Operacion") private String operacion;
	@Column(name="CodigoOperacion") private String codigoOperacion;
	@Column(name="DescOperacion") private String descOperacion;
	@Column(name="Total") private Double total;
	@Column(name="EspecieCodigo") private Integer especieCodigo;
	@Column(name="MercadoCodigo") private Integer mercadoCodigo;
	@Column(name="Cantidad") private Double cantidad;
	@Column(name="PrimLote") private Integer primLote;
	@Column(name="Moneda") private String moneda;
	@Column(name="EspecieTipo") private String especieTipo;
	@Column(name="Especie") private String especie;
	@Column(name="PrimaFecha") private Date primaFecha;
	@Column(name="PrimaEspecie") private String primaEspecie;
	@Column(name="Precio") private Double precio;
	@Column(name="EspecieMoneda") private String especieMoneda;
}
