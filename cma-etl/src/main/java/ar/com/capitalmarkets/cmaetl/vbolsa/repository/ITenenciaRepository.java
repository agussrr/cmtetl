package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Tenencia;

@Repository
public interface ITenenciaRepository {
	
	List<Tenencia> findTenenciasByNumComitente(@Param("numComitente") Integer numComitente, @Param("FechaHasta") Date fechaHasta, 
			@Param("esPorConcertacion") Boolean esPorConcertacion); //, @Param("Status") String status);	
}