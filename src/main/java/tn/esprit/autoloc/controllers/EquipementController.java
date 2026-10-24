package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.services.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService equipementService;

    @PostMapping
    public ResponseEntity<Equipement> create(@RequestBody Equipement equipement) {
        equipement.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.ajouterEquipement(equipement));
    }

    @GetMapping
    public List<Equipement> getAll() {
        return equipementService.consulterEquipements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getById(@PathVariable Long id) {
        Equipement equipement = equipementService.consulterEquipementById(id);
        return equipement == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(equipement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> update(@PathVariable Long id, @RequestBody Equipement equipement) {
        if (equipementService.consulterEquipementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipement.setId(id);
        return ResponseEntity.ok(equipementService.modifierEquipement(equipement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (equipementService.consulterEquipementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipementService.supprimerEquipementById(id);
        return ResponseEntity.noContent().build();
    }
}
