package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.services.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance) {
        maintenance.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.ajouterMaintenance(maintenance));
    }

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.consulterMaintenances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.consulterMaintenanceById(id);
        return maintenance == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(maintenance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        if (maintenanceService.consulterMaintenanceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenance.setId(id);
        return ResponseEntity.ok(maintenanceService.modifierMaintenance(maintenance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (maintenanceService.consulterMaintenanceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenanceService.supprimerMaintenanceById(id);
        return ResponseEntity.noContent().build();
    }
}
