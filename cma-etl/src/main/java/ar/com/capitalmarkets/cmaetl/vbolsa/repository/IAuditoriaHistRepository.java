package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.AuditoriaHist;

@Repository
public interface IAuditoriaHistRepository extends JpaRepository<AuditoriaHist, Integer>{
	
	@EntityGraph(attributePaths = {"usuario","accion"})
	List<AuditoriaHist> findByCodAuditoriaRef(Integer codAuditoriaRef);
	
}
