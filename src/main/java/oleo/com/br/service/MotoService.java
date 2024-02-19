package oleo.com.br.service;

import oleo.com.br.converter.MotoConverter;
import oleo.com.br.converter.OleoConverter;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.MotoNaoEncontradaException;
import oleo.com.br.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static oleo.com.br.converter.MotoConverter.toDto;
import static oleo.com.br.converter.MotoConverter.toEntity;

@Service
public class MotoService {

    private final MotoRepository repository;

    @Autowired
    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

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
        Long initialRowsCount = repository.count();
        repository.delete(toEntity(moto));
        return (initialRowsCount - repository.count());
    }


}
