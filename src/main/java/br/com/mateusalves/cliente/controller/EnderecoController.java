package br.com.mateusalves.cliente.controller;


import br.com.mateusalves.cliente.dto.EnderecoRequestDTO;
import br.com.mateusalves.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cliente/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cpf/{cpf}")
    public EnderecoRequestDTO findByClienteCpf(@PathVariable String cpf){
        return enderecoService.findByCpfCliente(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public EnderecoRequestDTO findByClienteEmail(@PathVariable String email){
        return enderecoService.findByEmailCliente(email);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cpf/{cpf}")
    public void updateByCpfCliente(@PathVariable String cpf,
                                   @RequestBody EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        if (!StringUtils.hasText(cpf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Invalid or Null.");
        }
        enderecoService.updateEnderecoByCpfCliente(cpf, enderecoRequestDTO);

    }

}
