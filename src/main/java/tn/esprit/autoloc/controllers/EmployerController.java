package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employer;
import tn.esprit.autoloc.services.IEmployerService;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {
    private final IEmployerService employerService;

    @PostMapping
    public ResponseEntity<Employer> create(@RequestBody Employer employer) {
        employer.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(employerService.ajouterEmployer(employer));
    }

    @GetMapping
    public List<Employer> getAll() {
        return employerService.consulterEmployers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getById(@PathVariable Long id) {
        Employer employer = employerService.consulterEmployerById(id);
        return employer == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> update(@PathVariable Long id, @RequestBody Employer employer) {
        if (employerService.consulterEmployerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employer.setId(id);
        return ResponseEntity.ok(employerService.modifierEmployer(employer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (employerService.consulterEmployerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employerService.supprimerEmployerById(id);
        return ResponseEntity.noContent().build();
    }
}
