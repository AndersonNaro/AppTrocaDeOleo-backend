package oleo.com.br.service;

import lombok.RequiredArgsConstructor;
import oleo.com.br.converter.MotoConverter;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.ProprietarioNaoEcontradoException;
import oleo.com.br.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static oleo.com.br.converter.ProprietarioConverter.toDto;
import static oleo.com.br.converter.ProprietarioConverter.toEntity;

@Service
@RequiredArgsConstructor
public class ProprietarioService {

    private final ProprietarioRepository repository;

    @Autowired
    private MotoService motoService;

    public ProprietarioDto getProprietarioById(long id)  {
        try {
            Optional<ProprietarioEntity> optional = repository.findById(id);
            if (optional.isEmpty())
                throw new ProprietarioNaoEcontradoException(id);
            return toDto(optional.get());
        } catch (ProprietarioNaoEcontradoException ex) {
            return null;
        }
    }

    public ProprietarioDto updateProprietario(ProprietarioDto proprietario) {
        return toDto(repository.save(toEntity(proprietario)));
    }

    public ProprietarioDto createProprietario(ProprietarioDto proprietario) {
        return toDto(repository.save(toEntity(proprietario)));
    }

    public Long deleteProprietario(ProprietarioDto proprietario) {

        long initialRownCount = repository.count();
        repository.delete(toEntity(proprietario));
        long finalRownCount = repository.count();

        return initialRownCount - finalRownCount;
    }

}
