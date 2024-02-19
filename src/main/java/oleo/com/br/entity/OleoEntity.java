package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.*;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "tb_oleo")
public class OleoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oleo")
    private Long id;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "km", nullable = false)
    private String km;

    @Column(name = "filtro")
    private Boolean filtro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_moto")
    private MotoEntity moto;

    public OleoEntity(Long id, Date data, boolean filtro, String km) {
        this.id = id;
        this.data = data;
        this.filtro = filtro;
        this.km = km;
    }

}
