package com.prueba.polizas.service;

import com.prueba.polizas.entity.Poliza;
import com.prueba.polizas.entity.Riesgo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PolizaServiceImpl implements PolizaService {

    private final List<Poliza> polizas = new ArrayList<>();
    private final AtomicLong polizaIdCounter = new AtomicLong(1);
    private final AtomicLong riesgoIdCounter = new AtomicLong(1);

    private static final BigDecimal IPC = new BigDecimal("0.10"); 

    @Override
    public List<Poliza> listarPorTipoYEstado(String tipo, String estado) {
        return polizas.stream()
                .filter(p -> p.getTipo().equals(tipo) && p.getEstado().equals(estado))
                .collect(Collectors.toList());
    }

    @Override
    public List<Riesgo> obtenerRiesgos(Long polizaId) {
        Poliza poliza = findPolizaById(polizaId);
        return poliza.getRiesgos();
    }

    @Override
    public Poliza renovarPoliza(Long id) {
        Poliza poliza = findPolizaById(id);
        if ("CANCELADA".equals(poliza.getEstado())) {
            throw new RuntimeException("No se puede renovar una póliza cancelada");
        }

        poliza.setCanon(poliza.getCanon().add(poliza.getCanon().multiply(IPC)));
        poliza.setPrima(poliza.getPrima().add(poliza.getPrima().multiply(IPC)));
        poliza.setEstado("RENOVADA");

        log.info("Enviando evento ACTUALIZACION al CORE para poliza {}", id);
        return poliza;
    }

    @Override
    public Poliza cancelarPoliza(Long id) {
        Poliza poliza = findPolizaById(id);
        poliza.setEstado("CANCELADA");
        poliza.getRiesgos().forEach(r -> r.setEstado("CANCELADO"));
        return poliza;
    }

    @Override
    public Riesgo agregarRiesgo(Long polizaId, Riesgo riesgo) {
        Poliza poliza = findPolizaById(polizaId);

        if (!"COLECTIVA".equals(poliza.getTipo())) {
            throw new RuntimeException("Solo pólizas colectivas pueden agregar riesgos");
        }

        if ("INDIVIDUAL".equals(poliza.getTipo()) && !poliza.getRiesgos().isEmpty()) {
            throw new RuntimeException("Una póliza individual solo puede tener 1 riesgo");
        }

        riesgo.setId(riesgoIdCounter.getAndIncrement());
        riesgo.setEstado("ACTIVO");
        riesgo.setPoliza(poliza);

        poliza.getRiesgos().add(riesgo);
        return riesgo;
    }

    @Override
    public Riesgo cancelarRiesgo(Long riesgoId) {
        Riesgo riesgo = findRiesgoById(riesgoId);
        riesgo.setEstado("CANCELADO");
        return riesgo;
    }

    @Override
    public Poliza crearPoliza(Poliza poliza) {
        poliza.setId(polizaIdCounter.getAndIncrement());
        poliza.setEstado("ACTIVA");

        // inicializa riesgos si es null
        if (poliza.getRiesgos() == null) {
            poliza.setRiesgos(new ArrayList<>());
        }

        polizas.add(poliza);
        return poliza;
    }

    private Poliza findPolizaById(Long id) {
        return polizas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Poliza no encontrada"));
    }

    private Riesgo findRiesgoById(Long id) {
        return polizas.stream()
                .flatMap(p -> p.getRiesgos().stream())
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Riesgo no encontrado"));
    }
}