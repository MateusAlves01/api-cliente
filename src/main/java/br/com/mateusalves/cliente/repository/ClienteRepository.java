package br.com.mateusalves.cliente.repository;

import br.com.mateusalves.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long >{

        public List<Cliente> findByNomeContainingIgnoreCase(String nome);
        public Cliente findByEmail(String email);
        public Cliente findByCpf(String cpf);

}
