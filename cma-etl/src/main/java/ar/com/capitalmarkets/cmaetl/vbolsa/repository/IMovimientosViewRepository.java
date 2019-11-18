package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView.CompositePk;

@Repository
public interface IMovimientosViewRepository extends JpaRepository<MovimientosView, CompositePk>{
	
	List<MovimientosView> findByIdNumComitenteAndIdFechaGreaterThan (Integer numComitente, Date fecha);

	@Async CompletableFuture<MovimientosView> findByIdFechaAfter(Date fecha);
	
//	@Query (value = "SELECT MAX(Fecha),* from vwMovTitulos where Comitente=$1")
//	Stream<MovimientosView> findMaxDateByIdNumComitente(Integer numComitente);
	
	Stream<MovimientosView> findByIdFechaGreaterThanEqual (Date fecha);

}
