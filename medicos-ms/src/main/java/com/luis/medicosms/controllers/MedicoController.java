package com.luis.medicosms.controllers;

import com.luis.medicosms.dtos.MedicoDTO;
import com.luis.medicosms.dtos.MedicoMinDTO;
import com.luis.medicosms.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping(value="/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
//      return ResponseEntity.ok(medicoService.searchById(id));
        return ResponseEntity.ok(new MedicoDTO(medicoService.buscaMedicoAtivo(id)));
    }

    @GetMapping(value = "todos/{id}")
    public ResponseEntity<MedicoDTO> findByIdTodos(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.searchById(id));
//     return ResponseEntity.ok(new MedicoDTO(medicoService.buscaMedicoAtivo(id)));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<MedicoMinDTO>> findAllPaginados(@RequestParam Map<String, String> param, Pageable pageable) {
        return ResponseEntity.ok(medicoService.findAllPaginados(param, pageable));
    }

//    @GetMapping
//    public List<MedicoMinDTO> findAllAvaiable(@RequestBody List<Long> idsMedicosIndisponiveis){
//        return medicoService
//                .buscaMedicosDisponiveis(idsMedicosIndisponiveis)
//                .stream()
//                .map(MedicoMinDTO::new)
//                .collect(Collectors.toList());
//    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<MedicoMinDTO>> findAll() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody @Valid MedicoDTO medicoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(medicoDTO));
    }

    @PutMapping(value = "/{id}")
    public MedicoDTO update(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        return medicoService.updateById(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public MedicoDTO inativa(@PathVariable Long id) {
        return medicoService.inativa(id);
    }

}
