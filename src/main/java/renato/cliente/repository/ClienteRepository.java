package renato.cliente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import renato.cliente.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Page<Cliente> findByCpfAndNomeContaining(String cpf, String nome, Pageable pageable);

    Page<Cliente> findByNomeContaining(String nome, Pageable pageable);

    Page<Cliente> findByCpf(String cpf, Pageable pageable);

    boolean existsByCpf(String cpf);

}
