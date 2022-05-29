import Model.Carte;
import Model.Imprumut;
import Model.Stare;
import Model.Status;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecarController {
    @FXML TableView allCartiTableView;
    @FXML TableView imprumuturiTableView;
    @FXML TextField autorTextField;
    @FXML TextField titluTextField;
    @FXML TextField codTextField;
    @FXML TextField stareTextField;

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
            this.updateImprumuturi();
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
            List<Carte> carti = service.findAllCarti();
            System.out.println("carti: " + carti);
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

    private void updateImprumuturi() throws BibliotecaException {
        try {
            List<Imprumut> imprumuturi = service.findImprumuturi();
            System.out.println("imprumuturi: " + imprumuturi);
            imprumuturiTableView.getItems().clear();
            for(Imprumut imprumut : imprumuturi){
                imprumuturiTableView.getItems().add(imprumut);
                System.out.println(imprumut);
            }
//            allCartiTableView.getItems().addAll(imprumuturi);

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
    public void onRestituireButtonClick(ActionEvent event) {
        Imprumut imprumut = (Imprumut) imprumuturiTableView.getSelectionModel().getSelectedItem();
        if(imprumut == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("iss");
            alert.setHeaderText("Restituire imprumut failure");
            alert.setContentText("Please select an imprumut first! :<");
            alert.showAndWait();
            return;
        }

        service.updateImprumut(imprumut);
        try {
            updateImprumuturi();
        } catch (Exception e) {
            System.out.println("Urat la updateImprumuturi dupa restituire :<");
        }
    }

    @FXML
    public void onAdaugareButtonClick(ActionEvent event) {
        String titlu = titluTextField.getText();
        if(titlu.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru titlu nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }

        String autor = autorTextField.getText();
        if(autor.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru autor nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }

        String codStr = codTextField.getText();
        Integer cod;
        try {
            cod = Integer.parseInt(codStr);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru cod trebuie sa fie un numar! :<\n");
            alert.showAndWait();
            return;
        }

        String stare = stareTextField.getText();
        if(!stare.equals("noua") && !stare.equals("buna") && !stare.equals("satisfacatoare") && !stare.equals("rea")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Starea poate fi numai noua, buna, satisfacatoar sau rea! :<\n");
            alert.showAndWait();
            return;
        }

        Carte carte = new Carte(cod, titlu, autor, Stare.getStare(stare));
        service.addCarte(carte);
        try {
            updateAllCarti();
        } catch (Exception e) {
            System.out.println("Urat la updateAll dupa add :<");
        }
    }

    @FXML
    public void onModificareButtonClick(ActionEvent event) {
        Carte carte = (Carte) allCartiTableView.getSelectionModel().getSelectedItem();

        if(carte == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("iss");
            alert.setHeaderText("Updating carte failure");
            alert.setContentText("Please select a book first! :<");
            alert.showAndWait();
            return;
        }

        String stare = stareTextField.getText();
        if(!stare.equals("") && !stare.equals("noua") && !stare.equals("buna") && !stare.equals("satisfacatoare") && !stare.equals("rea")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Starea poate fi numai noua, buna, satisfacatoar sau rea! :<\n");
            alert.showAndWait();
            return;
        }
        if(!stare.equals("")){
            carte.setStare(Stare.getStare(stare));
        }

        String titlu = titluTextField.getText();
        if(!titlu.equals("")){
            carte.setTitlu(titlu);
        }

        String autor = autorTextField.getText();
        if(!autor.equals("")){
            carte.setAutor(autor);
        }

        service.modifyCarte(carte);
        try {
            updateAllCarti();
        } catch (Exception e) {
            System.out.println("Urat la updateAll dupa modify :<");
        }
    }

    @FXML
    public void onStergereButtonClick(ActionEvent event) {
        Carte carte = (Carte) allCartiTableView.getSelectionModel().getSelectedItem();

        if(carte == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("iss");
            alert.setHeaderText("Updating carte failure");
            alert.setContentText("Please select a book first! :<");
            alert.showAndWait();
            return;
        }

        service.deleteCarte(carte);
        try {
            updateAllCarti();
        } catch (Exception e) {
            System.out.println("Urat la updateAll dupa delete :<");
        }
    }
}
