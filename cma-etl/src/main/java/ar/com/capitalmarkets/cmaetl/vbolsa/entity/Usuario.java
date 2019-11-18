package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table(name="USUARIOS")
public class Usuario {
	@Column(name="CodUsuario") @Id private Integer codUsuario;
//	@OneToMany(mappedBy = "usuario") private List<AuditoriaHist> auditoria;
	@Column(name="UserID") private String userId;
	@Column(name="Apellido") private String apellido;
	@Column(name="Nombre") private String nombre;

}
