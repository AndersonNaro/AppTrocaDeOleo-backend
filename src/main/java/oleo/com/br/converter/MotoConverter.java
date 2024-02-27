package oleo.com.br.converter;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;

import java.util.Collections;
import java.util.List;

public class MotoConverter {

    public static MotoEntity toEntity(MotoDto dto) {
        List<OleoEntity> oleosEntity = Collections.emptyList();
        if (dto.getOleos() != null) {
            oleosEntity = dto.getOleos().stream()
                    .map(OleoConverter::toEntity)
                    .toList();
        }
        return new MotoEntity(dto.getId(), dto.getNome(), dto.getModelo(), dto.getPlaca(), oleosEntity);
    }

    public static MotoDto toDto(MotoEntity entity) {
        List<OleoDto> oleosDto =  Collections.emptyList();
        if (entity.getOleos() != null) {
            oleosDto = entity.getOleos().stream()
                    .map(OleoConverter::toDto)
                    .toList();
        }
        return new MotoDto(entity.getId(), entity.getNome(), entity.getModelo(), entity.getPlaca(),oleosDto);
    }
}
