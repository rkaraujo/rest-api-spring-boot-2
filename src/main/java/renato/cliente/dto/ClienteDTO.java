package renato.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import renato.cliente.model.Cliente;
import renato.cliente.util.FormatUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.Period;

@Data
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "{cliente.nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{cliente.cpf.obrigatorio}")
    @CPF(message = "{cliente.cpf.invalido}")
    private String cpf;

    @JsonProperty("data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{cliente.data.nascimento.obrigatorio}")
    @Past(message = "{cliente.data.nascimento.passado}")
    private LocalDate dataNascimento;

    public static ClienteDTO of(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(FormatUtil.formatCpf(cliente.getCpf()));
        dto.setDataNascimento(cliente.getDataNascimento());
        return dto;
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(FormatUtil.unformatCpf(cpf));
        cliente.setDataNascimento(dataNascimento);
        return cliente;
    }

    @JsonProperty("idade")
    private int getAge() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

}
