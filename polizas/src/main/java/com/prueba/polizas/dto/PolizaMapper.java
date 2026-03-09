package com.prueba.polizas.dto;

import com.prueba.polizas.entity.Poliza;
import com.prueba.polizas.entity.Riesgo;

import java.util.List;
import java.util.stream.Collectors;

public class PolizaMapper {

    public static PolizaDTO toDTO(Poliza poliza) {
        List<RiesgoDTO> riesgosDTO = poliza.getRiesgos().stream()
                .map(PolizaMapper::toDTO)
                .collect(Collectors.toList());

        return new PolizaDTO(
                poliza.getId(),
                poliza.getTipo(),
                poliza.getEstado(),
                poliza.getCanon(),
                poliza.getPrima(),
                riesgosDTO
        );
    }

    public static RiesgoDTO toDTO(Riesgo riesgo) {
        return new RiesgoDTO(
                riesgo.getId(),
                riesgo.getDescripcion(),
                riesgo.getEstado()
        );
    }
}