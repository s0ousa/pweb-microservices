package com.luis.consultasms.entities;

import com.luis.consultasms.dtos.EnumMotivoDeCancelamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder

@Entity
@Table(name = "tb_consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
    private Long pacienteID;
//    @ManyToOne
    private Long medicoID;
    private LocalDateTime agendamento;
    private boolean ativo;
    @Enumerated(EnumType.STRING)
    private EnumMotivoDeCancelamento motivoDeCancelamento;

}
