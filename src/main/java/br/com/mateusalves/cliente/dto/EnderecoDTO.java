package br.com.mateusalves.cliente.dto;

import br.com.mateusalves.cliente.model.Cliente;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotBlank(message = " Cidade não pode ser nulo ou invalido!")
    private String cidade;

    @NotBlank(message = " Estado não pode ser nulo ou invalido !")
    private String uf;

    @NotBlank(message = " Logradouro não pode ser nulo ou invalido !")
    private String logradouro;

    @NotBlank(message = " O CEP não pode ser nulo ou invalido !")
    private String cep;

    private String complemento;

    private Long numero;

    private String referencia;

}



