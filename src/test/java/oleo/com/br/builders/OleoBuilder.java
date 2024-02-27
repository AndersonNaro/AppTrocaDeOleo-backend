package oleo.com.br.builders;

import oleo.com.br.dto.OleoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;

import java.util.Date;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;

public class OleoBuilder {

    private final OleoDto oleo;
    private OleoBuilder() {
        oleo = new OleoDto(null, new Date(), "80000",true);
    }

    public static OleoBuilder oleoBuilder () {
        return new OleoBuilder();

    }

    public OleoBuilder setId(Long id) {
        oleo.setId(id);
        return this;
    }

    public OleoBuilder setFiltro(boolean filtro) {
        oleo.setFiltro(filtro);
        return this;
    }

    public OleoBuilder setKm(String km) {
        oleo.setKm(km);
        return this;
    }

    public OleoBuilder setDate(Date date) {
        oleo.setData(date);
        return this;
    }



    public OleoDto build() {
        return oleo;
    }
}
