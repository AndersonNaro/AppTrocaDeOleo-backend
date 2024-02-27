package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import org.junit.Before;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static oleo.com.br.converter.ProprietarioConverter.toDto;
import static oleo.com.br.converter.ProprietarioConverter.toEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    void deveRetornarDtoComlistaDeMotosVazia() {
        ProprietarioDto resultado = toDto(entity);
        assertTrue(resultado instanceof ProprietarioDto);
        assertThat(resultado.getMotos().isEmpty());
    }

    @Test
    void deveRetornarDtoComMoto() {
        MotoEntity moto = new MotoEntity();
        entity.setMotos(Arrays.asList(moto));
        ProprietarioDto resultado = toDto(entity);
        assertTrue(resultado instanceof ProprietarioDto);
        assertEquals(resultado.getMotos().get(0), MotoConverter.toDto(moto));
    }

    @Test
    void deveRetornarEntityComListaDeMotosVazia() {
        ProprietarioEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof ProprietarioEntity);
        assertThat(resultado.getMotos().isEmpty());
    }

    @Test
    void deveRetornarEntityComMoto() {
        MotoDto moto =  motoBuilder().build();
        dto.setMotos(Arrays.asList(moto));
        ProprietarioEntity resultado = toEntity(dto);
        assertTrue(resultado instanceof ProprietarioEntity);
        assertEquals(resultado.getMotos().get(0), MotoConverter.toEntity(moto));
    }
}
