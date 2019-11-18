package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Documentacion;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Documentacion.CompositePk;

@Repository
public interface IDocumentacionRepository extends JpaRepository<Documentacion, CompositePk>{
	
	List<Documentacion> findAllByIdCodComitente(Integer c);
	List<Documentacion> findByIdCodComitenteAndIdCodDocumentacion(Integer c, Integer d);

}