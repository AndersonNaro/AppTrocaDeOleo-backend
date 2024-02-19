package oleo.com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class OleoDto {
    private Long id;
    private Date data;
    private String km;
    private Boolean filtro;
}





