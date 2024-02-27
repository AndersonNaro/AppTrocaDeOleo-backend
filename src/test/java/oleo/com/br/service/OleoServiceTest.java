package oleo.com.br.service;

import oleo.com.br.dto.OleoDto;
import oleo.com.br.repository.OleoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static oleo.com.br.converter.OleoConverter.toEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OleoServiceTest {

    @MockBean
    OleoRepository repository;

    @Autowired
    OleoService service;

    private OleoDto oleo;
    private Date data;

    @BeforeEach
    void setup() {
        service = new OleoService(repository);
        data = new Date();
        oleo = oleoBuilder().setDate(data).build();
    }

    @Test
    void criarTrocaDeOleo() {
        when(repository.save(toEntity(oleo))).thenReturn(toEntity(oleo));
        OleoDto resultado = service.createOleo(oleo);

        //Verificação
        assertEquals(data, resultado.getData());
        assertEquals(true, resultado.getFiltro());
    }

    @Test
    void buscarOleoPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(toEntity(oleo)));
        OleoDto resultado =  service.findOleoById(1L);
        assertEquals("80000", resultado.getKm());
        assertEquals(data, resultado.getData());
    }

    @Test
    void buscarOleoNaoExistente() {
        OleoDto resultado =  service.findOleoById(1L);
        assertNull(resultado);
    }

    @Test
    void alterarOleo() {
        when(repository.save(toEntity(oleo))).thenReturn(toEntity(oleo));
        OleoDto resultado = service.updateOleo(oleo);
        assertEquals("80000", resultado.getKm());
    }

    @Test
    void deletarTrocaDeOleo() {
        Long deletedRows = service.deleteOleo(oleo);
        assertEquals(0, deletedRows);
    }

}
