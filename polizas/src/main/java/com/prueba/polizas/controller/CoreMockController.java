package com.prueba.polizas.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-mock")
@Slf4j
public class CoreMockController {

    @PostMapping("/evento")
    public String recibirEvento(@RequestBody EventoRequest request) {

        log.info("Evento recibido en CORE MOCK: {} para poliza {}",
                request.getEvento(),
                request.getPolizaId());

        return "Evento registrado en logs";
    }

    @Data
    static class EventoRequest {
        private String evento;
        private Long polizaId;
    }
}