package com.prueba.polizas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String estado; // ACTIVO o CANCELADO

    @ManyToOne
    @JoinColumn(name = "poliza_id")
    private Poliza poliza;
}