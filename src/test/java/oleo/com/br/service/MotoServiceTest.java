package oleo.com.br.service;

import oleo.com.br.builders.OleoBuilder;
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

    @Autowired
    ProprietarioRepository propRepository;

    @Autowired
    private ProprietarioService propService;

    private ProprietarioEntity proprietario ;

    private MotoEntity moto;

    @BeforeEach
    void setup() {
        service = new MotoService(repository);
        propService = new ProprietarioService(propRepository);

        moto = motoBuilder().build();
        proprietario = propService.createProprietario(moto.getProprietario());
    }

    @Test
    void criarMoto() {
        //cenário

        //ação
        MotoEntity resultado = service.createMoto(moto);

        //Verificação
        Assertions.assertEquals("Lander", resultado.getNome() );
        Assertions.assertEquals(proprietario.getId(), resultado.getProprietario().getId());
        assertNotNull(resultado.getIdMoto());
    }

    @Test
    void buscarMotoPorId() {
        //cenário
        MotoEntity target = service.createMoto(moto);

        //ação
        MotoEntity resultado = service.findMotoById(target.getIdMoto());

        //Verificação
        Assertions.assertEquals("XYZ1980", resultado.getPlaca());
        Assertions.assertEquals("XTZ250", resultado.getModelo());
        Assertions.assertEquals("Lander", resultado.getNome());
        Assertions.assertNotNull(resultado.getIdMoto());
    }

    @Test
    void buscarMotoNaoExistente() {
        //Cenario
        long idNaoExistente = repository.count() + 2;

        //Ação
        MotoEntity resultado = service.findMotoById(idNaoExistente);

        //Verificação
        Assertions.assertNull(resultado);
    }

    @Test
    void buscarTodasMotosDeUmProprietario() {
        //cenario
        MotoEntity moto2 = service.createMoto(motoBuilder()
                .setNome("Virago")
                .setModelo("XV535")
                .setPlaca("ABC1234")
                .setProprietario(proprietario)
                .build());
        MotoEntity moto3 = service.createMoto(motoBuilder()
                .setNome("GS500")
                .setModelo("GS 500")
                .setPlaca("XYZ0099")
                .setProprietario(proprietario)
                .build());

        //Ação
        List<MotoEntity> resultado = service.findListMotosByProprietario(proprietario);

        //Verificação
        assertTrue(resultado.contains(moto2) || resultado.contains(moto3));
    }

    @Test
    void alterarMoto() {
        //cenario
        MotoEntity target = service.createMoto(moto);
        target.setNome("RD");
        target.setModelo("RD 350 LC");
        target.setPlaca("YVN1988");

        //Ação
        MotoEntity resultado = service.updateMoto(target);

        //Verificação
        Assertions.assertEquals("RD", resultado.getNome());
        Assertions.assertEquals("RD 350 LC", resultado.getModelo());
        Assertions.assertEquals("YVN1988", resultado.getPlaca());
        Assertions.assertEquals(target.getIdMoto(), resultado.getIdMoto());
    }

    @Test
    void deletaMoto() {
        //cenario
        MotoEntity target = service.createMoto(moto);
        Long id = target.getIdMoto();
        Long initialRownCount = repository.count();

        //Ação
        Long deletedRows = service.deleteMoto(target);
        Long finalRowCount = repository.count();

        //Verificação
        assertTrue(finalRowCount == initialRownCount - deletedRows);
    }

    @Test
    void pegarProprietarioDaMoto() {
        //cenario
        OleoEntity oleo = oleoBuilder().setMoto(moto).build();
        moto.setOleos(Arrays.asList(oleo));
        MotoEntity target = service.createMoto(moto);


        //Ação
        OleoEntity resultado = target.getOleos().get(0);

        //Verificação
        assertNotNull(resultado);

    }


}
