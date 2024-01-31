package oleo.com.br.service;

import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.ProprietarioNaoEcontradoException;
import oleo.com.br.repository.ProprietarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PreoprietarioServiceTest {

    @Autowired
    private ProprietarioService service;

    @Autowired
    private ProprietarioRepository repository;

    @BeforeEach
    public void setup() {
        ProprietarioService service; service = new ProprietarioService(repository);
        String nome = "James Web";
        String senha = "111222";
        String email = "usuario2@email.com";

        ProprietarioEntity proprietario = new ProprietarioEntity(nome, senha, email);
        service.createProprietario(proprietario);
    }

    @AfterEach
    public void clean() throws ProprietarioNaoEcontradoException {
        String nome = "James Web";
        String senha = "111222";
        String email = "usuario2@email.com";
        ProprietarioEntity proprietario = service.getProprietarioById(1L);
        proprietario.setNome(nome);
        proprietario.setSenha(senha);
        proprietario.setEmail(email);
        service.updateProprietario(proprietario);
    }

    @Test
    void buscarProprietarioPeloId() throws ProprietarioNaoEcontradoException {
        //cenario
        String nome = "James Web";
        String senha = "111222";
        String email = "usuario2@email.com";

        //ação
        ProprietarioEntity proprietario = service.getProprietarioById(1L);

        //Verificação
        Assertions.assertEquals(nome, proprietario.getNome());
        Assertions.assertEquals(senha, proprietario.getSenha());
        Assertions.assertEquals(email, proprietario.getEmail());
    }

    @Test
    void alterarSenhadoUsuario() throws ProprietarioNaoEcontradoException {
        //cenario
        ProprietarioEntity proprietario = service.getProprietarioById(1L);
        String senhaAntiga = proprietario.getSenha();
        String novaSenha = "nova senha";
        proprietario.setSenha(novaSenha);

        //ação
        service.updateProprietario(proprietario);
        ProprietarioEntity proprietarioAtualizado = service.getProprietarioById(1L);

        //Verificação
        Assertions.assertNotEquals(senhaAntiga, novaSenha);
        Assertions.assertEquals(novaSenha, proprietarioAtualizado.getSenha());
    }

    @Test()
    void salvarNovoUsuario() {
        //cenario
        String nome = "Thomas Edson";
        String senha = "1234";
        String email = "thomas@email.com";
        ProprietarioEntity proprietario = new ProprietarioEntity(nome, senha, email);

        //ação
        ProprietarioEntity proprietarioCriado = service.createProprietario(proprietario);

        //Verificação
        Assertions.assertEquals(proprietarioCriado, proprietario);
    }

    @Test()
    void lancarProprietarioNaoEcontradoException()  {
        //verificação
        assertThrows(ProprietarioNaoEcontradoException.class, () -> service.getProprietarioById(99999L));
    }

    @Test
    void deletarProprietario()  {
        //cenario
        ProprietarioEntity proprietario = new ProprietarioEntity("Alberto", "E=mc2",  null);
        service.createProprietario(proprietario);
        //ação
        service.deleteProprietario(proprietario);
        Long id = proprietario.getId();
        //verificação
        assertThrows(ProprietarioNaoEcontradoException.class, () -> service.getProprietarioById(id));
    }

}
