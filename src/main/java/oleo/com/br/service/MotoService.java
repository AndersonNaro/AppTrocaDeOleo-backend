package oleo.com.br.service;

import lombok.RequiredArgsConstructor;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.MotoNaoEncontradaException;
import oleo.com.br.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoService {

    MotoRepository repository;

    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

    public MotoEntity createMoto(MotoEntity moto) {
        return repository.save(moto);
    }

    public MotoEntity findMotoById(long id) {
        try {
            Optional<MotoEntity> optional = repository.findById(id);
            if (optional.isEmpty())
                throw new MotoNaoEncontradaException(id);
            return optional.get();
        } catch(MotoNaoEncontradaException ex) {
            return null;
        }
    }

    public List<MotoEntity> findListMotosByProprietario(ProprietarioEntity proprietario) {
        return repository.findByProprietario( proprietario);
    }

    public MotoEntity updateMoto(MotoEntity moto) {
        return repository.save(moto);
    }

    public Long deleteMoto(MotoEntity moto) {
        Long initialRowsCount = repository.count();
        repository.delete(moto);
        return (initialRowsCount - repository.count());
    }
}
