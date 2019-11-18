package ar.com.capitalmarkets.cmaetl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.AuditoriaHist;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Comitente;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Documentacion;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.MovimientosView;
import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Tenencia;

public class ComitenteDetalle {
	
	private Comitente comitente;
	private List<AuditoriaHist> auditoria;
	private Valuacion valuacion;
	private List<Tenencia> tenencias;
	private HashMap<Integer, Documentacion> documentacion;
	private List<MovimientosView> movimientos;
	private Date fecha;
	private Boolean esPorConcertacion;
	
	public ComitenteDetalle(Comitente c) {
		this.comitente=c;
		this.auditoria=null; //new ArrayList<>();
		this.valuacion=null;
		this.tenencias=null; //new ArrayList<>();
		this.movimientos=null;
	}
	private ComitenteDetalle() {}
	
	public Comitente getComitente() {
		return this.comitente;
	}
	public Valuacion getValuacion() {
		return this.valuacion;
	}
	private void setTenencias(List<Tenencia> t) {
		if (t!=null) {
			if (this.tenencias!=null) this.tenencias=null;
			this.tenencias=t;
		}
	}
	public void setMovimientos(List<MovimientosView> m) {
		if (m!=null) {
			if (this.movimientos!=null) this.movimientos=null;
			this.movimientos=m;
		}
	}
	private void setValuacion(Valuacion v) {
		if (v!=null) {
			if (this.valuacion!=null) this.valuacion=null;
			this.valuacion=v;
		}
	}
	private void addTenencia(Tenencia t) {
		if (t!=null) {
			if (this.tenencias==null) setTenencias(new ArrayList<>());
			tenencias.add(t);
		}
	}
	public void addDocumentacion(List<Documentacion> d) {
		if (d==null) { 
			System.out.println("CHABON!!! DOC LISTA VACIA - COM:"+this.comitente.getCodComitente());
			return;
		} else {
			if (this.documentacion==null) this.documentacion=new HashMap<Integer,Documentacion>();
			d.parallelStream().forEach((d1)->{
				this.documentacion.putIfAbsent(d1.getId().getCodDocumentacion(), d1);
			});
		}
	}
	public Boolean documentacionPresentada(Integer id) {
		if (this.documentacion.containsKey(id)) return this.documentacion.get(id).getFuePresentado();
		else return false;
	}
	public void calcularValuacion (Date fecha, Boolean esPorConcertacion, List<Tenencia> t) {
		if (t==null) return;
		this.fecha=fecha;
		this.esPorConcertacion=esPorConcertacion;
		setValuacion(new Valuacion(this.comitente.getNumComitente(), "Pesos", esPorConcertacion, fecha));
		t.parallelStream().filter((t1)-> t1.getNumComitente().equals(comitente.getNumComitente()))
				.forEach(this::addTenencia);
		if (this.tenencias!=null) {
			this.tenencias.parallelStream().forEach(this::calcularDetalleValuacion);
		}
	}
	
	private void calcularDetalleValuacion(Tenencia tenencia) {
		try {
			var moneda = (tenencia.getMonedaEmision()!=null) ? tenencia.getMonedaEmision():"Sin moneda";
			var tipoCambio = (tenencia.getTipoDeCambio()!=null) ? tenencia.getTipoDeCambio() : 0.0;
			var valAcum = (valuacion.getValuacion()!=null) ? valuacion.getValuacion() : 0.0;
			var montoPaisApl= (tenencia.getMontoMonPaisApl()!=null) ? tenencia.getMontoMonPaisApl() : 0.0;

			valuacion.setValuacion(valAcum+montoPaisApl);

			var detalleValuacion = valuacion.getDetalleValuacion();
			detalleValuacion.putIfAbsent(moneda, new DetalleValuacion());

			var detalleValuacionXMoneda = detalleValuacion.get(moneda);
			var valAcumXMon = (detalleValuacionXMoneda.getValuacion()!=null) ? detalleValuacionXMoneda.getValuacion():0.0;
			var montoMonOrigen = (tenencia.getMontoMonOrigen()!=null) ? tenencia.getMontoMonOrigen():0.0;

			detalleValuacionXMoneda.setValuacion(valAcumXMon+montoMonOrigen);
			detalleValuacionXMoneda.setTipoCambio(tipoCambio);

			detalleValuacion.put(moneda,detalleValuacionXMoneda);
		} catch (Exception e) {
			System.out.println("Error calculando Valuacion en tenencia: "+tenencia);
			System.out.println("Error: "+e.getMessage());
		}
	}
}
