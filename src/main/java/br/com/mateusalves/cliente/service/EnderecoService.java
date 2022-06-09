package br.com.mateusalves.cliente.service;

import br.com.mateusalves.cliente.dto.EnderecoRequestDTO;
import br.com.mateusalves.cliente.dto.EnderecoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface EnderecoService {

    EnderecoRequestDTO findByCpfCliente(String cpf);

    EnderecoRequestDTO findByEmailCliente(String email);

    EnderecoResponseDTO updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO) throws  Exception;

}
