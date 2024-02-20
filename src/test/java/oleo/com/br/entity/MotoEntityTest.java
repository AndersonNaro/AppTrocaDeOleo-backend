package oleo.com.br.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MotoEntityTest {

    @Test
    void deveRetornarInstancia_QuandoUsarConstrutorSermArgumentos() {
        MotoEntity resultado = new MotoEntity();
        assertTrue(resultado instanceof MotoEntity);
    }

    @Test
    void deveRetornarInstancia_QuandoUsarConstrutorComArgumentos() {
        MotoEntity resultado = new MotoEntity(1L, "Nome", "Modelo", "placa", null);
        assertTrue(resultado instanceof MotoEntity);
    }

    @Test
    void deveSetarTodosOsAtributos() {
        MotoEntity resultado = new MotoEntity();
        ProprietarioEntity proprietario = new ProprietarioEntity();
        OleoEntity oleo = new OleoEntity();

        resultado.setId(1L);
        resultado.setNome("Nome");
        resultado.setModelo("Modelo");
        resultado.setPlaca("placa");
        resultado.setProprietario(proprietario);
        resultado.setOleos(Arrays.asList(oleo));

        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getNome(), "Nome");
        assertEquals(resultado.getModelo(), "Modelo");
        assertEquals(resultado.getPlaca(), "placa");
        assertEquals(resultado.getProprietario(), proprietario);
        assertEquals(resultado.getOleos().get(0), oleo);
    }

}