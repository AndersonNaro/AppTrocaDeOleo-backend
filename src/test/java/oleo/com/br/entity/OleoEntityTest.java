package oleo.com.br.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OleoEntityTest {

    @Test
    void deveRetornarInstancia_QuandoUsarConstrutorSemArgumentos() {
        OleoEntity resultado = new OleoEntity();
        assertTrue(resultado instanceof OleoEntity);
    }

    @Test
    void deveRetornarInstancia_QuandoUsarConstrutorComArgumentos() {
        OleoEntity resultado = new OleoEntity(1L, new Date(), true, "km" );
        assertTrue(resultado instanceof OleoEntity);
    }

    @Test
    void deveSetarTodosAtributos() {
        OleoEntity resultado = new OleoEntity();
        Date data = new Date();
        MotoEntity moto = new MotoEntity();

        resultado.setId(1L);
        resultado.setData(data);
        resultado.setKm("km");
        resultado.setFiltro(true);
        resultado.setMoto(moto);

        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getData(), data);
        assertEquals(resultado.getKm(), "km");
        assertEquals(resultado.getFiltro(), true);
        assertEquals(resultado.getMoto(), moto);
    }
}
