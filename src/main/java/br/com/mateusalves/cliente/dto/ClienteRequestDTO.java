package br.com.mateusalves.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotNull(message = "Nome é Obrigatório.")
    @Length(min = 3, max = 255)
    private String nome;

    @NotNull(message = "Sobre Nome é Obrigatório.")
    @Length(min = 3, max = 255)
    private String sobrenome;

    @NotNull(message = "{cpf.notNull}")
    @CPF(message = "{cpf}")
    private String cpf;

    @NotNull(message = "{email.notNull}")
    @Email(message = "{email}")
    private String email;

    @Length(min = 2, max = 3)
    private String ddd;

    @Length(min = 5, max = 30)
    private String telefone;

    private EnderecoDTO endereco;

}
