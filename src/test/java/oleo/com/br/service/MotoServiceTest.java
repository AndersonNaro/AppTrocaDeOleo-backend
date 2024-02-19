package oleo.com.br.service;

import oleo.com.br.builders.OleoBuilder;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.entity.OleoEntity;
import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.repository.MotoRepository;
import oleo.com.br.repository.OleoRepository;
import oleo.com.br.repository.ProprietarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static oleo.com.br.converter.MotoConverter.toDto;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MotoServiceTest {

    @Autowired
    MotoRepository repository;

    @Autowired
    MotoService service;

    @Autowired
    OleoRepository oleoRepository;

    @Autowired
    OleoService oleoService;

    private OleoDto oleo ;

    private MotoDto moto;

    @BeforeEach
    void setup() {

        service = new MotoService(repository);


        oleo = oleoBuilder().build();
        moto = motoBuilder().build();

    }

    @Test
    void criarMoto() {
        //cenário

        //ação
        MotoDto resultado = service.createMoto(moto);

        //Verificação
        Assertions.assertEquals("Lander", resultado.getNome() );

    }

    @Test
    void buscarMotoPorId() {
        //cenário
        MotoDto target = service.createMoto(motoBuilder().build());

        //ação
        MotoDto resultado = service.findMotoById(target.getId());

        //Verificação
        Assertions.assertEquals("XYZ1980", resultado.getPlaca());
        Assertions.assertEquals("XTZ250", resultado.getModelo());
        Assertions.assertEquals("Lander", resultado.getNome());
    }

    @Test
    void buscarMotoNaoExistente() {
        //Cenario
        long idNaoExistente = repository.count() + 2;

        //Ação
        MotoDto resultado = service.findMotoById(idNaoExistente);

        //Verificação
        Assertions.assertNull(resultado);
    }
    @Test
    void alterarMoto() {
        //cenario
        MotoDto target = service.createMoto(moto);
        target.setNome("RD");
        target.setModelo("RD 350 LC");
        target.setPlaca("YVN1988");

        //Ação
        service.updateMoto(target);
        MotoDto resultado = service.findMotoById(target.getId());

        //Verificação
        Assertions.assertEquals("RD", resultado.getNome());
        Assertions.assertEquals("RD 350 LC", resultado.getModelo());
        Assertions.assertEquals("YVN1988", resultado.getPlaca());
        Assertions.assertEquals(target.getId(), resultado.getId());
    }

    @Test
    void deletaMoto() {
        //cenario
        MotoDto target = service.createMoto(moto);
        Long id = target.getId();
        Long initialRownCount = repository.count();

        //Ação
        Long deletedRows = service.deleteMoto(target);
        Long finalRowCount = repository.count();

        //Verificação
        assertTrue(finalRowCount == initialRownCount - deletedRows);
    }




}
