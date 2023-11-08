package com.luis.consultasms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    private Long id;
    private String nome;
    private String email;
    private String cpf;


}

