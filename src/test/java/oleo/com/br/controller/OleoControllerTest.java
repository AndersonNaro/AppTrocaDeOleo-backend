package oleo.com.br.controller;

import io.restassured.http.ContentType;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.service.MotoService;
import oleo.com.br.service.OleoService;
import oleo.com.br.service.ProprietarioService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@WebMvcTest
public class OleoControllerTest {

    @Autowired
    OleoController controller;

    @MockBean
    OleoService service;

    @MockBean
    MotoService motoService;

    @MockBean
    ProprietarioService proprietarioService;

    private OleoDto oleo;

    @BeforeEach
    void setup() {
        standaloneSetup(this.controller);
        oleo = oleoBuilder().setId(1L).build();
    }

    @Test
    void deveRetornarSuccess_QuandoBuscarUmOleo() {
        when(service.findOleoById(1L)).thenReturn(oleo);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/oleo/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "km", equalTo("80000"))
                .extract()
                .as(OleoDto.class);
    }

    @Test
    void deveRetornarNotFound_QuandoBuscarUmOleoInexistente() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/oleo/{id}", "1")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void deveRetornarSuccessEOleoComId_QuandoPersistirUmOleo() {
        when(service.createOleo(oleo)).thenReturn(oleo);

        given()
                .contentType(ContentType.JSON)
                .body(oleo)
                .when()
                .post("/oleo/create")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "km", equalTo("80000"))
                .extract()
                .as(OleoDto.class);
    }

    @Test
    void deveRetornarSuccess_QuandoDeletarUmOleo() {
        when(service.deleteOleo(oleo)).thenReturn(1L);

        given()
                .contentType(ContentType.JSON)
                .body(oleo)
                .when()
                .delete("/oleo/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body( equalTo("1"));
    }

    @Test
    void deveRetornarSuccessEZeroNoBody_QuandoNaoEncontrarOleoParaDeletar() {
        when(service.deleteOleo(oleo)).thenReturn(0L);

        given()
                .contentType(ContentType.JSON)
                .body(oleo)
                .when()
                .delete("/oleo/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(equalTo("0"));
    }

    @Test
    void deveRetornarNotFound_QuandoNaoEncontraOOleoAAlterar() {

        given()
                .contentType(ContentType.JSON)
                .body(oleo)
                .when()
                .patch("/oleo/update")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void deveRetornarSuccess_QuandoAlterarOleo() {
        when(service.updateOleo(oleo)).thenReturn(oleo);
        given()
                .contentType(ContentType.JSON)
                .body(oleo)
                .when()
                .patch("/oleo/update")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "km", equalTo("80000"))
                .extract()
                .as(OleoDto.class);

    }
}
