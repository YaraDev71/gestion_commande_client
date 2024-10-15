package com.udb.brahima.yara.gestion_commande_client.service;

import com.udb.brahima.yara.gestion_commande_client.model.Commande;
import com.udb.brahima.yara.gestion_commande_client.model.Commandes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommandeServiceImpl implements CommandeService {

    private final XMLService xmlService ;

    @Autowired
    public CommandeServiceImpl(XMLService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public void addCommande (Commande commande) {
        Commandes commandes = xmlService.load();
        commandes.getCommandes().add(commande);
        xmlService.save(commandes);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return xmlService.load().getCommandes();
    }

    @Override
    public Commande getCommandeByNumero(String numero) {
        return xmlService.load().getCommandes().stream()
                .filter(commande -> commande.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void getCommandesByClient(String clientId) {
         xmlService.load().getCommandes().stream()
                .filter(commande -> commande.getClient().getId().equals(clientId))
                .toList();
    }

    @Override
    public void deleteCommande(String numero) {
        Commandes commandes = xmlService.load();  // Charger les commandes existantes
        commandes.getCommandes().removeIf(commande -> commande.getNumero().equals(numero));  // Supprimer la commande par son numéro
        xmlService.save(commandes);  // Sauvegarder les changements//
    }
    @Override
    public List<Commande> getCommandesPayees() {
        return xmlService.load().getCommandes().stream()
                .filter(Commande::isPayee)  // Filtrer les commandes payées
                .toList();    }

    @Override
    public List<Commande> getCommandesNonPayees() {
        return xmlService.load().getCommandes().stream()
                .filter(commande -> !commande.isPayee())  // Filtrer les commandes non payées
                .toList();    }

    @Override
    public void payerCommande(String numero) {
        Commandes commandes = xmlService.load();
        commandes.getCommandes().stream()
                .filter(commande -> commande.getNumero().equals(numero))
                .forEach(commande -> commande.setPayee(true));  // Marquer la commande comme payée
        xmlService.save(commandes);  // Sauvegarder les modifications

    }
}
