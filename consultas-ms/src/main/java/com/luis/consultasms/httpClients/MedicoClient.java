package com.luis.consultasms.httpClients;

import com.luis.consultasms.entities.MedicoMinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "medicos-ms")
public interface MedicoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/medicos/{id}")
    MedicoMinDTO buscaMedico(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/medicos")
    List<MedicoMinDTO> buscaMedicosDisponiveis(List<Long> medicosIndisponiveisIds);
}
