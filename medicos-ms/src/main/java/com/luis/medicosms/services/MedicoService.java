package com.luis.medicosms.services;



import com.luis.medicosms.dtos.MedicoDTO;
import com.luis.medicosms.dtos.MedicoMinDTO;
import com.luis.medicosms.entities.Medico;
import com.luis.medicosms.repositories.EnderecoRepository;
import com.luis.medicosms.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

//    @Transactional(readOnly = true)
//    //metodo privado, somente para uso interno da classe
//    private MedicoDTO findById(Long id) {
//        Medico result = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico nao encontrado"));
//        return new MedicoDTO(result);
//    }

    @Transactional(readOnly = true)
    public MedicoDTO searchById(Long id) {
        Medico result = medicoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Medico nao encontrado"));
        return new MedicoDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<MedicoMinDTO> findAllPaginados(Map<String, String> param, Pageable pageable) {
        String page = Optional.ofNullable(param.get("page")).orElse("0");
        int pageSize = Integer.parseInt(Optional.ofNullable(param.get("size")).orElse("10"));
        String metodoOrdenacao = Optional.ofNullable(param.get("sortBy")).orElse("nome");

        pageable = PageRequest.of(Integer.parseInt(page),pageSize, Sort.by(metodoOrdenacao));
        Page<Medico> result = medicoRepository.findAll(pageable);
        return result.map(MedicoMinDTO::new);
    }

    public MedicoDTO create(MedicoDTO medicoDTO) {
        Medico novoMedico = new Medico(medicoDTO);
        return new MedicoDTO(medicoRepository.save(novoMedico));
    }

    public MedicoDTO updateById(Long id, MedicoDTO dto) {
        // nao pode atualizar esses campos
        checaEmail(dto);
        checaCrm(dto);
        checaEspecialidade(dto);

        Medico entidade = medicoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Medico nao encontrado"));

        if (dto.getNome()!=null)
            entidade.setNome(dto.getNome());
        if (dto.getTelefone()!=null)
            entidade.setTelefone(dto.getTelefone());
        if (dto.getEndereco()!=null)
            entidade.setEndereco(dto.getEndereco());
        if (dto.getAtivo()!=null)
            entidade.setAtivo(dto.getAtivo());

        return new MedicoDTO(medicoRepository.save(entidade));
    }

    public MedicoDTO inativa(Long id) {
        Medico entidade = medicoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Medico nao encontrado"));

        entidade.setAtivo(false);

        return new MedicoDTO(medicoRepository.save(entidade));
    }
    public void checaEmail(MedicoDTO dto) {
        if(Optional.ofNullable(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Não pode atualizar email");
        }
    }

    public void checaCrm(MedicoDTO dto) {
        if(Optional.ofNullable(dto.getCrm()).isPresent()){
            throw new RuntimeException();
        }
    }

    public void checaEspecialidade(MedicoDTO dto) {
        if(Optional.ofNullable(dto.getEspecialidade()).isPresent()){
            throw new RuntimeException();
        }
    }

    public Medico buscaMedicoAtivo(Long id) {
        return medicoRepository.findByIdAndAtivo(id, true).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico nao encontrada"));
    }

    public List<Medico> buscaMedicosDisponiveis(List<Long> idsMedicosIndisponiveis) {
        Optional<List<Medico>> todosMedicos = medicoRepository.findAllByAtivo(true);

        Map<Long, Medico > map = todosMedicos
                .get()
                .stream()
                .collect(Collectors
                        .toMap(Medico::getId, Function.identity()));

        for(Long medicoId : idsMedicosIndisponiveis) {
            map.remove(medicoId);
        }

        List<Medico> medicosDisponiveis = new ArrayList<Medico>(map.values());

        return medicosDisponiveis;
    }

    public List<MedicoMinDTO> findAll() {
        List<Medico> result = medicoRepository.findAllByAtivo(true).get();
        return result
                .stream()
                .map(MedicoMinDTO::new)
                .collect(Collectors.toList());
    }
}

