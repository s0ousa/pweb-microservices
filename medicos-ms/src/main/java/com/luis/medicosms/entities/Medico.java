package com.luis.medicosms.entities;

import com.luis.medicosms.dtos.MedicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoDTO medicoDTO) {
        this.nome = medicoDTO.getNome();
        this.email = medicoDTO.getEmail();
        this.telefone = medicoDTO.getTelefone();
        this.crm = medicoDTO.getCrm();
        this.especialidade = medicoDTO.getEspecialidade();
        this.endereco = medicoDTO.getEndereco();
        this.ativo = medicoDTO.getAtivo();
    }
}