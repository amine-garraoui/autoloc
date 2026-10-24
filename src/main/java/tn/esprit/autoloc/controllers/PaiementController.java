package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.services.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService paiementService;

    @PostMapping
    public ResponseEntity<Paiement> create(@RequestBody Paiement paiement) {
        paiement.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.ajouterPaiement(paiement));
    }

    @GetMapping
    public List<Paiement> getAll() {
        return paiementService.consulterPaiements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getById(@PathVariable Long id) {
        Paiement paiement = paiementService.consulterPaiementById(id);
        return paiement == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(paiement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(@PathVariable Long id, @RequestBody Paiement paiement) {
        if (paiementService.consulterPaiementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiement.setId(id);
        return ResponseEntity.ok(paiementService.modifierPaiement(paiement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (paiementService.consulterPaiementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiementService.supprimerPaiementById(id);
        return ResponseEntity.noContent().build();
    }
}
