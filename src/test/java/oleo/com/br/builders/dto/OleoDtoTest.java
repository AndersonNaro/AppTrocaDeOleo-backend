package oleo.com.br.builders.dto;

import oleo.com.br.dto.OleoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OleoDtoTest {

    @Test
    void deveCriarUmaInstancia() {
        OleoDto resultado = new OleoDto(null, null, null, null);
        assertTrue(resultado instanceof OleoDto);
    }

    @Test
    void deveSetarTodosAtributos() {
        OleoDto resultado = new OleoDto(null, null, null, null);
        Date data = new Date();

        resultado.setId(1L);
        resultado.setData(data);
        resultado.setKm("km");
        resultado.setFiltro(true);

        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getData(), data);
        assertEquals(resultado.getKm(), "km");
        assertEquals(resultado.getFiltro(), true);
    }
}
