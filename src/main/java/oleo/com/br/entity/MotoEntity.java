package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_moto")
public class MotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_moto", nullable = false)
    private Long idProprietario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "placa", nullable = false)
    private String placa;

    @JoinColumn(name = "id_proprietario", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private ProprietarioEntity proprietario;

}
