package com.udb.brahima.yara.gestion_commande_client.service;

import com.udb.brahima.yara.gestion_commande_client.model.Commande;

import java.util.List;

public interface CommandeService {

    void addCommande (Commande commande);
    List<Commande> getAllCommandes();
    Commande getCommandeByNumero(String numero);
    void getCommandesByClient(String clientId);
    void deleteCommande(String numero);
    List<Commande> getCommandesPayees();
    List<Commande> getCommandesNonPayees();
    void payerCommande(String numero);


}
