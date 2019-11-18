package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name="DOCUMENTACIONCMT")
@SecondaryTable (name="DOCUMENTACION")
public class Documentacion {	
	
	@Embeddable
	@Data
	public static class CompositePk implements Serializable {
		private static final long serialVersionUID = 1L;
		@Column (name="CodComitente",table="DOCUMENTACIONCMT") private Integer codComitente;
		@Column (name="CodDocumentacion",table="DOCUMENTACIONCMT") private Integer codDocumentacion;
	}
	@EmbeddedId private CompositePk id;
	@Column (name="FuePresentado",table="DOCUMENTACIONCMT") private Boolean fuePresentado;
	@Column (name="FechaVencimiento",table="DOCUMENTACIONCMT") private Date fechaVencimiento;
	@Column (name="Descripcion", table="DOCUMENTACION") private String descripcion;
}
