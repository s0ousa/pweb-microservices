package com.luis.pacientesms.services;


import com.luis.pacientesms.dtos.PacienteDTO;
import com.luis.pacientesms.dtos.PacienteMinDTO;
import com.luis.pacientesms.entities.Endereco;
import com.luis.pacientesms.entities.Paciente;
import com.luis.pacientesms.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public PacienteDTO searchById(Long id) {
        Paciente result = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico nao encontrado"));

        return new PacienteDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<PacienteMinDTO> findAll(Map<String, String> param, Pageable pageable) {
        String page = Optional.ofNullable(param.get("page")).orElse("0");
        int pageSize = Integer.parseInt(Optional.ofNullable(param.get("size")).orElse("10"));
        String metodoOrdenacao = Optional.ofNullable(param.get("sortBy")).orElse("nome");

        pageable = PageRequest.of(Integer.parseInt(page),pageSize, Sort.by(metodoOrdenacao));
        Page<Paciente> result = pacienteRepository.findAll(pageable);
        return result.map(PacienteMinDTO::new);
    }

    public PacienteDTO create(PacienteDTO pacienteDTO) {
        Paciente novoPaciente = new Paciente(pacienteDTO);
        return new PacienteDTO(pacienteRepository.save(novoPaciente));
    }

    public PacienteDTO updateById(Long id, PacienteDTO dto) {
        checaEmail(dto);
        checaCpf(dto);

        Paciente entidade = pacienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Paciente nao encontrado"));

        if (dto.getNome()!=null)
            entidade.setNome(dto.getNome());
        if (dto.getTelefone()!=null)
            entidade.setTelefone(dto.getTelefone());
        if (dto.getEndereco()!=null)
            entidade.setEndereco(new Endereco(dto.getEndereco()));
        if (dto.getAtivo()!=null)
            entidade.setAtivo(dto.getAtivo());
        return new PacienteDTO(pacienteRepository.save(entidade));
    }

    private void checaEmail(PacienteDTO dto) {
        if(Optional.ofNullable(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Não pode atualizar email do paciente.");
        }
    }

    private void checaCpf(PacienteDTO dto) {
        if(Optional.ofNullable(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Não pode atualizar CPF do paciente.");
        }
    }

    public PacienteDTO inativa(Long id) {
        Paciente entidade = pacienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Paciente nao encontrado"));
        entidade.setAtivo(false);

        return new PacienteDTO(pacienteRepository.save(entidade));
    }

    public Paciente buscaPaciente(Long id) {
        return pacienteRepository.findByIdAndAtivo(id, true).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente nao encontrada ou inativo"));
    }

}
