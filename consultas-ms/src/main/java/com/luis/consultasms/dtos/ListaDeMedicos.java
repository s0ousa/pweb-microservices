package com.luis.consultasms.dtos;

import com.luis.consultasms.entities.MedicoMinDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaDeMedicos {
    private List<MedicoMinDTO> medicos;
}
