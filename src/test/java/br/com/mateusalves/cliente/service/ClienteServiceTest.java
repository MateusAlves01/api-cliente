package br.com.mateusalves.cliente.service;

import br.com.mateusalves.cliente.dto.ClienteRequestDTO;
import br.com.mateusalves.cliente.dto.ClienteResponseDTO;
import br.com.mateusalves.cliente.model.Cliente;
import br.com.mateusalves.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class ClienteServiceTest {

    private Cliente clienteSalvo;
    private Cliente cliente;
    private Cliente cliente2;
    private Cliente cliente3;
    private List<Cliente> clienteList;
    private ClienteResponseDTO clienteResponseDTO;
    private ClienteRequestDTO clienteRequestDTO;


    @Mock
    private ClienteRepository clienteRepository;

    @Mock //Simular
    private ModelMapper modelMapper;

    @InjectMocks //Testar
    private ClienteService clienteService;

    @BeforeEach
    public void init(){
        this.cliente = Cliente.builder()
                .cpf("888.236.123.12")
                .nome("Marcelo")
                .sobrenome("Alves")
                .email("ariel.01@gmail.com")
                .telefone("9936542331")
                .build();
        this.cliente2 = Cliente.builder()
                .id(2L)
                .nome("Ariel")
                .cpf("888.888.999.88")
                .build();
        this.cliente3 = Cliente.builder()
                .id(3L)
                .nome("Jardel")
                .cpf("888.888.000.88")
                .build();
        this.clienteSalvo = Cliente.builder()
                .id(1L)
                .cpf("888.236.123.12")
                .nome("Marcelo")
                .sobrenome("Alves")
                .email("ariel.01@gmail.com")
                .telefone("9936542331")
                .build();

        this.clienteRequestDTO= ClienteRequestDTO.builder()
                .cpf("888.236.123.12")
                .nome("Marcelo")
                .sobrenome("Alves")
                .email("ariel.01@gmail.com")
                .telefone("9936542331")
                .build();

        this.clienteResponseDTO = ClienteResponseDTO.builder()
                .cpf("888.236.123.12")
                .nome("Marcelo")
                .sobrenome("Alves")
                .email("ariel.01@gmail.com")
                .telefone("9936542331")
                .build();

        clienteList = Arrays.asList(cliente, cliente2, cliente3);
    }

    @Test
    public void listarClientesTest(){
        when(clienteRepository.findAll()).thenReturn(this.clienteList);
        List<ClienteResponseDTO> listaClientes = clienteService.listarClientes(null);
        Assertions.assertNotNull(listaClientes);
        Assertions.assertEquals(3, listaClientes.size());
        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }
    @Test
    public void criarClienteTest() {
//        when(modelMapper.map(clienteRequestDTO, Cliente.class)).thenReturn(cliente);
       // when(this.clienteRepository.save(this.cliente)).thenReturn(this.clienteSalvo);
        lenient().when(this.clienteRepository.save(this.cliente)).thenReturn(clienteSalvo);
//        when(modelMapper.map(clienteSalvo, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

       ClienteResponseDTO clienteResponseDTO = clienteService.criar(clienteRequestDTO);
       Assertions.assertNotNull(clienteResponseDTO);
       Assertions.assertEquals("888.236.123.12", clienteResponseDTO.getCpf());


    }
}
