package br.com.mateusalves.cliente.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@Entity //Uma Tabela no banco de Dados.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente") //NOme da tabela
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(nullable = false, unique = true ,length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 3)
    private String ddd;

    @Column(length = 50)
    private String telefone;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;


}
