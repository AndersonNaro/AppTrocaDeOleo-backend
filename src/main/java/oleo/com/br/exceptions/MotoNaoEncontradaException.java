package oleo.com.br.exceptions;

public class MotoNaoEncontradaException extends Exception{
    public MotoNaoEncontradaException(Long id) {
        super(String.format("Não foi encontrado no banco de dados a Moto com Id: %s", Long.toString(id)));
    }
}
