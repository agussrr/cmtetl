package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
@Table(name="AUDITORIASHIST")
public class AuditoriaHist {
	@Id @Column(name="CodAuditoriaHist") private Integer codAuditoriaHist;
	@Column(name="CodAuditoriaRef") private Integer codAuditoriaRef;
	
//	@ManyToOne @JoinColumn(name = "CodAuditoriaRef")  private Comitente comitente;
	
	@OneToOne(fetch = FetchType.EAGER) @JoinColumn (name="CodAccion") @Fetch(FetchMode.JOIN) private Accion accion; // 
	//@OneToOne @JoinColumn (referencedColumnName = "CodUsuario", name="CodUsuario") private Usuario usuario; 
	@OneToOne(fetch = FetchType.EAGER) @JoinColumn(name="CodUsuario") @Fetch(FetchMode.JOIN) private Usuario usuario; // 
	@Column (name="Fecha") private Date fecha;

}
