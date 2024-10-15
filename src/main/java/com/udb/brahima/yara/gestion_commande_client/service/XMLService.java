package com.udb.brahima.yara.gestion_commande_client.service;

import com.udb.brahima.yara.gestion_commande_client.model.Commandes;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class XMLService {
    private static final String FILE_PATH = "commandes.xml";
    /**
     * Verifie si le fichier XML existe, sinon on cree un nouveau.
     * ou charge les donnes des commandes depuis le fichier XML.
     *@return une instance de {@link Commandes} ou null en cas d'erreur
     */
    public Commandes load() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()){
                System.out.println(
                        "Fichier commandes.xml introuvable. Creation d'un nouveau fichier...");
               createXMLFile();
             }
            JAXBContext jaxbContext = JAXBContext.newInstance(Commandes.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Commandes) unmarshaller.unmarshal(file);
     }catch (IOException | JAXBException e ) {
         e.printStackTrace();
         throw new RuntimeException(
                 "Erreur lors du chargement des données depuis le fichier XML", e );
        }
    }
     /**
      * Enregistrer les donnees de commndes dans le fichier XML.
      * @param commandes les donnees a enregistrer (on passe la paramtre commandes a la methodes save ) */
    public void save(Commandes commandes){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Commandes.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            //Formatage pour une sortie lisible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            //Enregistrer dans le fichier
            marshaller.marshal(commandes,new File(FILE_PATH));
        }catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Erreur lors de l'enregistremnt des données dans le fichier XML", e);
        }
    }
    /**
     * Cree un fichier XML avec une strucutre vide pour les commandes.
     */
    public void createXMLFile()throws JAXBException, IOException{
        Commandes commandes = new Commandes();
        save(commandes);
    }
}
