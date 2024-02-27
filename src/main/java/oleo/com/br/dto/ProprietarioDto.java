package oleo.com.br.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProprietarioDto {
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private List<MotoDto> motos;
}
