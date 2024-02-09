package oleo.com.br.repository;

import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OleoRepository extends JpaRepository<OleoEntity, Long>  {
   List<OleoEntity> findByMoto(MotoEntity moto);
}
