package oleo.com.br.exceptions;

public class OleoNaoEncontradoException extends Exception{
    public OleoNaoEncontradoException(Long id) {
        super(String.format("Não foi encontrado no banco de dados a Troca de oleo com Id: %s", Long.toString(id)));
    }
}
