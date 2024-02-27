package oleo.com.br.service;

import lombok.RequiredArgsConstructor;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import oleo.com.br.exceptions.OleoNaoEncontradoException;
import oleo.com.br.repository.OleoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static oleo.com.br.converter.OleoConverter.toDto;
import static oleo.com.br.converter.OleoConverter.toEntity;

@Service
@RequiredArgsConstructor
public class OleoService {

    private OleoRepository repository;

    public OleoService(OleoRepository repository) {
        this.repository = repository;
    }

    public OleoDto createOleo(OleoDto oleo) {
        return toDto(repository.save(toEntity(oleo)));
    }

    public OleoDto findOleoById(Long id) {
        Optional<OleoEntity> optional = repository.findById(id);
        try {
            if (optional.isEmpty())
                throw new OleoNaoEncontradoException(id);
            return toDto(optional.get());
        } catch(OleoNaoEncontradoException ex) {
            return null;
        }
    }

    public OleoDto updateOleo(OleoDto oleo) {
        return toDto(repository.save(toEntity(oleo)));
    }

    public Long deleteOleo(OleoDto oleo) {
        long initialCountRows = repository.count();
        repository.delete(toEntity(oleo));
        return initialCountRows - repository.count();
    }

}
