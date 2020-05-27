package renato.teste.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cliente.nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{cliente.cpf.obrigatorio}")
    @CPF(message = "{cliente.cpf.invalido}")
    private String cpf;

    @NotNull(message = "{cliente.data.nascimento.obrigatorio}")
    @Past(message = "{cliente.data.nascimento.passado}")
    private LocalDate dataNascimento;

}
