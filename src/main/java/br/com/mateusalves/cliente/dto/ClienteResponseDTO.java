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

    private String nomeCompleto;
    private String cpf;
    private String enderecoEletronico;
    private String ddd;
    private String telefone;
    private EnderecoDTO endereco;
}
