package br.com.mateusalves.cliente.service;

import br.com.mateusalves.cliente.dto.ClienteRequestDTO;
import br.com.mateusalves.cliente.dto.ClienteResponseDTO;
import br.com.mateusalves.cliente.model.Cliente;
import br.com.mateusalves.cliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
        return clienteResponseDTO;
    }

    public List<ClienteResponseDTO> listarClientes(String nome) {

        List<Cliente> clienteList = null;

        if (nome == null ) {
            clienteList = (List<Cliente>) clienteRepository.findAll();
        } else {
            clienteList = (List<Cliente>)clienteRepository.findByNomeContainingIgnoreCase(nome);
        }

        Collections.sort(clienteList, Comparator.comparing(Cliente::getNome));

        List<ClienteResponseDTO> clienteResponseDTOList = new ArrayList<>();
        clienteList.forEach(cliente -> {
          ClienteResponseDTO clienteResponseDTO = modelMapper.map(cliente, ClienteResponseDTO.class);
          clienteResponseDTOList.add(clienteResponseDTO);
        });
        return clienteResponseDTOList;
    }

    public ClienteResponseDTO consultarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public void deletarCliente(String email) throws Exception {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new Exception("Cliete não encontrado. Verifique o Email digitado.");
        }
        clienteRepository.deleteById(cliente.getId());

    }

    public ClienteResponseDTO consultarPorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

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
