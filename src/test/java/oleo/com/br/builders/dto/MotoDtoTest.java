package oleo.com.br.builders.dto;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MotoDtoTest {

    @Test
    void deveCriarUmaInstancia() {
        MotoDto resultado = new MotoDto(1L, null, null, null, null);
        assertTrue(resultado instanceof MotoDto);
    }

    @Test
    void deveSetarTodosAtributos() {
        MotoDto resultado = new MotoDto(null, null, null, null, null);
        OleoDto oleo = oleoBuilder().build();

        resultado.setId(1L);
        resultado.setNome("Nome");
        resultado.setModelo("modelo");
        resultado.setPlaca("placa");
        resultado.setOleos(Arrays.asList(oleo));

        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getNome(), "Nome");
        assertEquals(resultado.getModelo(), "modelo");
        assertEquals(resultado.getPlaca(), "placa");
        assertEquals(resultado.getOleos().get(0), oleo);
    }
}
