package com.luis.pacientesms.entities;

import com.luis.pacientesms.dtos.PacienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private String cpf;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(PacienteDTO dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.cpf = dto.getCpf();
        this.endereco = new Endereco(dto.getEndereco());
        this.ativo = dto.getAtivo();
    }
}
