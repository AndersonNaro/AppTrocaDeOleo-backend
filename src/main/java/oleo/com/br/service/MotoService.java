package oleo.com.br.service;

import lombok.RequiredArgsConstructor;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.exceptions.MotoNaoEncontradaException;
import oleo.com.br.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static oleo.com.br.converter.MotoConverter.toDto;
import static oleo.com.br.converter.MotoConverter.toEntity;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository repository;
    public MotoDto createMoto(MotoDto moto) {
        MotoEntity motoEntity = toEntity(moto);
        MotoEntity resultado = repository.save(motoEntity);
        return toDto(resultado);
    }

    public MotoDto findMotoById(long id) {
        try {
            Optional<MotoEntity> optional = repository.findById(id);
            if (optional.isEmpty())
                throw new MotoNaoEncontradaException(id);
            return toDto(optional.get());
        } catch(MotoNaoEncontradaException ex) {
            return null;
        }
    }

    public MotoDto updateMoto(MotoDto moto) {
        return toDto(repository.save(toEntity(moto)));
    }

    public Long deleteMoto(MotoDto moto) {
        long initialRowsCount = repository.count();
        repository.delete(toEntity(moto));
        return (initialRowsCount - repository.count());
    }
}
