package com.luis.pacientesms.repositories;

import com.luis.pacientesms.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Optional<Paciente> findByIdAndAtivo(Long id, boolean ativo);



}
