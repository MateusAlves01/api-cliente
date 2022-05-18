package br.com.mateusalves.cliente.repository;

import br.com.mateusalves.cliente.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long > {

        public List<Cliente> findByNomeContainingIgnoreCase(String nome);
        public Cliente findByEmail(String email);
        public Cliente findByCpf(String cpf);

}
