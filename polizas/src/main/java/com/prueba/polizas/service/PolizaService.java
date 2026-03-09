package com.prueba.polizas.service;

import com.prueba.polizas.entity.Poliza;
import com.prueba.polizas.entity.Riesgo;

import java.util.List;

public interface PolizaService {

    List<Poliza> listarPorTipoYEstado(String tipo, String estado);

    List<Riesgo> obtenerRiesgos(Long polizaId);

    Poliza renovarPoliza(Long id);

    Poliza cancelarPoliza(Long id);

    Riesgo agregarRiesgo(Long polizaId, Riesgo riesgo);
    Riesgo cancelarRiesgo(Long riesgoId);
    Poliza crearPoliza(Poliza poliza);
}