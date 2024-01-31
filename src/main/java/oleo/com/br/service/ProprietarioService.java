package oleo.com.br.service;

import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.ProprietarioNaoEcontradoException;
import oleo.com.br.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProprietarioService {


    private final ProprietarioRepository repository;

    @Autowired
    public ProprietarioService(ProprietarioRepository repository) {
        this.repository = repository;
    }

    public ProprietarioEntity getProprietarioById(long id) throws ProprietarioNaoEcontradoException {
         Optional<ProprietarioEntity> optional = repository.findById(id);
         if (optional.isEmpty())
             throw new ProprietarioNaoEcontradoException(id);
         return optional.get();
    }

    public void updateProprietario(ProprietarioEntity proprietario) {
        repository.save(proprietario);
    }

    public ProprietarioEntity createProprietario(ProprietarioEntity proprietario) {
        return repository.save(proprietario);
    }

    public void deleteProprietario(ProprietarioEntity proprietario) {
        repository.delete(proprietario);
    }

}
