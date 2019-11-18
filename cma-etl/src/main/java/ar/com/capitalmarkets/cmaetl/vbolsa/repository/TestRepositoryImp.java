package ar.com.capitalmarkets.cmaetl.vbolsa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import ar.com.capitalmarkets.cmaetl.vbolsa.entity.Test;

//@Repository
@Service
public class TestRepositoryImp implements ITestRepositoryCustom{
//	@Procedure(name="test")
	@PersistenceContext private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAllTest() {
		StoredProcedureQuery sp = this.entityManager.createNamedStoredProcedureQuery("test");
		return sp.getResultList();
	}
}
