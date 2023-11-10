package com.luis.consultasms.entities;

import com.luis.medicosms.entities.Especialidade;
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

}