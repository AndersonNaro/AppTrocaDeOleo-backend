package oleo.com.br.exceptions;

public class ProprietarioNaoEcontradoException extends Exception{
    public ProprietarioNaoEcontradoException(Long id) {
        super(String.format("Não foi encontrado no banco de dados o Proprietário com Id: %s", Long.toString(id)));
    }
}
