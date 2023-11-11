package com.luis.consultasms.repositories;

import com.luis.consultasms.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT medicoid FROM tb_consulta WHERE agendamento > :horaMarcada AND agendamento < :termino")
    public List<Long> medicosIndisponiveis(@Param("horaMarcada") LocalDateTime horaMarcada,
                                           @Param("termino") LocalDateTime termino);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_consulta WHERE agendamento >= ?2 AND pacienteid = ?1 and ativo != false")
    public Optional<Consulta> ConsultaAgendadaNoDia(@Param(value = "id") Long id, LocalDateTime agendamento);
}
