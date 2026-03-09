package com.prueba.polizas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PolizaDTO {
    private Long id;
    private String tipo;
    private String estado;
    private BigDecimal canon;
    private BigDecimal prima;
    private List<RiesgoDTO> riesgos;
}