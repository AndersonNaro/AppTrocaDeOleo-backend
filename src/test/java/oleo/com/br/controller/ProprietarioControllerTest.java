package oleo.com.br.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.service.MotoService;
import oleo.com.br.service.OleoService;
import oleo.com.br.service.ProprietarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
class ProprietarioControllerTest {

    @Autowired
    private ProprietarioController proprietarioContoller;


    @MockBean
    private ProprietarioService proprietarioService;

    @MockBean
    private MotoService motoService;

    @MockBean
    private OleoService oleoService;

    @BeforeEach
    public void setup() {

        standaloneSetup(this.proprietarioContoller);
    }

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void deveRetornarSucesso_QuandoBuscarProprietario() {

        when(this.proprietarioService.getProprietarioById(1L)).thenReturn(
                proprietarioBuilder().setId(1L).build());

        given()
                .accept(ContentType.JSON)
            .when()
                .get("/proprietario/{id}", "1")
            .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void deveRetornarNotFound_QuandoNaoEncontrarEndpoint() {

        when(this.proprietarioService.getProprietarioById(1L)).thenReturn(
                proprietarioBuilder().setId(1L).build());

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/proprietario/{id}", "15")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void deveRetornarUmProprietario_QuandoSalvarProprietario() throws JsonProcessingException {

        ProprietarioDto proprietario = proprietarioBuilder().setId(1L).build();

        String json = objectMapper.writeValueAsString(proprietario);

        when(this.proprietarioService.createProprietario(any(ProprietarioDto.class))).thenReturn(proprietario);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/proprietario/create")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1),
                        "nome", equalTo("Pessoa"),
                        "senha", equalTo("123456")
                        );

    }

    @Test
    void deveRetornarStatusOk_QuandoDeletarProprietario() throws JsonProcessingException {

        ProprietarioDto proprietario = proprietarioBuilder().setId(1L).build();
        when(this.proprietarioService.deleteProprietario(any(ProprietarioDto.class))).thenReturn(1L);

        given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(proprietario))
                .when()
                .delete("/proprietario/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(equalTo(String.valueOf(1)));
    }

    @Test
    void deveRetornarZero_QuandoTentarDeletarProprietarioInexistente() throws JsonProcessingException {

        ProprietarioDto proprietario = proprietarioBuilder().setId(1L).build();

        String json = objectMapper.writeValueAsString(proprietario);

        when(this.proprietarioService.deleteProprietario(any(ProprietarioDto.class))).thenReturn(0L);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .delete("/proprietario/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(equalTo(String.valueOf(0)));

    }

    @Test
    void deveRetornarSucesso_QuandoTentarAtualizarUmProprietario() throws JsonProcessingException {

        ProprietarioDto proprietario = proprietarioBuilder().setId(1L).build();

        String json = objectMapper.writeValueAsString(proprietario);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .patch("/proprietario/update")
                .then()
                .statusCode(HttpStatus.OK.value());

    }


}
