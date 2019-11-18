package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import lombok.Data;

@Data

@NamedStoredProcedureQueries(
//@NamedNativeQueries(
	@NamedStoredProcedureQuery
//		@NamedNativeQuery(name = "test", query = "CALL TEST()", resultClass = Test.class
		(name = "test", procedureName = "TEST"
		, resultClasses = {Test.class}
				
//								resultSetMappings = "testResultMapping" 
//								parameters = {
//										@StoredProcedureParameter(queryParameter="Descripcion", name="Descripcion",type=Test.class)
//								}
	)
)
//@SqlResultSetMapping (name="testResultMapping",columns = {@ColumnResult(name = "Descripcion")},
//						entities = {@EntityResult(entityClass = ar.com.capitalmarkets.cmaetl.vbolsa.model.Test.class)}
//)
@Entity (name="COMITENTES")
public class Test {
//	@Id private Long id; 
	@Id @Column(name="Descripcion") 
	private String descripcion;
//	private String descComitente;
//	private String grilla;
//	private String disponibilidad;
//	private String comercial;
//	private String tpInstrumento;
//	private String tpEspecie;
//	private Integer codigoInstrumento;
//	private String descInstrumento;
//	private String abrevInstrumento;
//	private String isinCode;
//	private String monedaEmision;
//	private Integer cantidad;
//	private Double cotizacion;
	

}
