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

public class BibliotecarController {
    @FXML TableView allCartiTableView;
    @FXML TextField autorTextField;
    @FXML TextField titluTextField;
    @FXML TextField codTextField;

    @FXML Button restituireButton;
    @FXML Button adaugareButton;
    @FXML Button modificareButton;
    @FXML Button stergereButton;
    @FXML Button logoutButton;

    private String bibliotecarCrt;
    private Service service;

    public BibliotecarController() {
    }

    @FXML
    public void initialize(){
        this.titluTextField.setVisible(true);
        this.titluTextField.managedProperty().bind(this.titluTextField.visibleProperty());
        this.autorTextField.setVisible(true);
        this.autorTextField.managedProperty().bind(this.autorTextField.visibleProperty());
        this.codTextField.setVisible(true);
        this.codTextField.managedProperty().bind(this.codTextField.visibleProperty());

        this.logoutButton.setVisible(true);
        this.logoutButton.managedProperty().bind(this.logoutButton.visibleProperty());
        this.restituireButton.setVisible(true);
        this.restituireButton.managedProperty().bind(this.restituireButton.visibleProperty());
        this.adaugareButton.setVisible(true);
        this.adaugareButton.managedProperty().bind(this.adaugareButton.visibleProperty());
        this.modificareButton.setVisible(true);
        this.modificareButton.managedProperty().bind(this.modificareButton.visibleProperty());
        this.stergereButton.setVisible(true);
        this.stergereButton.managedProperty().bind(this.stergereButton.visibleProperty());
    }

    public void setService(Service service) {
        this.service = service;
    }
    private void setCurrentBibliotecar(String cnp) {
        this.bibliotecarCrt = cnp;
    }

    public void afterLoad(Service service, String cnp) {
        this.setService(service);
        this.setCurrentBibliotecar(cnp);

        try {
            this.updateAllCarti();
        } catch (Exception e) {
            System.out.println("Urat la afterLoad :<");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("mpp");
            alert.setHeaderText("Loading shows failure");
            alert.setContentText("Unable to load shows");
            alert.showAndWait();
        }
    }

    private void updateAllCarti() throws BibliotecaException {
        try {
            List<Carte> carti = service.findAllCarti();
            allCartiTableView.getItems().clear();
            for(Carte carte : carti){
                allCartiTableView.getItems().add(carte);
                System.out.println(carte);
            }
//            allCartiTableView.getItems().addAll(carti);

        } catch (Exception e) {
            System.out.println("Urat la updateAll :<");
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
    public void onRestituireButtonClick(ActionEvent event) {
    }

    @FXML
    public void onAdaugareButtonClick(ActionEvent event) {
    }

    @FXML
    public void onModificareButtonClick(ActionEvent event) {
    }

    @FXML
    public void onStergereButtonClick(ActionEvent event) {
    }
}
