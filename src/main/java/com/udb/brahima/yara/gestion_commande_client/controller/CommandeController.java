package com.udb.brahima.yara.gestion_commande_client.controller;

import com.udb.brahima.yara.gestion_commande_client.model.Commande;
import com.udb.brahima.yara.gestion_commande_client.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {
    private final CommandeService commandeService;
    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return new ResponseEntity<>(commandeService.getAllCommandes(), HttpStatus.OK);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Commande> getCommandeByNumero(@PathVariable String numero) {
        Commande commande = commandeService.getCommandeByNumero(numero);
        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> ajouterCommande(@RequestBody Commande commande) {
        commandeService.addCommande(commande);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable String numero) {
        Commande commande = commandeService.getCommandeByNumero(numero);
        if (commande != null) {
            commandeService.deleteCommande(numero);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Commande>> getCommandesByClient(@PathVariable String clientId) {
        return new ResponseEntity<>(commandeService.getCommandesByClient(clientId), HttpStatus.OK);
    }

    @GetMapping("/payees")
    public ResponseEntity<List<Commande>> getCommandesPayees() {
        return new ResponseEntity<>(commandeService.getCommandesPayees(), HttpStatus.OK);
    }

    @GetMapping("/nonpayees")
    public ResponseEntity<List<Commande>> getCommandesNonPayees() {
        return new ResponseEntity<>(commandeService.getCommandesNonPayees(), HttpStatus.OK);
    }

    @PutMapping("/payer/{numero}")
    public ResponseEntity<Void> payerCommande(@PathVariable String numero) {
        Commande commande = commandeService.getCommandeByNumero(numero);
        if (commande != null) {
            commandeService.payerCommande(numero);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
