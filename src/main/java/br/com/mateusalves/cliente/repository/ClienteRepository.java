package br.com.mateusalves.cliente.repository;

import br.com.mateusalves.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long >{

        public List<Cliente> findByNomeContainingIgnoreCase(String nome);
        public Cliente findByEmail(String email);
        public Cliente findByCpf(String cpf);

}
