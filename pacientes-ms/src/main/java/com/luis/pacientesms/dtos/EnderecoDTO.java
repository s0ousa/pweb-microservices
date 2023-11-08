package com.luis.pacientesms.dtos;

import com.luis.pacientesms.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {
    @NotBlank(message = "Logradouro não pode estar em branco.")
    private String logradouro;

    private int numero;
    private String complemento;

    @NotBlank(message = "Bairro não pode estar em branco.")
    private String bairro;

    @NotBlank(message = "Cidade não pode estar em branco.")
    private String cidade;

    @NotBlank(message = "UF não pode estar em branco.")
    private String uf;

    @NotBlank(message = "CEP não pode estar em branco.")
    private String cep;

//    @OneToMany
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Medico> medicos;

    public EnderecoDTO(Endereco entidade) {
        this.logradouro = entidade.getLogradouro();
        this.numero = entidade.getNumero();
        this.complemento = entidade.getComplemento();
        this.bairro = entidade.getBairro();
        this.cidade = entidade.getCidade();
        this.uf = entidade.getUf();
        this.cep = entidade.getCep();
//        this.medicos = entidade.getMedicos();
    }
}
