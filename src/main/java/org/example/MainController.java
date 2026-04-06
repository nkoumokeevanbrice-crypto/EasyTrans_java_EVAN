package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    @FXML private StackPane contentArea;

    @FXML
    public void initialize() {
        // Charge le dashboard au démarrage
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        loadPage("dashboard.fxml");
    }

    @FXML
    private void showVehicules() {
        loadPage("vehicules.fxml");
    }

    @FXML
    private void showParametres() {
        loadPage("parametres.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            // Chemin sécurisé : cherche dans le dossier fxml des resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            Parent root = loader.load();

            if (contentArea != null) {
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            }
        } catch (Exception e) {
            System.err.println("ERREUR : Impossible de trouver /fxml/" + fxmlFile);
            e.printStackTrace();
        }
    }
}

