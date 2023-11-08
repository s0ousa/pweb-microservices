package com.luis.consultasms.httpClients;

import com.luis.consultasms.entities.Medico;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "medicos-ms" , url = "localhost:8084/")
public interface MedicoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/medicos/{id}")
    Medico buscaMedico(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/medicos")
    List<Medico> buscaMedicosDisponiveis(List<Long> medicosIndisponiveisIds);
}
