package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import oleo.com.br.entity.ProprietarioEntity;

import java.util.Collections;
import java.util.List;

public class ProprietarioConverter {

    public static ProprietarioEntity toEntity(ProprietarioDto dto) {
        List<MotoEntity> motosEntity = Collections.emptyList();
        if(dto.getMotos() != null) {
            motosEntity = dto.getMotos().stream()
                    .map(MotoConverter::toEntity)
                    .toList();
        }
        return new ProprietarioEntity(dto.getId(), dto.getNome(), dto.getSenha(), dto.getEmail(), motosEntity);
    }

    public static ProprietarioDto toDto(ProprietarioEntity entity) {
        List<MotoDto> motosDto = Collections.emptyList();
        if(entity.getMotos() != null) {
            motosDto = entity.getMotos().stream()
                    .map(MotoConverter::toDto)
                    .toList();
        }
        return new ProprietarioDto(entity.getId(), entity.getNome(), entity.getSenha(), entity.getEmail(), motosDto);
    }
}
