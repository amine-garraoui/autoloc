package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.services.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService agenceService;

    @PostMapping
    public ResponseEntity<Agence> create(@RequestBody Agence agence) {
        agence.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.ajouterAgence(agence));
    }

    @GetMapping
    public List<Agence> getAll() {
        return agenceService.consulterAgences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getById(@PathVariable Long id) {
        Agence agence = agenceService.consulterAgenceById(id);
        return agence == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(agence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence agence) {
        if (agenceService.consulterAgenceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agence.setId(id);
        return ResponseEntity.ok(agenceService.modifierAgence(agence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (agenceService.consulterAgenceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agenceService.supprimerAgenceById(id);
        return ResponseEntity.noContent().build();
    }
}
