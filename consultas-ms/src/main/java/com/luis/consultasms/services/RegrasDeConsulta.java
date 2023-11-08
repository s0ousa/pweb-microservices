package com.luis.consultasms.services;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@NoArgsConstructor
public class RegrasDeConsulta {

    public boolean validaConsulta(LocalDateTime horaMarcada) {
        if (horaMarcada.plusHours(1).isAfter(horaMarcada.withHour(19).withMinute(0))) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Só funcionamos até as 19 horas");
        }

        if(horaMarcada.isBefore(horaMarcada.withHour(7).withMinute(0))){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Funcionamos a partir das 7 horas");
        }

        if (horaMarcada.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Funcionamos de Segunda a Sabado");
        }

        if(horaMarcada.isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Exigimos uma antecedencia de pelo menos 30min");
        }
        return true;
    }

}
