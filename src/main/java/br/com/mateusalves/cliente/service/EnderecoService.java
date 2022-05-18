package br.com.mateusalves.cliente.service;

import br.com.mateusalves.cliente.dto.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface EnderecoService {

    EnderecoDTO findByClienteCpf(String cpf);

    EnderecoDTO findByClienteEmail(String email);
}
