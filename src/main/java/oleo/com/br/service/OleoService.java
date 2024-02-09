package oleo.com.br.service;

import lombok.RequiredArgsConstructor;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import oleo.com.br.exceptions.OleoNaoEncontradoException;
import oleo.com.br.repository.OleoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OleoService {

    private OleoRepository repository;

    public OleoService(OleoRepository repository) {
        this.repository = repository;
    }

    public OleoEntity createOleo(OleoEntity oleo) {
        return repository.save(oleo);
    }

    public OleoEntity findOleoById(Long id) {
        Optional<OleoEntity> optional = repository.findById(id);
        try {
            if (optional.isEmpty())
                throw new OleoNaoEncontradoException(id);
            return optional.get();
        } catch(OleoNaoEncontradoException ex) {
            return null;
        }
    }

    public OleoEntity updateOleo(OleoEntity oleo) {
        return repository.save(oleo);
    }

    public Long deleteOleo(OleoEntity oleo) {
        long initialCountRows = repository.count();
        repository.delete(oleo);
        return initialCountRows - repository.count();
    }

    public List<OleoEntity> findListByMoto(MotoEntity moto) {
        return repository.findByMoto(moto);
    }
}
