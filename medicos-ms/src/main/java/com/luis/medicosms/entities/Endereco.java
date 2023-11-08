package com.luis.medicosms.entities;

import com.luis.medicosms.dtos.EnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

//    @OneToMany
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Medico> medicos;


    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.complemento = dto.getComplemento();
        this.bairro = dto.getBairro();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
        this.cep = dto.getCep();
    }
}
