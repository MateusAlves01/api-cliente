package br.com.mateusalves.cliente.controller;


import br.com.mateusalves.cliente.dto.EnderecoDTO;
import br.com.mateusalves.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cpf/{cpf}")
    public EnderecoDTO findByClienteCpf(@PathVariable String cpf){
        return enderecoService.findByClienteCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{email}")
    public EnderecoDTO findByClienteEmail(@PathVariable String email){
        return enderecoService.findByClienteEmail(email);
    }


}
