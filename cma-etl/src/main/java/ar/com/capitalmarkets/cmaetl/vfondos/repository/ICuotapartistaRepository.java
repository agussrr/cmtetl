package ar.com.capitalmarkets.cmaetl.vfondos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.capitalmarkets.cmaetl.vfondos.entity.Cuotapartista;

@Repository
public interface ICuotapartistaRepository extends JpaRepository<Cuotapartista, Long>{

}
