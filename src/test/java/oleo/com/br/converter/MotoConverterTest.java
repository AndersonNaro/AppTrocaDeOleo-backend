package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.entity.MotoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.converter.MotoConverter.toDto;
import static oleo.com.br.converter.MotoConverter.toEntity;
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
    void deveRetornarDto() {
        MotoDto resultado = toDto(entity);
        assertTrue(resultado instanceof MotoDto);
    }

    @Test
    void deveRetornarEntity() {
        MotoEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof MotoEntity);
    }

}
