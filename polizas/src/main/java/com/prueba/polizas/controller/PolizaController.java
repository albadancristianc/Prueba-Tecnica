
package com.prueba.polizas.controller;

import com.prueba.polizas.dto.PolizaDTO;
import com.prueba.polizas.dto.PolizaMapper;
import com.prueba.polizas.entity.Poliza;
import com.prueba.polizas.entity.Riesgo;
import com.prueba.polizas.service.PolizaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
@RequiredArgsConstructor
public class PolizaController {

    private final PolizaService polizaService;

    private void validarApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("API KEY inválida");
        }
    }

    @GetMapping
    public ResponseEntity<List<PolizaDTO>> listar(
            @RequestHeader("x-api-key") String apiKey,
            @RequestParam String tipo,
            @RequestParam String estado
    ) {
        validarApiKey(apiKey);
        List<PolizaDTO> dtos = polizaService.listarPorTipoYEstado(tipo, estado).stream()
                .map(PolizaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}/riesgos")
    public ResponseEntity<List<Riesgo>> obtenerRiesgos(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id
    ) {
        validarApiKey(apiKey);
        return ResponseEntity.ok(polizaService.obtenerRiesgos(id));
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<PolizaDTO> renovar(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id
    ) {
        validarApiKey(apiKey);
        Poliza poliza = polizaService.renovarPoliza(id);
        return ResponseEntity.ok(PolizaMapper.toDTO(poliza));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<PolizaDTO> cancelar(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id
    ) {
        validarApiKey(apiKey);
        Poliza poliza = polizaService.cancelarPoliza(id);
        return ResponseEntity.ok(PolizaMapper.toDTO(poliza));
    }

    @PostMapping("/{id}/riesgos")
    public ResponseEntity<Riesgo> agregarRiesgo(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id,
            @RequestBody Riesgo riesgo
    ) {
        validarApiKey(apiKey);
        return ResponseEntity.ok(polizaService.agregarRiesgo(id, riesgo));
    }

    @PostMapping("/riesgos/{id}/cancelar")
    public ResponseEntity<Riesgo> cancelarRiesgo(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id
    ) {
        validarApiKey(apiKey);
        return ResponseEntity.ok(polizaService.cancelarRiesgo(id));
    }

    @PostMapping
    public ResponseEntity<PolizaDTO> crearPoliza(
            @RequestHeader("x-api-key") String apiKey,
            @RequestBody Poliza poliza
    ) {
        validarApiKey(apiKey);
        Poliza nueva = polizaService.crearPoliza(poliza);
        return ResponseEntity.ok(PolizaMapper.toDTO(nueva));
    }
}