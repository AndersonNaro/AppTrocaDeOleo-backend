package oleo.com.br.entity;

import oleo.com.br.dto.MotoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.converter.MotoConverter.toEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProprietarioEntityTest {

    MotoDto motoDto;

    @Test
    void deveRetornarUmProprietarioEntity_QuandoInstanciarPeloConstrutorSemArgumento() {
        ProprietarioEntity resultado = new ProprietarioEntity();
        assertTrue(resultado instanceof ProprietarioEntity);
    }

    @Test
    void deveRetornarUmProprietarioEntity_QuandoInstanciarPeloConstrutorComArgumentos() {
        ProprietarioEntity resultado = new ProprietarioEntity(
                1L, "Nome", "senha", "email@e.com", null);
        assertTrue(resultado instanceof ProprietarioEntity);
    }

    @Test
    void deveSetarTodososAtributos () {
        motoDto = motoBuilder().build();
        ProprietarioEntity resultado = new ProprietarioEntity();

        resultado.setId(1L);
        resultado.setNome("Nome");
        resultado.setEmail("email@e.com");
        resultado.setMotos(Arrays.asList(toEntity(motoDto)));

        assertEquals(resultado.getId() , 1L);
        assertEquals(resultado.getNome() , "Nome" );
        assertEquals(resultado.getEmail() , "email@e.com");
        assertEquals(resultado.getMotos().get(0) , toEntity(motoDto));

    }
}
