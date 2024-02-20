package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oleo.com.br.dto.MotoDto;
import oleo.com.br.dto.OleoDto;
import oleo.com.br.dto.ProprietarioDto;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_moto")
@NoArgsConstructor
@EqualsAndHashCode
public class MotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "placa", nullable = false)
    private String placa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proprietario")
    private ProprietarioEntity proprietario;

    @OneToMany(mappedBy = "moto" , fetch = FetchType.EAGER)
    private List<OleoEntity> oleos;
    public MotoEntity(Long id, String nome, String modelo, String placa, List<OleoEntity> oleos) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.placa = placa;
        this.oleos = oleos;
    }

}
