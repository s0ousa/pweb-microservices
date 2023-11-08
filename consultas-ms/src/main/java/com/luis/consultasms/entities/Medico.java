package com.luis.consultasms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    private Long id;
    private String nome;
    private String email;
    private String crm;

}