package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Vehicule {
    private final IntegerProperty id;
    private final StringProperty matricule;
    private final StringProperty modele;
    private final StringProperty statut;

    // Constructeur
    public Vehicule(int id, String matricule, String modele, String statut) {
        this.id = new SimpleIntegerProperty(id);
        this.matricule = new SimpleStringProperty(matricule);
        this.modele = new SimpleStringProperty(modele);
        this.statut = new SimpleStringProperty(statut);
    }

    // --- GETTERS (Très importants pour l'affichage) ---
    public int getId() { return id.get(); }
    public String getMatricule() { return matricule.get(); }
    public String getModele() { return modele.get(); }
    public String getStatut() { return statut.get(); }

    // --- PROPERTIES (Pour la liaison JavaFX) ---
    public IntegerProperty idProperty() { return id; }
    public StringProperty matriculeProperty() { return matricule; }
    public StringProperty modeleProperty() { return modele; }
    public StringProperty statutProperty() { return statut; }
}

