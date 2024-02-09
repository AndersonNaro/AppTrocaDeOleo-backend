package oleo.com.br.builders;

import oleo.com.br.entity.ProprietarioEntity;

public class ProprietarioBuilder {

    private final ProprietarioEntity proprietario;

    private ProprietarioBuilder() {
        proprietario = new ProprietarioEntity("Pessoa", "123456", "email@email.com");
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

    public ProprietarioEntity build() {
        return proprietario;
    }

}
