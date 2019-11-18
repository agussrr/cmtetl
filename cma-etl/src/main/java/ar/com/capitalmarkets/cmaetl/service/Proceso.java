package ar.com.capitalmarkets.cmaetl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.capitalmarkets.cmaetl.model.ComitenteDetalle;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Comitente;
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

//	private Stream<MovimientosView> movimientos;
	private List<ComitenteDetalle> comitentesFinal=new ArrayList<>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	@Autowired
	public Proceso(IComitenteRepository cr, ITenenciaRepository tr, IDocumentacionRepository dr,
			IMovimientosViewRepository mvr) {
		this.comitenteRepository=cr;
		this.documentacionRepository=dr;
		this.tenenciaRepository=tr;
		this.movimientosViewRepository=mvr;
	}
	
	public void procesarComitentes() {
//		Stream<MovimientosView> movimientos;
		var start=System.currentTimeMillis();
		logger.info("Inicio:"+start);

		try {
			var comitentesInicio = comitenteRepository.findByFechaIngresoLessThanAndEstaAnulado(sdf.parse("2015/07/01"),false);
			var tenencias = tenenciaRepository.findTenenciasByNumComitente(null,sdf.parse("2015/07/01"),false);
			comitentesInicio.parallelStream().forEach(c->movimientosFutureDone(c,tenencias.parallelStream().filter(t1->t1.getNumComitente().equals(c.getNumComitente())).collect(Collectors.toList())));
//			movimientos=movimientosViewRepository.findByIdFechaAfter(sdf.parse("2019/04/01")).parallelStream().collect(Collectors.toList());
//			movimientosFuture=movimientosViewRepository.findByIdFechaAfter(sdf.parse("2019/06/01"));
//			movimientosFuture.addCallback(this::movimientosFutureDone, this::movimientosFutureFailed);
//			movimientos=movimientosViewRepository.findByIdFechaGreaterThanEqual(sdf.parse("2019/06/01")).parallel();
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		
//		movimientos.close();
//		tenenciasComitentes.clear();
//		tenencias.clear();
		var finish=System.currentTimeMillis();
		var delta=finish-start;
		logger.info("Fin:"+finish);
		logger.info("Tiempo de proceso: "+(delta));
//		tenenciasComitentes=null;
	}
	
	private void movimientosFutureDone(Comitente c, List<Tenencia> t) {
		logger.info("Llamo al callback onSuccess MovFutures");
		var comitenteDetalle = new ComitenteDetalle(c);
		try {
			var d=documentacionRepository.findAllByCodComitente(c.getCodComitente());
			if (d!=null) comitenteDetalle.addDocumentacion(d);
			if (!comitenteDetalle.documentacionPresentada(13)) {
				
				comitenteDetalle.calcularValuacion(sdf.parse("2015/07/01"), false,t);
				
				if(comitenteDetalle.getValuacion().getValuacion()<=(100*60)) {
					var movimientos=movimientosViewRepository.findByIdNumComitenteAndIdFechaGreaterThan(c.getNumComitente(),sdf.parse("2019/06/01"));

					comitenteDetalle.setMovimientos(movimientos); //.filter((m)->m.getId().getNumComitente().equals(c.getNumComitente())).collect(Collectors.toList()));
					comitentesFinal.add(comitenteDetalle);
					//hacer un save async
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR PROC COM: "+c.getCodComitente());
			e.printStackTrace();
		}
	}
	private void movimientosFutureFailed(Throwable ex) {
		logger.info("Llamo al callback onFailure MovFutures");
		logger.info("Error "+ex.getMessage());
		
	}
}
