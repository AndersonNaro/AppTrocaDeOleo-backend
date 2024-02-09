package oleo.com.br.service;


import oleo.com.br.builders.MotoBuilder;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.repository.ProprietarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PreoprietarioServiceTest {

    @Autowired
    private ProprietarioService service;

    @Autowired
    private ProprietarioRepository repository;

    ProprietarioEntity proprietario;

    @BeforeEach
    public void setup() {
        ProprietarioService service; service = new ProprietarioService(repository);

        proprietario = service.createProprietario(proprietarioBuilder()
                .setNome("James Web")
                .setSenha("111222")
                .setEmail("usuario2@email.com")
                .build());
    }

    @AfterEach
    public void clean() {
        ProprietarioEntity proprietario = proprietarioBuilder()
                .setNome("James Web")
                .setSenha("111222")
                .setEmail("usuario2@email.com")
                .build();
        proprietario.setId(1L);
        service.updateProprietario(proprietario);
    }

    @Test
    void buscarProprietarioPeloId() {
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
    void buscarProprietarioNaoExistente() {
        //cenario
        Long idInexistente = repository.count() + 2;

        //Ação
        ProprietarioEntity proprietario = service.getProprietarioById(idInexistente);

        //Verificação
        Assertions.assertNull(proprietario);
    }

    @Test
    void alterarSenhadoUsuario() {
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
        ProprietarioEntity proprietario = proprietarioBuilder()
                .setNome("Thomas Edson")
                .setSenha("1234")
                .setEmail("thomas@email.com")
                .build();

        //ação
        ProprietarioEntity proprietarioCriado = service.createProprietario(proprietario);

        //Verificação
        Assertions.assertEquals(proprietarioCriado, proprietario);
    }

    @Test
    void deletarProprietario()  {
        //cenario
        ProprietarioEntity proprietario = proprietarioBuilder().build();
        service.createProprietario(proprietario);
        Long initialRownCount = repository.count();

        //ação
        Long deletedRows = service.deleteProprietario(proprietario);
        Long id = proprietario.getId();
        Long finalRownCount = repository.count();

        //verificação
        Assertions.assertTrue( finalRownCount == initialRownCount - deletedRows);
    }

    @Test
    void pegarMotosDeUmProprietario() {
        //cenario
        MotoEntity moto = motoBuilder().build();
        proprietario.setMotos(Arrays.asList(moto));

        //Ação
        MotoEntity resultado = proprietario.getMotos().get(0);

        //Verificação
        assertNotNull(resultado);
    }

}
