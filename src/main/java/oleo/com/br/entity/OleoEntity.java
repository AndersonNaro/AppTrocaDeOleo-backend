package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_troca_oleo")
public class OleoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_troca_oleo", nullable = false)
    private Long idTrocaOleo;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "km", nullable = false)
    private String km;

    @Column(name = "filtro")
    private String filtro;

    @JoinColumn(name = "id_moto", nullable = false)
    @ManyToOne( cascade = CascadeType.ALL)
    private MotoEntity moto;

}
