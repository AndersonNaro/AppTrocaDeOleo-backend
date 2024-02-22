package oleo.com.br.service;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.repository.MotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.converter.MotoConverter.toEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MotoServiceTest {

    @MockBean
    MotoRepository repository;

    @Autowired
    MotoService service;

    private MotoDto moto;

    @BeforeEach
    void setup() {
        service = new MotoService(repository);
        moto = motoBuilder().build();
    }

    @Test
    void criarMoto() {
        moto.setNome("Virago");
        when(repository.save(toEntity(moto))).thenReturn(toEntity(moto));
        MotoDto resultado = service.createMoto(moto);
        assertEquals("Virago", resultado.getNome());
    }

    @Test
    void buscarMotoPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(toEntity(moto)));
        MotoDto resultado = service.findMotoById(1L);
        assertEquals("XYZ1980", resultado.getPlaca());
        assertEquals("XTZ250", resultado.getModelo());
        assertEquals("Lander", resultado.getNome());
    }

    @Test
    void buscarMotoNaoExistente() {
        MotoDto resultado = service.findMotoById(1L);
        assertNull(resultado);
    }

    @Test
    void alterarMoto() {
        when(repository.save(toEntity(moto))).thenReturn(toEntity(moto));
        MotoDto resultado = service.updateMoto(moto);
        assertEquals(moto.getNome(), resultado.getNome());
        assertEquals(moto.getModelo(), resultado.getModelo());
        assertEquals(moto.getPlaca(), resultado.getPlaca());
    }

    @Test
    void deletaMoto() {
        Long resultado = service.deleteMoto(moto);
        assertEquals(resultado, 0);
    }

}
