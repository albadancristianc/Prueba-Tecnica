package com.prueba.polizas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiesgoDTO {
    private Long id;
    private String descripcion;
    private String estado;
}