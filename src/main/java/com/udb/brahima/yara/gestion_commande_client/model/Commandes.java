package com.udb.brahima.yara.gestion_commande_client.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "commandes")
public class Commandes {
   private List<Commande> commandes;

    public Commandes() {
        this.commandes = new ArrayList<>();
    }

    @XmlElement(name = "commande")
    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
