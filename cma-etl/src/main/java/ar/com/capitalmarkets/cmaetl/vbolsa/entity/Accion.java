//Clase de acciones
package ar.com.capitalmarkets.cmaetl.vbolsa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table(name="ACCIONES")
public class Accion {
	
	@Id @Column(name="CodAccion") private String codAccion; 
	@Column(name="Descripcion") private String descripcion;
	

}
