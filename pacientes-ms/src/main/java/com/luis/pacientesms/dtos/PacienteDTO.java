package com.luis.pacientesms.dtos;

import com.luis.pacientesms.entities.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class PacienteDTO {


    private Long id;
    @NotBlank(message = "Nome não pode estar em branco.")
    private String nome;
    @NotBlank
    @Email(message = "Endereço de email inválido.")
    private String email;
    @NotBlank (message = "Telefone não pode estar em branco.")
    private String telefone;
    @NotBlank @CPF (message = "CPF não pode estar em branco.")
    private String cpf;

    @Valid
    @NotNull(message = "Endereco não pode estar em branco.")
    private EnderecoDTO endereco;
    private Boolean ativo = true;

    public PacienteDTO(Paciente entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.telefone = entidade.getTelefone();
        this.cpf = entidade.getCpf();
        this.endereco = new EnderecoDTO(entidade.getEndereco());
        this.ativo = entidade.getAtivo();
    }
}
