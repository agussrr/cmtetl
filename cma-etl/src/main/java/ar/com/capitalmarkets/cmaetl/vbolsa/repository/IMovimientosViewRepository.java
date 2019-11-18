package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView.CompositePk;

@Repository
public interface IMovimientosViewRepository extends JpaRepository<MovimientosView, CompositePk>{
	@Transactional(readOnly = true)
	List<MovimientosView> findByIdNumComitenteAndIdFechaGreaterThan (Integer numComitente, Date fecha);

	@Async 
	ListenableFuture<List<MovimientosView>> findByIdFechaAfter(Date fecha);
	
//	@Query (value = "SELECT MAX(Fecha),* from vwMovTitulos where Comitente=$1")
//	Stream<MovimientosView> findMaxDateByIdNumComitente(Integer numComitente);
	
	@Transactional(readOnly = true)
	Stream<MovimientosView> findByIdFechaGreaterThanEqual (Date fecha);

}
