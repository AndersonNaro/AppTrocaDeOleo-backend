package oleo.com.br.builders;

import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.ProprietarioEntity;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioBuilder {

    private final ProprietarioDto proprietario;

    private ProprietarioBuilder() {
        proprietario = new ProprietarioDto(
                null, "Pessoa", "123456", "email@email.com", new ArrayList<MotoDto>());
    }

    public ProprietarioBuilder setId(Long id) {
        proprietario.setId(id);
        return this;
    }

    public static ProprietarioBuilder proprietarioBuilder() {
        return new ProprietarioBuilder();
    }


    public ProprietarioBuilder setNome(String nome) {
        proprietario.setNome(nome);
        return this;
    }

    public ProprietarioBuilder setSenha(String senha) {
        proprietario.setSenha(senha);
        return this;
    }

    public ProprietarioBuilder setEmail(String email) {
        proprietario.setEmail(email);
        return this;
    }

    public ProprietarioBuilder setMotos(List<MotoDto> motos) {
        proprietario.setMotos(motos);
        return this;
    }

    public ProprietarioDto build() {
        return proprietario;
    }


}
