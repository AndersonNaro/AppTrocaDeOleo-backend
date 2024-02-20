package oleo.com.br.builders.dto;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.ProprietarioDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProprietarioDtoTest {

    @Test
    void deveCriarUmaInstancia_QuandoUsarConstrutorComArgumentos() {
        ProprietarioDto resultado = new ProprietarioDto(1L, "Nome","senha","email", null);
        assertTrue(resultado instanceof ProprietarioDto);
    }

    @Test
    void deveSetarTodosAtributos() {
        ProprietarioDto resultado = new ProprietarioDto(null, null, null, null, null);
        MotoDto moto = motoBuilder().build();

        resultado.setId(1L);
        resultado.setNome("Nome");
        resultado.setSenha("senha");
        resultado.setEmail("email@e.com");
        resultado.setMotos(Arrays.asList(moto));

        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getNome(), "Nome");
        assertEquals(resultado.getSenha(), "senha");
        assertEquals(resultado.getEmail(), "email@e.com");
        assertEquals(resultado.getMotos().get(0), moto);
    }
}
