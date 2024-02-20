package oleo.com.br.converter;

import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.ProprietarioEntity;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static oleo.com.br.converter.ProprietarioConverter.toDto;
import static oleo.com.br.converter.ProprietarioConverter.toEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProprietarioConverterTest {

    private ProprietarioEntity entity;
    private ProprietarioDto dto;

    @BeforeEach
    void setup() {
        dto = proprietarioBuilder().build();
        entity = new ProprietarioEntity(1L,"Nome", "senha", "email@email.com", null);
    }

    @Test
    void deveRetornarDto() {
        ProprietarioDto resultado = toDto(entity);
        assertTrue(resultado instanceof ProprietarioDto);
    }

    @Test
    void deveRetornarEntity() {
        ProprietarioEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof ProprietarioEntity);
    }
}
