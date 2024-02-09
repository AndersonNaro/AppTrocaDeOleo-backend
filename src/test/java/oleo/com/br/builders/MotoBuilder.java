package oleo.com.br.builders;

import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;

import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;

public class MotoBuilder {

    private final MotoEntity moto;
    private MotoBuilder() {
        moto = new MotoEntity("Lander","XTZ250" , "XYZ1980", proprietarioBuilder().build());
    }
    public static MotoBuilder motoBuilder() {
        return new MotoBuilder();
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

    public MotoBuilder setProprietario(ProprietarioEntity proprietario){
        moto.setProprietario(proprietario);
        return this;
    }

    public MotoEntity build() {
        return moto;
    }
}
