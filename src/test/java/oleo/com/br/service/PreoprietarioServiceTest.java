package oleo.com.br.service;


import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.repository.ProprietarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static oleo.com.br.converter.ProprietarioConverter.toDto;
import static oleo.com.br.converter.ProprietarioConverter.toEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PreoprietarioServiceTest {

    @Autowired
    ProprietarioService service;

    @MockBean
    ProprietarioRepository repository;

    @BeforeEach
    void setup() {
        service = new ProprietarioService(repository);
    }
    @Test
    void buscarProprietarioPeloId() {
        ProprietarioDto esperado = proprietarioBuilder().setNome("Nome").build();
        when(repository.findById(1L)).thenReturn(Optional.of(toEntity(esperado)));
        ProprietarioDto resultado = service.getProprietarioById(1L);
        assertEquals(resultado.getNome(), esperado.getNome());
    }
    @Test
    void buscarProprietarioNaoExistente() {
        ProprietarioDto resultado = service.getProprietarioById(1L);
        assertNull(resultado);
    }

    @Test
    void alterarSenhadoUsuario() {
        ProprietarioEntity esperado = toEntity(proprietarioBuilder().build());
        when(repository.save(esperado)).thenReturn(esperado);
        ProprietarioDto resultado = service.updateProprietario(toDto(esperado));
        assertEquals(resultado, toDto(esperado));
    }

    @Test()
    void salvarNovoUsuario() {
        ProprietarioDto proprietario = proprietarioBuilder().build();
        when(repository.save(toEntity(proprietario))).thenReturn(toEntity(proprietario));
        ProprietarioDto resultado = service.createProprietario(proprietario);
        assertNotNull(resultado);
    }

    @Test
    void deletarProprietario()  {
        ProprietarioDto proprietario = proprietarioBuilder().build();
        Long deletedRows = service.deleteProprietario(proprietario);
        assertEquals(0, deletedRows);
    }

}
