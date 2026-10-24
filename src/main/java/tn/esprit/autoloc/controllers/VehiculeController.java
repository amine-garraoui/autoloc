package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.services.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule vehicule) {
        vehicule.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.ajouterVehicule(vehicule));
    }

    @GetMapping
    public List<Vehicule> getAll() {
        return vehiculeService.consulterVehicules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.consulterVehiculeById(id);
        return vehicule == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(vehicule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        if (vehiculeService.consulterVehiculeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehicule.setId(id);
        return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (vehiculeService.consulterVehiculeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.supprimerVehiculeById(id);
        return ResponseEntity.noContent().build();
    }
}
