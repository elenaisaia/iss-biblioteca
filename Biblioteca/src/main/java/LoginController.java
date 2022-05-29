import Model.Abonat;
import Model.Bibliotecar;
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

public class LoginController{
    private Service service;

    @FXML TextField cnpTextField;
    @FXML TextField numeTextField;
    @FXML TextField prenumeTextField;
    @FXML TextField adresaTextField;
    @FXML TextField telefonTextField;
    @FXML PasswordField parolaTextField;

    @FXML Button login_button;
    @FXML Button register_button;

    @FXML Label questionLabel;
    @FXML Button registerViewButton;
    @FXML Button loginViewButton;

    public LoginController() {
    }

    @FXML
    public void initialize() {
        this.numeTextField.setVisible(false);
        this.numeTextField.managedProperty().bind(this.numeTextField.visibleProperty());
        this.prenumeTextField.setVisible(false);
        this.prenumeTextField.managedProperty().bind(this.prenumeTextField.visibleProperty());
        this.adresaTextField.setVisible(false);
        this.adresaTextField.managedProperty().bind(this.adresaTextField.visibleProperty());
        this.telefonTextField.setVisible(false);
        this.telefonTextField.managedProperty().bind(this.telefonTextField.visibleProperty());

        this.login_button.managedProperty().bind(this.login_button.visibleProperty());
        this.register_button.setVisible(false);
        this.register_button.managedProperty().bind(this.register_button.visibleProperty());

        this.registerViewButton.managedProperty().bind(this.registerViewButton.visibleProperty());
        this.loginViewButton.setVisible(false);
        this.loginViewButton.managedProperty().bind(this.loginViewButton.visibleProperty());
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String cnp = cnpTextField.getText();
        String parola = parolaTextField.getText();

        if(cnp.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru CNP nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(!cnp.matches("[1-8][0-9]{12}")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("CNP invalid! :<\n");
            alert.showAndWait();
            return;
        }
        if(parola.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru parola nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }

        try {
            Bibliotecar persoana = new Bibliotecar(cnp, "nimic", "nimic", parola);
            System.out.println("Logging in as: " + persoana.getID());
            boolean tip = service.login(persoana);
            if(tip){
                System.out.println("Logging in as abonat...");
                Node source = (Node) event.getSource();
                Stage current = (Stage) source.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("abonat.fxml"));
                Parent root = fxmlLoader.load();
                AbonatController ctrl = fxmlLoader.getController();
                ctrl.afterLoad(this.service, persoana.getID());
                Scene scene = new Scene(root, 600, 505);
                assert persoana != null;
                current.setTitle("Logged in as - " + persoana.getID());
                current.setScene(scene);
            }
            else{
                System.out.println("Logging in as bibliotecar...");
                Node source = (Node) event.getSource();
                Stage current = (Stage) source.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("bibliotecar.fxml"));
                Parent root = fxmlLoader.load();
                BibliotecarController ctrl = fxmlLoader.getController();
                ctrl.afterLoad(this.service, persoana.getID());
                Scene scene = new Scene(root, 600, 550);
                assert persoana != null;
                current.setTitle("Logged in as - " + persoana.getID());
                current.setScene(scene);
            }
        } catch(BibliotecaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {
        String cnp = cnpTextField.getText();
        String parola = parolaTextField.getText();
        String nume = numeTextField.getText();
        String prenume = prenumeTextField.getText();
        String adresa = adresaTextField.getText();
        String telefon = telefonTextField.getText();
        String cod = "1111";

        if(cnp.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru CNP nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(!cnp.matches("[1-8][0-9]{12}")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("CNP invalid! :<\n");
            alert.showAndWait();
            return;
        }
        if(parola.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru parola nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(nume.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru nume nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(prenume.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru prenume nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(adresa.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru adresa nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(telefon.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Campul pentru telefon nu poate fi gol! :<\n");
            alert.showAndWait();
            return;
        }
        if(!telefon.matches("0[0-9]{9}")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Telefon invalid! :<\n");
            alert.showAndWait();
            return;
        }

        try {
            Abonat abonat = new Abonat(cnp, nume, prenume, adresa, telefon, Integer.parseInt(cod), parola);
            System.out.println("Logging in as: " + abonat);
            service.register(abonat);
            Node source = (Node) event.getSource();
            Stage current = (Stage) source.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("abonat.fxml"));
            Parent root = fxmlLoader.load();
            AbonatController ctrl = fxmlLoader.getController();
            ctrl.afterLoad(this.service, abonat.getID());
            Scene scene = new Scene(root, 600, 505);
            assert abonat != null;
            current.setTitle("Logged in as - " + abonat.getPrenume() + " " + abonat.getNume());
            current.setScene(scene);
        } catch(BibliotecaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchRegister() {
        this.numeTextField.setVisible(true);
        this.prenumeTextField.setVisible(true);
        this.adresaTextField.setVisible(true);
        this.telefonTextField.setVisible(true);
        this.login_button.setVisible(false);
        this.register_button.setVisible(true);

        this.questionLabel.setText("Already have an account?");
        this.registerViewButton.setVisible(false);
        this.loginViewButton.setVisible(true);
    }

    public void switchLogin() {
        this.numeTextField.setVisible(false);
        this.prenumeTextField.setVisible(false);
        this.adresaTextField.setVisible(false);
        this.telefonTextField.setVisible(false);
        this.login_button.setVisible(true);
        this.register_button.setVisible(false);

        this.questionLabel.setText("No account?");
        this.registerViewButton.setVisible(true);
        this.loginViewButton.setVisible(false);
    }

    public void setService(Service service) {
        this.service = service;
    }
}