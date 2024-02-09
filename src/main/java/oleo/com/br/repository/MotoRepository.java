package oleo.com.br.repository;

import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity, Long>  {
    List<MotoEntity> findByProprietario(ProprietarioEntity proprietario);
}
