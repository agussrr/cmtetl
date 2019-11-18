package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.Immutable;

import lombok.Data;


@NamedStoredProcedureQueries(
	@NamedStoredProcedureQuery (name = "valuacion", procedureName = "spSITE_CMA_Valuacion3", resultClasses = Tenencia.class 
		,parameters = {
				@StoredProcedureParameter(mode=ParameterMode.IN,name="numComitente",type=Integer.class),
				@StoredProcedureParameter(mode=ParameterMode.IN,name="fechaHasta",type=Date.class),
				@StoredProcedureParameter(mode=ParameterMode.IN,name="esPorConcertacion",type=Boolean.class),
				@StoredProcedureParameter(mode=ParameterMode.INOUT,name="Status",type=String.class)
		}
	)
)
@Entity
@Data
@Immutable
public class Tenencia {
//	@Id @GeneratedValue (strategy=GenerationType.IDENTITY) private FooKey fookey;
//	@Id @Column (name="ROWID",columnDefinition = "ROW_NUMBER() OVER (ORDER by NumComitente)") private Integer rowId;
//	@EmbeddedId private FooKey fookey;
	@Id @Column (name="ROWID") private Integer rowId;
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
