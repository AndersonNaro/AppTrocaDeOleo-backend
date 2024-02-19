package oleo.com.br.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.service.MotoService;
import oleo.com.br.service.ProprietarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
public class MotoControllerTest {

    @Autowired
    MotoController motoController;

    @MockBean
    MotoService motoService;

    @MockBean
    ProprietarioService proprietarioService;

    private MotoDto moto;

    @BeforeEach
    public void setup() {

        standaloneSetup(this.motoController);
        moto = motoBuilder().setId(1L).build();
    }


    @Test
    void deveRetornarSucesso_QuandoBuscarMotoPorId() {

        ProprietarioDto proprietario = proprietarioBuilder().setMotos(Arrays.asList(moto)).build();

        when(this.proprietarioService.createProprietario(proprietario)).thenReturn(proprietario);
        when(this.motoService.findMotoById(1L)).thenReturn(moto);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/moto/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(
                        "modelo" , equalTo("XTZ250")
                    );
    }

    @Test
    void deveRetornarNotFound_QuandoRequisitarMotoInexistente() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/moto/{id}", "1")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveRetornarSucesso_QuandoCriarUmaMoto() throws JsonProcessingException {

        ProprietarioDto proprietario = proprietarioBuilder().setMotos(Arrays.asList(moto)).build();

        when(this.motoService.createMoto(any(MotoDto.class))).thenReturn(moto);
        when(this.proprietarioService.createProprietario(proprietario)).thenReturn(proprietario);


        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(moto));

        given()
                .contentType(ContentType.JSON)
                .body(moto)
                .when()
                .post("/moto/create")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(
                        "modelo" , equalTo("XTZ250")
                );

    }

    @Test
    void deveRetornarSuccessENoBodyAQuantidadeDeletadaIgual1_QuandoDeletarMoto() {
        when(this.motoService.deleteMoto(moto)).thenReturn(1L);

        given()
                .contentType(ContentType.JSON)
                .body(moto)
                .when()
                .delete("/moto/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(equalTo(String.valueOf(1))
                );
    }

    @Test
    void deveRetornarSuccessENoBodyAQuantidadeDeletadaIgualA0_QuandoNaoEncontrarAMotoParaDeletar() {
        when(this.motoService.deleteMoto(moto)).thenReturn(0L);

        given()
                .contentType(ContentType.JSON)
                .body(moto)
                .when()
                .delete("/moto/delete")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(equalTo(String.valueOf(0))
                );
    }

    @Test
    void deveRetornarSuccess_QuandoAtualizarMoto() {

        given()
                .contentType(ContentType.JSON)
                .body(moto)
                .when()
                .patch("/moto/update")
                .then()
                .statusCode(HttpStatus.OK.value());

    }


}
