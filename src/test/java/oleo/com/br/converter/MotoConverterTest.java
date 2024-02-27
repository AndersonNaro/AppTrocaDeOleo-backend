package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.converter.MotoConverter.toDto;
import static oleo.com.br.converter.MotoConverter.toEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MotoConverterTest {

    private MotoEntity entity;
    private MotoDto dto;

    @BeforeEach
    void setup() {
        dto = motoBuilder().build();
        entity = new MotoEntity();
    }

    @Test
    void deveRetornarDtoComListaDeOleoVazio() {
        MotoDto resultado = toDto(entity);
        assertTrue(resultado instanceof MotoDto);
    }

    @Test
    void deveRetornarEntityComListaDeOleoVazio() {
        MotoEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof MotoEntity);
    }

    @Test
    void deveRetornarDtoComOleo() {
        OleoEntity oleo = new OleoEntity(null, null, true, null);
        entity.setOleos(Arrays.asList(oleo));
        MotoDto resultado = toDto(entity);
        assertTrue(resultado instanceof MotoDto);
        assertEquals(resultado.getOleos().get(0), OleoConverter.toDto(oleo));
    }

    @Test
    void deveRetornarEntityComOleo() {
        OleoDto oleo = new OleoDto(null, null, null, true);
        dto.setOleos(Arrays.asList(oleo));
        MotoEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof MotoEntity);
        assertEquals(resultado.getOleos().get(0), OleoConverter.toEntity(oleo));
    }

}
