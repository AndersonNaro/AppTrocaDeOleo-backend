package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id_oleo", nullable = false)
    private Long idOleo;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "km", nullable = false)
    private String km;

    @Column(name = "filtro")
    private Boolean filtro;

    @ManyToOne
    @JoinColumn(name = "id_moto")
    private MotoEntity moto;

    public OleoEntity(Date data, boolean filtro, String km, MotoEntity moto) {
        this.data = data;
        this.filtro = filtro;
        this.km = km;
        this.moto = moto;
    }
}
