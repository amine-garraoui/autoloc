package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.services.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        client.setId(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.ajouterClient(client));
    }

    @GetMapping
    public List<Client> getAll() { return clientService.consulterClients(); }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client client = clientService.consulterClientById(id);
        return client == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        if (clientService.consulterClientById(id) == null) return ResponseEntity.notFound().build();
        client.setId(id);
        return ResponseEntity.ok(clientService.modifierClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (clientService.consulterClientById(id) == null) return ResponseEntity.notFound().build();
        clientService.supprimerClientById(id);
        return ResponseEntity.noContent().build();
    }
}
