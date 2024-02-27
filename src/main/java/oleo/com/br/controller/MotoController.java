package oleo.com.br.controller;

import lombok.RequiredArgsConstructor;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.entity.MotoEntity;
import oleo.com.br.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    MotoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        if(service.findMotoById(id) != null)
            return ResponseEntity.ok().body(service.findMotoById(id));
        return  ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity createMoto(@RequestBody MotoDto moto) {
        return ResponseEntity.ok().body(service.createMoto(moto));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteMoto(@RequestBody MotoDto moto) {
        return ResponseEntity.ok().body(service.deleteMoto(moto));
    }

    @PatchMapping(value = "/update")
    public ResponseEntity updateMoto(@RequestBody MotoDto moto) {
        service.updateMoto(moto);
        return ResponseEntity.ok().build();
    }
}
