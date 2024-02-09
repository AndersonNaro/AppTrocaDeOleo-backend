package oleo.com.br.service;

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

    private ProprietarioEntity proprietario ;

    private MotoEntity moto;

    private OleoEntity oleo;


    private Date data;

    @BeforeEach
    void setup() {
        service = new OleoService(repository);
        propService = new ProprietarioService(propRepository);
        motoService = new MotoService(motoRepository);

        proprietario = propService.createProprietario(proprietarioBuilder().build());
        moto = motoService.createMoto(motoBuilder().setProprietario(proprietario).build());

        data = new Date();
        oleo = oleoBuilder().setDate(data).setMoto(moto).build();

    }

    @Test
    void criarTrocaDeOleo() {
        //cenário

        //Ação
        OleoEntity resultado = service.createOleo(oleo);

        //Verificação
        assertEquals(data, resultado.getData());
        assertEquals(true, resultado.getFiltro());
        assertEquals("Pessoa", resultado.getMoto().getProprietario().getNome());

    }

    @Test
    void buscarOleoPorId() {
        //cenario
        OleoEntity target = service.createOleo(oleo);
        Long id = target.getIdOleo();

        //Ação
        OleoEntity resultado =  service.findOleoById(id);

        //Verificação
        assertEquals("80000", resultado.getKm());
        assertEquals(data, resultado.getData());
        assertEquals(moto, resultado.getMoto());
        assertEquals("Pessoa", resultado.getMoto().getProprietario().getNome());
    }

    @Test
    void buscarOleoNaoExistente() {
        //Cenario
        Long idNaoExistente = repository.count() + 2;

        //Ação
        OleoEntity resultado =  service.findOleoById(idNaoExistente);

        //Verificação
        assertNull(resultado);
    }

    @Test
    void alterarOleo() {
        //Cenario
        service.createOleo(oleo);
        MotoEntity moto = motoService.createMoto(
                new MotoEntity("Virago", "VX535", "XYZ1234", proprietario));
        OleoEntity oleo = service.findOleoById(1L);
        oleo.setKm("10000");
        oleo.setMoto(moto);


        //Ação
        OleoEntity resultado = service.updateOleo(oleo);

        //Verificação
        assertEquals("10000", resultado.getKm());
        assertEquals(moto, resultado.getMoto());
    }

    @Test
    void deletarTrocaDeOleo() {
        //Cenario
        OleoEntity target = service.createOleo(oleo);
        long initialCountRowns = repository.count();

        //Ação
        service.deleteOleo(target);

        //Verificação
        Long finalCountRows = repository.count();
        assertNull(service.findOleoById(target.getIdOleo()));
        assertEquals(finalCountRows, initialCountRowns - 1);
    }

    @Test
    void buscarTodasTrocasDeOloeDeUmaMoto() {
        //Cenario
        Date data1 = null;
        Date data2 = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String stringData1 = "10/05/2020";
        String stringData2 = "20/07/2020";
        try {
            data1 = formato.parse(stringData1);
            data2 = formato.parse(stringData2);
        } catch (ParseException ignored) {
        }

        OleoEntity oleo1 = service.createOleo(oleo);
        OleoEntity oleo2 = service.createOleo(oleoBuilder().setDate(data1).setKm("84600").setMoto(moto).build());
        OleoEntity oleo3 = service.createOleo(oleoBuilder().setDate(data2).setKm("84600").setMoto(moto).build());

        //Ação
        List<OleoEntity> trocasDeOleo = service.findListByMoto(moto);

        //Verificação
        long total = trocasDeOleo.size();
        assertEquals(3, total);
        assertTrue(trocasDeOleo.contains(oleo1));
        assertTrue(trocasDeOleo.contains(oleo2));
        assertTrue(trocasDeOleo.contains(oleo3));
    }

    @Test
    void pegarMotoDoOleo() {
        //cenario
        MotoEntity moto = motoBuilder().build();
        MotoEntity motoDefault = oleo.getMoto();
        oleo.setMoto(moto);
        //acão
        MotoEntity resultado = oleo.getMoto();

        //verificação
        assertNotNull(resultado);
        assertNotNull(motoDefault);
    }

}
