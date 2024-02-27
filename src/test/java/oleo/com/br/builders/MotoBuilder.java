package oleo.com.br.builders;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;

import java.util.Arrays;
import java.util.List;

import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;

public class MotoBuilder {

    private final MotoDto moto;
    private MotoBuilder() {
        moto = new MotoDto(
                null, "Lander","XTZ250" , "XYZ1980", null);
    }

    public static MotoBuilder motoBuilder() {
        return new MotoBuilder();
    }

    public MotoBuilder setId(Long id) {
        moto.setId(id);
        return this;
    }
    public MotoBuilder setNome(String nome) {
        moto.setNome(nome);
        return this;
    }

    public MotoBuilder setModelo(String modelo) {
        moto.setModelo(modelo);
        return this;
    }

    public MotoBuilder setPlaca(String placa) {
        moto.setNome(placa);
        return this;
    }

    public MotoBuilder setOleos(List<OleoDto> oleos) {
        moto.setOleos(oleos);
        return this;
    }


    public MotoDto build() {
        return moto;
    }
}
