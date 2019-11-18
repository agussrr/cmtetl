package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Tenencia;

@Repository
public class TenenciaRepository implements ITenenciaRepository{
	
	@PersistenceContext private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tenencia> findTenenciasByNumComitente(Integer numComitente, Date fechaHasta,
			Boolean esPorConcertacion) {
		var sp = this.entityManager.createNamedStoredProcedureQuery("valuacion");
//		.registerStoredProcedureParameter("NumComitente", Integer.class, ParameterMode.IN)
//		.registerStoredProcedureParameter("FechaHasta", Date.class, ParameterMode.IN)
//		.registerStoredProcedureParameter("EsPorConcertacion", Boolean.class, ParameterMode.IN)
//		.registerStoredProcedureParameter("Status", String.class, ParameterMode.OUT);
		sp.setParameter("numComitente", numComitente);
		sp.setParameter("fechaHasta", fechaHasta);
		sp.setParameter("esPorConcertacion", esPorConcertacion);
		sp.setParameter("Status", null);
		var result = sp.getResultList();
		return result;
	}
}
