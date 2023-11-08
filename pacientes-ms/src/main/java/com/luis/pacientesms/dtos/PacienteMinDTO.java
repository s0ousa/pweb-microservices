package com.luis.pacientesms.dtos;

import com.luis.pacientesms.entities.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteMinDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;

    public PacienteMinDTO(Paciente entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.cpf = entidade.getCpf();
    }

}
