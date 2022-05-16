import Model.Carte;
import Services.BibliotecaException;
import Services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbonatController {
    @FXML TableView allCartiTableView;
    @FXML TableView filteredCartiTableView;
    @FXML TextField autorTextField;
    @FXML TextField titluTextField;

    @FXML Button cautaButton;
    @FXML Button adaugaButton;
    @FXML Button finalizeazaButton;
    @FXML Button logoutButton;

    private String abonatCrt;
    private Service service;

    public AbonatController() {
    }

    @FXML
    public void initialize(){
        this.titluTextField.setVisible(true);
        this.titluTextField.managedProperty().bind(this.titluTextField.visibleProperty());
        this.autorTextField.setVisible(true);
        this.autorTextField.managedProperty().bind(this.autorTextField.visibleProperty());

        this.logoutButton.setVisible(true);
        this.logoutButton.managedProperty().bind(this.logoutButton.visibleProperty());
        this.cautaButton.setVisible(true);
        this.cautaButton.managedProperty().bind(this.cautaButton.visibleProperty());
        this.adaugaButton.setVisible(true);
        this.adaugaButton.managedProperty().bind(this.adaugaButton.visibleProperty());
        this.finalizeazaButton.setVisible(true);
        this.finalizeazaButton.managedProperty().bind(this.finalizeazaButton.visibleProperty());
    }

    public void setService(Service service) {
        this.service = service;
    }
    private void setCurrentAbonat(String cnp) {
        this.abonatCrt = cnp;
    }

    public void afterLoad(Service service, String cnp) {
        this.setService(service);
        this.setCurrentAbonat(cnp);

        try {
            this.updateAllCarti();
        } catch (Exception e) {
            System.out.println("Urat la afterLoad :<");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("iss");
            alert.setHeaderText("Loading carti failure");
            alert.setContentText("Unable to load carti");
            alert.showAndWait();
        }
    }

    private void updateAllCarti() throws BibliotecaException {
        try {
            List<Carte> carti = service.findCartiByStatus(false);
            System.out.println("carti disponibile: " + carti);
            allCartiTableView.getItems().clear();
            for(Carte carte : carti){
                allCartiTableView.getItems().add(carte);
                System.out.println(carte);
            }
//            allCartiTableView.getItems().addAll(carti);

        } catch (Exception e) {
            System.out.println("Urat la updateAll :<");
            e.printStackTrace();
            throw new BibliotecaException("Urat la updateAll :<");
        }
    }

    @FXML
    public void onLogoutButtonClick(ActionEvent event) {
        try {
            System.out.println("Logging out...");
            Node source = (Node) event.getSource();
            Stage current = (Stage) source.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            LoginController loginController = fxmlLoader.getController();
            loginController.setService(service);
            Scene scene = new Scene(root, 400, 500);
            current.setTitle("Login terminal");
            current.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCautaButtonClick(ActionEvent event) {
        String titlu = titluTextField.getText();
        String autor = autorTextField.getText();
        if (titlu.equals("") && autor.equals("")) {
            return;
        }
        List<Carte> carti = new ArrayList<>();

        if (autor.equals("")) {
            try {
                carti = service.findCartiByTitluStatus(titlu, false);
            } catch (Exception e) {
                System.out.println("Urat la filter by titlu! :<");
            }
        }
        else if (titlu.equals("")) {
            try {
                carti = service.findCartiByAutorStatus(autor, false);
            } catch (Exception e) {
                System.out.println("Urat la filter by autor! :<");
            }
        }
        else {
            try {
                carti = service.findCartiByTitluAutorStatus(titlu, autor, false);
            } catch (Exception e) {
                System.out.println("Urat la filter by titlu si autor! :<");
            }
        }

        if (carti.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Carti indisponibile! :<");
            alert.showAndWait();
            return;
        }
        filteredCartiTableView.getItems().clear();
        for(Carte carte : carti){
            filteredCartiTableView.getItems().add(carte);
        }
    }

    @FXML
    public void onAdaugaButtonClick(ActionEvent event) {
    }

    @FXML
    public void onFinalizeazaButtonClick(ActionEvent event) {
    }
}
