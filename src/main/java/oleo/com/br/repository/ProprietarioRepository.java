package oleo.com.br.repository;

import oleo.com.br.entity.ProprietarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends CrudRepository<ProprietarioEntity, Long> {
}
