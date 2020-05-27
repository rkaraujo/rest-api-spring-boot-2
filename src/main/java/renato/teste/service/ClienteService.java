package renato.teste.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import renato.teste.exception.BusinessException;
import renato.teste.exception.NotFoundException;
import renato.teste.model.Cliente;
import renato.teste.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final MessageService messageService;

    public ClienteService(ClienteRepository clienteRepository, MessageService messageService) {
        this.clienteRepository = clienteRepository;
        this.messageService = messageService;
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new BusinessException(messageService.getMessage("cliente.cpf.ja.cadastrado"));
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente update(Long idCliente, Cliente cliente) {
        Cliente dbCliente = findCliente(idCliente);

        if (cliente.getCpf() != null) {
            boolean isUpdatingCpf = !dbCliente.getCpf().equals(cliente.getCpf());
            if (isUpdatingCpf && clienteRepository.existsByCpf(cliente.getCpf())) {
                throw new BusinessException(messageService.getMessage("cliente.cpf.ja.cadastrado"));
            }
            dbCliente.setCpf(cliente.getCpf());
        }
        if (cliente.getDataNascimento() != null) {
            dbCliente.setDataNascimento(cliente.getDataNascimento());
        }
        if (cliente.getNome() != null) {
            dbCliente.setNome(cliente.getNome());
        }

        return clienteRepository.save(dbCliente);
    }

    public Cliente findCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage("cliente.nao.encontrado", id)));
    }

    public Page<Cliente> findClienteByCpfAndNome(String cpf, String nome, int page, int size) {
        if (cpf != null && nome != null) {
            PageRequest pageable = PageRequest.of(page, size, Sort.Direction.ASC, "cpf");
            return clienteRepository.findByCpfAndNomeContaining(cpf, nome, pageable);
        }
        if (nome != null) {
            PageRequest pageable = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
            return clienteRepository.findByNomeContaining(nome, pageable);
        }
        if (cpf != null) {
            PageRequest pageable = PageRequest.of(page, size, Sort.Direction.ASC, "cpf");
            return clienteRepository.findByCpf(cpf, pageable);
        }
        return Page.empty();
    }

    public void removeCliente(Long id) {
        Cliente cliente = findCliente(id);
        clienteRepository.delete(cliente);
    }

}
