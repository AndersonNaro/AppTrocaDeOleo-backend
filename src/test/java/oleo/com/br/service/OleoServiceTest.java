package oleo.com.br.service;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static oleo.com.br.builders.MotoBuilder.motoBuilder;
import static oleo.com.br.builders.OleoBuilder.oleoBuilder;
import static oleo.com.br.builders.ProprietarioBuilder.proprietarioBuilder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OleoServiceTest {

    @Autowired
    OleoRepository repository;

    @Autowired
    OleoService service;

    @Autowired
    MotoRepository motoRepository;

    @Autowired
    MotoService motoService;

    @Autowired
    ProprietarioRepository propRepository;

    @Autowired
    private ProprietarioService propService;

    private ProprietarioDto proprietario ;

    private MotoDto moto;

    private OleoDto oleo;


    private Date data;

    @BeforeEach
    void setup() {
        service = new OleoService(repository);
        propService = new ProprietarioService(propRepository);
        motoService = new MotoService(motoRepository);

        proprietario = propService.createProprietario(proprietarioBuilder().build());

        data = new Date();
        oleo = oleoBuilder().setDate(data).build();
        moto = motoService.createMoto(motoBuilder().setOleos(Arrays.asList(oleo)).build());


    }

    @Test
    void criarTrocaDeOleo() {
        //cenário

        //Ação
        OleoDto resultado = service.createOleo(oleo);

        //Verificação
        assertEquals(data, resultado.getData());
        assertEquals(true, resultado.getFiltro());
    }

    @Test
    void buscarOleoPorId() {
        //cenario
        OleoDto target = service.createOleo(oleo);
        Long id = target.getId();

        //Ação
        OleoDto resultado =  service.findOleoById(id);

        //Verificação
        assertEquals("80000", resultado.getKm());
        assertEquals(data, resultado.getData());
    }

    @Test
    void buscarOleoNaoExistente() {
        //Cenario
        Long idNaoExistente = repository.count() + 2;

        //Ação
        OleoDto resultado =  service.findOleoById(idNaoExistente);

        //Verificação
        assertNull(resultado);
    }

    @Test
    void alterarOleo() {
        //Cenario
        OleoDto oleo = service.createOleo(oleoBuilder().build());
        oleo.setKm("10000");

        //Ação
        OleoDto resultado = service.updateOleo(oleo);

        //Verificação
        assertEquals("10000", resultado.getKm());

    }

    @Test
    void deletarTrocaDeOleo() {
        //Cenario
        OleoDto target = service.createOleo(oleo);
        long initialCountRowns = repository.count();

        //Ação
        service.deleteOleo(target);

        //Verificação
        Long finalCountRows = repository.count();
        assertNull(service.findOleoById(target.getId()));
        assertEquals(finalCountRows, initialCountRowns - 1);
    }

}
