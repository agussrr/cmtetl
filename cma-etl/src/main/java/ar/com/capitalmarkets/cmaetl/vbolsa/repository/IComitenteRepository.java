package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Comitente;

@Repository
public interface IComitenteRepository extends JpaRepository<Comitente, Integer>{
	
//	@EntityGraph(attributePaths = {"auditoria"})//,"usuario","accion"})
	List<Comitente> findByFechaIngresoLessThanAndEstaAnulado(Date fechaIngreso,Boolean estaAnulado);
}
