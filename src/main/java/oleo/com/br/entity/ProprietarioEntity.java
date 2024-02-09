package oleo.com.br.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_proprietario")
public class ProprietarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proprietario")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    private List<MotoEntity> motos;

    public ProprietarioEntity(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }
}
