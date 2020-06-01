package renato.cliente.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import renato.cliente.dto.ClienteDTO;
import renato.cliente.model.Cliente;
import renato.cliente.service.ClienteService;
import renato.cliente.util.FormatUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/v1/cliente")
    public ClienteDTO insert(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente savedCliente = clienteService.insert(clienteDTO.toCliente());
        return ClienteDTO.of(savedCliente);
    }

    @PutMapping("/v1/cliente/{id}")
    public ClienteDTO update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente savedCliente = clienteService.update(id, clienteDTO.toCliente());
        return ClienteDTO.of(savedCliente);
    }

    @PatchMapping("/v1/cliente/{id}")
    public ClienteDTO patch(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente savedCliente = clienteService.update(id, clienteDTO.toCliente());
        return ClienteDTO.of(savedCliente);
    }

    @GetMapping("/v1/cliente/{id}")
    public ClienteDTO findCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.findCliente(id);
        return ClienteDTO.of(cliente);
    }

    @GetMapping("/v1/clientes")
    public Page<ClienteDTO> findClientesByCpfAndNome(@RequestParam(required = false) String cpf,
                                                     @RequestParam(required = false) String nome,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "20") int size) {
        String cpfUnformatted = FormatUtil.unformatCpf(cpf);
        Page<Cliente> clientes = clienteService.findClienteByCpfAndNome(cpfUnformatted, nome, page, size);
        return clientes.map(ClienteDTO::of);
    }

    @DeleteMapping("/v1/cliente/{id}")
    public void remove(@PathVariable Long id) {
        clienteService.removeCliente(id);
    }

}
