package br.com.mateusalves.cliente.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Entity //Uma Tabela no banco de Dados.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco_cliente") //Nome da tabela
public class Endereco {

    @Id
    @Column(name = "cliente_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    @NotBlank (message = " Cidade não pode ser nulo ou invalido!")
    private String cidade;

    @Column(nullable = false)
    @NotBlank (message = " Estado não pode ser nulo ou invalido !")
    private String uf;

    @Column(nullable = false)
    @NotBlank (message = " Logradouro não pode ser nulo ou invalido !")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank (message = " O CEP não pode ser nulo ou invalido !")
    private String cep;

    private String complemento;

    private Long numero;

    private String referencia;



}
