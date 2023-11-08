package com.luis.consultasms.httpClients;

import com.luis.consultasms.entities.Paciente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "pacientes-ms", url = "localhost:8083/")
public interface PacienteClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pacientes/{id}")
    Paciente buscaPaciente (@PathVariable("id") Long id);

}
