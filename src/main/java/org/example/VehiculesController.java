package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VehiculesController {

    // Éléments du tableau
    @FXML private TableView<Vehicule> tableVehicules;
    @FXML private TableColumn<Vehicule, Integer> col_id;
    @FXML private TableColumn<Vehicule, String> col_matricule;
    @FXML private TableColumn<Vehicule, String> col_modele;
    @FXML private TableColumn<Vehicule, String> col_statut;

    // Éléments de saisie
    @FXML private TextField txtMatricule;
    @FXML private ComboBox<String> comboCategorie;

    private ObservableList<Vehicule> vehiculeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. Remplir la liste déroulante
        if (comboCategorie != null) {
            comboCategorie.setItems(FXCollections.observableArrayList("Poids lourd", "Léger", "Utilitaire"));
        }

        // 2. Configurer les colonnes du tableau
        if (tableVehicules != null) {
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            col_modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
            col_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));

            // 3. Charger les données depuis MySQL
            loadData();
        }
    }

    // --- CHARGER LES DONNÉES ---
    private void loadData() {
        vehiculeList.clear();
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                String query = "SELECT id, matricule, modele, statut FROM vehicules";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    vehiculeList.add(new Vehicule(
                            rs.getInt("id"),
                            rs.getString("matricule"),
                            rs.getString("modele"),
                            rs.getString("statut")
                    ));
                }
                tableVehicules.setItems(vehiculeList);
            }
        } catch (Exception e) {
            System.err.println("Erreur de chargement : " + e.getMessage());
        }
    }

    // --- AJOUTER UN VÉHICULE ---
    @FXML
    private void ajouterVehicule() {
        String matricule = (txtMatricule != null) ? txtMatricule.getText().trim() : "";
        String modele = (comboCategorie != null) ? comboCategorie.getValue() : null;
        String statut = "Disponible";

        System.out.println("Tentative d'ajout -> Matricule: [" + matricule + "], Modèle: [" + modele + "]");

        if (matricule.isEmpty() || modele == null) {
            System.err.println("ERREUR : Champs vides !");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                String query = "INSERT INTO vehicules (matricule, modele, statut) VALUES ('" + matricule + "', '" + modele + "', '" + statut + "')";
                Statement st = conn.createStatement();
                st.executeUpdate(query);

                System.out.println("SUCCÈS : Véhicule ajouté !");
                txtMatricule.clear();
                loadData(); // Rafraîchit le tableau
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- SUPPRIMER UN VÉHICULE ---
    @FXML
    private void supprimerVehicule() {
        // On récupère la ligne sélectionnée dans le tableau
        Vehicule selection = tableVehicules.getSelectionModel().getSelectedItem();

        if (selection == null) {
            System.out.println("Veuillez sélectionner un véhicule dans le tableau !");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                // On supprime en utilisant l'ID unique du véhicule
                String query = "DELETE FROM vehicules WHERE id = " + selection.getId();
                Statement st = conn.createStatement();
                st.executeUpdate(query);

                System.out.println("SUCCÈS : Véhicule supprimé !");
                loadData(); // Rafraîchit le tableau
            }
        } catch (Exception e) {
            System.err.println("Erreur de suppression : " + e.getMessage());
        }
    }
}
