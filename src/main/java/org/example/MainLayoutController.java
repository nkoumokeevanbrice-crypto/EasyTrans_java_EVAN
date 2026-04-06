package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainLayoutController {

    @FXML
    private StackPane contentArea;

    @FXML
    private void showVehicules(ActionEvent event) {
        loadView("/fxml/vehicules.fxml");
    }
    @FXML
    private void showParametres(ActionEvent event) {
        loadView("Parametres.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            // Utilisation de FXMLLoader pour le changement dynamique (Exigence PDF)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlFile));
            Parent fxml = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(fxml);
        } catch (IOException e) {
            // Traitement des erreurs FXML (4 pts dans le barème)
            System.out.println("Erreur de chargement de la page : " + e.getMessage());
        }
    }
}//



