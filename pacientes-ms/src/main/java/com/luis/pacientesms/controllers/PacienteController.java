package com.luis.pacientesms.controllers;

import com.luis.pacientesms.dtos.PacienteDTO;
import com.luis.pacientesms.dtos.PacienteMinDTO;
import com.luis.pacientesms.services.PacienteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.searchById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteMinDTO>>  findAll(@RequestParam Map<String, String> param, Pageable pageable) {
        return ResponseEntity.ok(pacienteService.findAll(param, pageable));
    }


    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.create(pacienteDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO> updateById(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return  ResponseEntity.status(HttpStatus.OK).body(pacienteService.updateById(id, pacienteDTO));
    }

    @DeleteMapping(value = "/{id}")
    public PacienteDTO inativa(@PathVariable Long id) {
        return pacienteService.inativa(id);
    }
}
