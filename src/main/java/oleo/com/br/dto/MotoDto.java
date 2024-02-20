package oleo.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class MotoDto {
    private Long id;
    private String nome;
    private String modelo;
    private String placa;
    private List<OleoDto> oleos;
}
