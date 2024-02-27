package oleo.com.br.controller;

import lombok.RequiredArgsConstructor;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.repository.OleoRepository;
import oleo.com.br.service.OleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/oleo")
public class OleoController {

    @Autowired
    OleoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OleoDto> findOleoById(@PathVariable Long id) {
        if(service.findOleoById(id) != null)
            return ResponseEntity.ok().body(service.findOleoById(id));
        return  ResponseEntity.notFound().build();

    }

    @PostMapping(value = "/create")
    public ResponseEntity<OleoDto> createOleo(@RequestBody OleoDto oleo) {
        return ResponseEntity.ok().body(service.createOleo(oleo));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Long> DeleteOleo(@RequestBody OleoDto oleo) {
        return ResponseEntity.ok().body(service.deleteOleo(oleo));
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<OleoDto> UpdateOleo(@RequestBody OleoDto oleo) {
        OleoDto response = service.updateOleo(oleo);
        if(response != null)
            return ResponseEntity.ok().body(response);
        return ResponseEntity.notFound().build();
    }
}
