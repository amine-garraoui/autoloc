package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService contratService;

    @PostMapping
    public ResponseEntity<Contrat> create(@RequestBody Contrat contrat) {
        contrat.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.ajouterContrat(contrat));
    }

    @GetMapping
    public List<Contrat> getAll() {
        return contratService.consulterContrats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getById(@PathVariable Long id) {
        Contrat contrat = contratService.consulterContratById(id);
        return contrat == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(contrat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        if (contratService.consulterContratById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contrat.setId(id);
        return ResponseEntity.ok(contratService.modifierContrat(contrat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (contratService.consulterContratById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contratService.supprimerContratById(id);
        return ResponseEntity.noContent().build();
    }
}
