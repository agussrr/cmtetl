package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Documentacion;

@Repository
public interface IDocumentacionRepository extends JpaRepository<Documentacion, Integer>{
	
	List<Documentacion> findAllByCodComitente(Integer c);
	List<Documentacion> findByCodComitenteAndCodDocumentacion(Integer c, Integer d);

}