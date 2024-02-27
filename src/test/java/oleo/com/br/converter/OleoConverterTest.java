package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static oleo.com.br.converter.OleoConverter.toDto;
import static oleo.com.br.converter.OleoConverter.toEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OleoConverterTest {

    private OleoEntity entity;
    private OleoDto dto;

    @BeforeEach
    void setup() {
        dto = oleoBuilder().build();
        entity = new OleoEntity();
    }

    @Test
    void deveRetornarDto() {
        OleoDto resultado = toDto(entity);
        assertTrue(resultado instanceof OleoDto);
    }

    @Test
    void deveRetornarEntity() {
        OleoEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof OleoEntity);
    }
}
