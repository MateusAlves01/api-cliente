package br.com.mateusalves.cliente.service.impl;


import br.com.mateusalves.cliente.dto.EnderecoDTO;
import br.com.mateusalves.cliente.model.Endereco;
import br.com.mateusalves.cliente.repository.EnderecoRepository;
import br.com.mateusalves.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public EnderecoDTO findByClienteCpf(String cpf) {
        return null;
    }

    @Override
    public EnderecoDTO findByClienteEmail(String email) {

        Endereco endereco = enderecoRepository.findByClienteEmail(email);

        return EnderecoDTO.builder()
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .uf(endereco.getUf())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .referencia(endereco.getReferencia())
                .build();
    }
}
