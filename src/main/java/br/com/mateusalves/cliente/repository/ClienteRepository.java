package br.com.mateusalves.cliente.repository;

import br.com.mateusalves.cliente.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long >{

    public Cliente findByEmail(String email);
    public Cliente findByCpf(String cpf);

}
