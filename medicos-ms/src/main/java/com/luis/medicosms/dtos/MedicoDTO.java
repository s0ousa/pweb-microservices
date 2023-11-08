package com.luis.medicosms.dtos;

import com.luis.medicosms.entities.Endereco;
import com.luis.medicosms.entities.Especialidade;
import com.luis.medicosms.entities.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco.")
    private String nome;
    @NotBlank
    @Email(message = "Endereço de email inválido.")
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    private String crm;
    @NotBlank
    private Especialidade especialidade;
    @Valid
    @NotNull(message = "Endereco não pode estar em branco.")
    private Endereco endereco;
    private Boolean ativo = true;


    public MedicoDTO(Medico entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.telefone = entidade.getTelefone();
        this.crm = entidade.getCrm();
        this.especialidade = entidade.getEspecialidade();
        this.endereco = entidade.getEndereco();
        this.ativo = entidade.getAtivo();
    }
}
