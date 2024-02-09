package oleo.com.br.controller;

import oleo.com.br.entity.ProprietarioEntity;
import oleo.com.br.exceptions.ProprietarioNaoEcontradoException;
import oleo.com.br.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proprietario")
public class ProprietaroController {

    @Autowired
    private ProprietarioService service;

    @PostMapping(value = "/create")
    public ResponseEntity<ProprietarioEntity> createProprietario(@RequestBody ProprietarioEntity proprietario) {
        return ResponseEntity.ok().body( service.createProprietario(proprietario));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ProprietarioEntity> findProprietarioById(@PathVariable Long id){
        if(service.getProprietarioById(id) != null)
            return ResponseEntity.ok().body(service.getProprietarioById(id));
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Long> deleteProprietario(@RequestBody ProprietarioEntity proprietario) {
        Long deletedRows = service.deleteProprietario(proprietario);
        return ResponseEntity.ok().body(deletedRows);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ProprietarioEntity> updateProprietario(@RequestBody ProprietarioEntity proprietario) {
        service.updateProprietario(proprietario);
        return ResponseEntity.ok().body( null);
    }

}
