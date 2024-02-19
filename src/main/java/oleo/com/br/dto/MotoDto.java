package oleo.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MotoDto {
    private Long id;
    private String nome;
    private String modelo;
    private String placa;
    private List<OleoDto> oleos;
}
