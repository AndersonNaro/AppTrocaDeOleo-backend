package oleo.com.br.controller;

import oleo.com.br.dto.ProprietarioDto;
import oleo.com.br.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @PostMapping(value = "/create")
    public ResponseEntity<ProprietarioDto> createProprietario(@RequestBody ProprietarioDto proprietario) {
        System.out.println(proprietario);
        return ResponseEntity.ok().body(service.createProprietario(proprietario));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProprietarioDto> findProprietarioById(@PathVariable Long id){
        if(service.getProprietarioById(id) != null)
            return ResponseEntity.ok().body(service.getProprietarioById(id));
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Long> deleteProprietario(@RequestBody ProprietarioDto proprietario) {
        return ResponseEntity.ok().body(service.deleteProprietario(proprietario));
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<ProprietarioDto> updateProprietario(@RequestBody ProprietarioDto proprietario) {
        service.updateProprietario(proprietario);
        return ResponseEntity.ok().build();
    }

}
