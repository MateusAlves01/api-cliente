package br.com.mateusalves.cliente.service;

import br.com.mateusalves.cliente.dto.ClienteRequestDTO;
import br.com.mateusalves.cliente.dto.ClienteResponseDTO;
import br.com.mateusalves.cliente.dto.EnderecoDTO;
import br.com.mateusalves.cliente.model.Cliente;
import br.com.mateusalves.cliente.model.Endereco;
import br.com.mateusalves.cliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "clientes", allEntries = true)
    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO){
//        Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
        Cliente cliente =  Cliente.builder()
                .cpf(clienteRequestDTO.getCpf())
                .nome(clienteRequestDTO.getNome())
                .sobrenome(clienteRequestDTO.getSobrenome())
                .email(clienteRequestDTO.getEmail())
                .ddd(clienteRequestDTO.getDdd())
                .telefone(clienteRequestDTO.getTelefone())
                .build();
         Cliente clienteSalvo = clienteRepository.save(cliente);
         clienteSalvo.setEndereco(Endereco.builder()
                 .id(clienteSalvo.getId())
                 .cliente(clienteSalvo)
                 .cep(clienteRequestDTO.getEndereco().getCep())
                 .cidade(clienteRequestDTO.getEndereco().getCidade())
                 .uf(clienteRequestDTO.getEndereco().getUf())
                 .logradouro(clienteRequestDTO.getEndereco().getLogradouro())
                 .complemento(clienteRequestDTO.getEndereco().getComplemento())
                 .referencia(clienteRequestDTO.getEndereco().getReferencia())
                 .numero(clienteRequestDTO.getEndereco().getNumero())
                 .build());
//            return  modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
        return ClienteResponseDTO.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .sobrenome(cliente.getSobrenome())
                .email(cliente.getEmail())
                .ddd(cliente.getDdd())
                .telefone(cliente.getTelefone())
                .endereco(EnderecoDTO.builder()
                        .cep(clienteSalvo.getEndereco().getCep())
                        .cidade(clienteSalvo.getEndereco().getCidade())
                        .uf(clienteSalvo.getEndereco().getUf())
                        .logradouro(clienteSalvo.getEndereco().getLogradouro())
                        .complemento(clienteSalvo.getEndereco().getComplemento())
                        .referencia(clienteSalvo.getEndereco().getReferencia())
                        .numero(clienteSalvo.getEndereco().getNumero())
                        .build())
                .build();
    }
    @Cacheable("clientes")
    public List<ClienteResponseDTO> listarClientes(String nome) {

        List<Cliente> clienteList = null;

        if (nome == null ) {
            clienteList = (List<Cliente>) clienteRepository.findAll();
        } else {
            clienteList = (List<Cliente>)clienteRepository.findByNomeContainingIgnoreCase(nome);
        }

        Collections.sort(clienteList, Comparator.comparing(Cliente::getNome)); //Listar em ordem alfabetica

        List<ClienteResponseDTO> clienteResponseDTOList = new ArrayList<>();
        clienteList.forEach(cliente -> {

              ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.builder()
                    .cpf(cliente.getCpf())
                    .nome(cliente.getNome())
                    .sobrenome(cliente.getSobrenome())
                    .email(cliente.getEmail())
                    .telefone(cliente.getTelefone())
                    .build();
            //ClienteResponseDTO clienteResponseDTO = modelMapper.map(cliente, ClienteResponseDTO.class); 44 a 49
          clienteResponseDTOList.add(clienteResponseDTO); //Adiciona a lista no clienteResponseDTO
        });
        return clienteResponseDTOList;
    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletarCliente(String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new Exception("Cliete não encontrado. Verifique o Email digitado.");
        }
        clienteRepository.deleteById(cliente.getId());

    }

    @Cacheable("clientes")
    public ClienteResponseDTO consultarPorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void atualizarCliente(ClienteRequestDTO clienteRequestDTO, String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new Exception("Cliete não encontrado.");
        }
        //Parse
        modelMapper.map(clienteRequestDTO, cliente);
        clienteRepository.save(cliente);
    }
}
