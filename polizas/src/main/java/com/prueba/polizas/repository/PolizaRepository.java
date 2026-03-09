package com.prueba.polizas.repository;

import com.prueba.polizas.entity.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    List<Poliza> findByTipoAndEstado(String tipo, String estado);

}