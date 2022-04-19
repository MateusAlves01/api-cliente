package br.com.mateusalves.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String ddd;
    private String telefone;
    private EnderecoDTO endereco;

}
