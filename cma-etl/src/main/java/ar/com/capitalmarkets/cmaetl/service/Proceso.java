package ar.com.capitalmarkets.cmaetl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import ar.com.capitalmarkets.cmaetl.model.ComitenteDetalle;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Comitente;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Tenencia;
import ar.com.capitalmarkets.cmaetl.vbolsa.repository.IComitenteRepository;
import ar.com.capitalmarkets.cmaetl.vbolsa.repository.IDocumentacionRepository;
import ar.com.capitalmarkets.cmaetl.vbolsa.repository.IMovimientosViewRepository;
import ar.com.capitalmarkets.cmaetl.vbolsa.repository.ITenenciaRepository;

@Service
public class Proceso {
	
	private static final Logger logger = LoggerFactory.getLogger(Proceso.class);

	private final IComitenteRepository comitenteRepository;
	private final ITenenciaRepository tenenciaRepository;
	private final IDocumentacionRepository documentacionRepository;
	private final IMovimientosViewRepository movimientosViewRepository;
	
	@Autowired
	public Proceso(IComitenteRepository cr, ITenenciaRepository tr, IDocumentacionRepository dr,
			IMovimientosViewRepository mvr) {
		this.comitenteRepository=cr;
		this.documentacionRepository=dr;
		this.tenenciaRepository=tr;
		this.movimientosViewRepository=mvr;
	}
	
	public void procesarComitentes() {
		var sdf = new SimpleDateFormat("yyyy/MM/dd");

		List<Comitente> tenenciasComitentes;
		List<ComitenteDetalle> comitentes=new ArrayList<>();
		List<Tenencia> tenencias;
		ListenableFuture<List<MovimientosView>> movimientosFuture;
//		Stream<MovimientosView> movimientos;
		Stream<MovimientosView> movimientos;
		
		try {
			tenenciasComitentes = comitenteRepository.findByFechaIngresoLessThanAndEstaAnulado(sdf.parse("2015/07/01"),false);
			tenencias = tenenciaRepository.findTenenciasByNumComitente(2001, sdf.parse("2015/07/01"), false);
//			movimientos=movimientosViewRepository.findByIdFechaAfter(sdf.parse("2019/04/01")).parallelStream().collect(Collectors.toList());
			movimientosFuture=movimientosViewRepository.findByIdFechaAfter(sdf.parse("2019/06/01"));
			movimientosFuture.
			movimientosFuture.addCallback(new ListenableFutureCallback<List<MovimientosView>>() {

				@Override
				public void onFailure(Throwable ex) {
					logger.info("Llamo al callback onFailure MovFutures");
				}

				@Override
				public void onSuccess(List<MovimientosView> result) {
					logger.info("Llamo al callback onSuccess MovFutures");
//					this.movimientos=movimientosFuture.get().parallelStream();//.collect(Collectors.toList());
				}
			});
			
			movimientos=movimientosViewRepository.findByIdFechaGreaterThanEqual(sdf.parse("2019/06/01")).parallel();
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		
		var start=System.currentTimeMillis();
		logger.info("Inicio:"+start);
		
		tenenciasComitentes.stream().forEach((c)->{
			var comitenteDetalle = new ComitenteDetalle(c);
			try {
				comitenteDetalle.calcularValuacion(sdf.parse("2015/07/01"), false,tenencias);
				if(comitenteDetalle.getValuacion().getValuacion()<=(100*60)) {
					var d=documentacionRepository.findAllByIdCodComitente(comitenteDetalle.getComitente().getCodComitente());
					if (d!=null) comitenteDetalle.addDocumentacion(d);
					else System.out.println("ERROR: DOC VACIA COM:"+c.getCodComitente());
					if (!comitenteDetalle.documentacionPresentada(13)) {
						
						comitenteDetalle.setMovimientos(movimientos.filter((m)->m.getId().getNumComitente().equals(comitenteDetalle.getComitente().getNumComitente()))
								.collect(Collectors.toList()));
						
						comitentes.add(comitenteDetalle);
						System.out.println("DOC NO PRESENTADA ID13-COM:"+comitenteDetalle.getComitente().getCodComitente());
					}
					else {
						System.out.println("DOC PRESENTADA ID13 - COM"+comitenteDetalle.getComitente().getCodComitente());
					}
						
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("ERROR PROC COM: "+c.getCodComitente());
				e.printStackTrace();
			}
			
		});
//		movimientos.close();
//		tenenciasComitentes.clear();
//		tenencias.clear();
		var finish=System.currentTimeMillis();
		logger.info("Fin:"+finish);
		logger.info("Tiempo de proceso: "+(finish-start));
		tenenciasComitentes=null;
	}
}
