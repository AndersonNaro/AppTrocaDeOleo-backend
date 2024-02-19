package oleo.com.br.service;

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
public class ProprietarioService {

    private final ProprietarioRepository repository;

    @Autowired
    private MotoService motoService;

    @Autowired
    public ProprietarioService(ProprietarioRepository repository) {
        this.repository = repository;
    }

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

    public void updateProprietario(ProprietarioDto proprietario) {
        repository.save(toEntity(proprietario));
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
