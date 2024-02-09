package oleo.com.br.builders;

import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;

import java.util.Date;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;

public class OleoBuilder {

    private final OleoEntity oleo;
    private OleoBuilder() {
        oleo = new OleoEntity(new Date(), true, "80000", motoBuilder().build());
    }

    public static OleoBuilder oleoBuilder () {
        return new OleoBuilder();
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

    public OleoBuilder setMoto( MotoEntity moto) {
        oleo.setMoto(moto);
        return this;
    }

    public OleoEntity build() {
        return oleo;
    }
}
