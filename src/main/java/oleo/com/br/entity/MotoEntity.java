package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_moto")
@NoArgsConstructor
@EqualsAndHashCode(of = {"modelo", "marca", "placa"})
public class MotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Long idMoto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "placa", nullable = false)
    private String placa;


    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    private ProprietarioEntity proprietario;

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL)
    private List<OleoEntity> oleos;
    public MotoEntity(String nome, String modelo, String placa, ProprietarioEntity proprietario) {
        this.nome = nome;
        this.modelo = modelo;
        this.placa = placa;
        this.proprietario = proprietario;
    }
}
