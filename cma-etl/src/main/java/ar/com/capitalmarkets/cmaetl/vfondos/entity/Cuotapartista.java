package ar.com.capitalmarkets.cmaetl.vfondos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table (name="CUOTAPARTISTAS") //,schema = "VFondosTest")
public class Cuotapartista {
	
	@Id @Column(name="CodCuotapartista") private Long codCuotapartista;
	@Column(name="Nombre") private String nombre;
	@Column(name="FechaIngreso") private Date fechaIngreso;

}
