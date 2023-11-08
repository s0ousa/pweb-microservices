package com.luis.consultasms.controllers;


import com.luis.consultasms.dtos.Cancelamento;
import com.luis.consultasms.dtos.ConsultaDTO;
import com.luis.consultasms.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConsultaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.searchById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaDTO>> findAll(@RequestParam Map<String, String> param, Pageable pageable) {
        return ResponseEntity.ok(consultaService.findAll(param, pageable));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> create(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.create(consultaDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ConsultaDTO> desativa(@PathVariable Long id , @RequestBody Cancelamento cancelamento) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.desmarcaConsulta(id, cancelamento));
    }

}
