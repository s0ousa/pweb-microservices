package com.luis.medicosms.dtos;

import com.luis.medicosms.entities.Especialidade;
import com.luis.medicosms.entities.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoMinDTO {
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;

    public MedicoMinDTO(Medico entidade) {
        this.id =entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.crm = entidade.getCrm();
        this.especialidade = entidade.getEspecialidade();
    }
}

