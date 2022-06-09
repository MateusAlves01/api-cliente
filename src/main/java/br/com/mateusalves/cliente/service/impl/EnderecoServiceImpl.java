package br.com.mateusalves.cliente.service.impl;


import br.com.mateusalves.cliente.dto.EnderecoRequestDTO;
import br.com.mateusalves.cliente.dto.EnderecoResponseDTO;
import br.com.mateusalves.cliente.model.Endereco;
import br.com.mateusalves.cliente.repository.EnderecoRepository;
import br.com.mateusalves.cliente.service.EnderecoService;
import br.com.mateusalves.cliente.utils.TextoUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EnderecoRequestDTO findByCpfCliente(String cpf) {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        if (TextoUtils.contemTexto(cpf)) {
            Endereco endereco = enderecoRepository.findByCpfCliente(cpf);
            return modelMapper.map(endereco, EnderecoRequestDTO.class);
        }
        return null;
    }

    @Override
    public EnderecoRequestDTO findByEmailCliente(String email) {
        Endereco endereco = enderecoRepository.findByEmailCliente(email);

        return EnderecoRequestDTO.builder()
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .uf(endereco.getUf())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .referencia(endereco.getReferencia())
                .build();
    }

    @Override
    public EnderecoResponseDTO updateEnderecoByCpfCliente(String cpf, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        cpf = TextoUtils.removeEspecialCaracter(cpf);
        Endereco endereco = enderecoRepository.findByCpfCliente(cpf);
        if(endereco == null){
            throw new Exception("Endereço não encontrado.");
        }
        modelMapper.map(enderecoRequestDTO, endereco);
        enderecoRepository.save(endereco);

        return modelMapper.map(endereco, EnderecoResponseDTO.class);
    }



}
