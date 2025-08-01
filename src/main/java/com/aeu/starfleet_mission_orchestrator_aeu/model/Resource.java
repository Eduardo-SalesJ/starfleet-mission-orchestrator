package com.aeu.starfleet_mission_orchestrator_aeu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ex: Combustivel, Suprimentos, Municao

    @Column(nullable = false)
    private String unitOfmeasurement; // Ex: Litros, Unidades, Disparos
}
