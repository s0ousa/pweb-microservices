package com.luis.consultasms.dtos;

import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Cancelamento {
    @Enumerated
    private EnumMotivoDeCancelamento motivoDeCancelamento;
}
