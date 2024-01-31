package oleo.com.br.repository;

import oleo.com.br.entity.OleoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OleoRepository extends JpaRepository<OleoEntity, Long>  {
}
