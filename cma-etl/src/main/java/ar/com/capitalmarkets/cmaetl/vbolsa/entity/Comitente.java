package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity 
@Table (name="COMITENTES")
@Immutable
public class Comitente {

	@Column(name="CodComitente") private Integer codComitente;
	@Id @Column(name="NumComitente") private Integer numComitente;
	@Column(name="Descripcion") private String descripcion;
	@Column(name="FechaIngreso") private Date fechaIngreso;
	@Column(name="FechaBajaCV") private Date fechaBaja;
	@Column(name="EstaAnulado") private Boolean estaAnulado;
	@Column(name="CodAuditoriaRef") private Integer codAuditoriaRef;
//	@Transient private List<AuditoriaHist> auditoria; 
////	@Column(name="CodAuditoriaRef") 
////	@OneToMany(fetch = FetchType.EAGER) @JoinColumn(name="CodAuditoriaRef") //, mappedBy = "comitente") // 
////	private List<AuditoriaHist> auditoria;
//
//	public void obtenerAuditoria(@Autowired IAuditoriaHistRepository auditoriaHIstRepository) {
//		this.setAuditoria(auditoriaHIstRepository.findByCodAuditoriaRef(this.getCodAuditoriaRef()));
//		System.out.println(this.getCodAuditoriaRef());
//	}
}
