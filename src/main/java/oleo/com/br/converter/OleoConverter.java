package oleo.com.br.converter;

import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.OleoEntity;

public class OleoConverter {

    public static OleoEntity toEntity(OleoDto dto) {
        return new OleoEntity(dto.getId(),dto.getData(), dto.getFiltro(), dto.getKm());
    }

    public static OleoDto toDto(OleoEntity entity) {
        return new OleoDto(entity.getId(), entity.getData(),  entity.getKm(), entity.getFiltro());
    }

}
