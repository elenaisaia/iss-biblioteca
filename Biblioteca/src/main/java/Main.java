import Repository.*;
import Services.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        BibliotecarRepositoryInterface bibliotecarRepository = new BibliotecarRepository(props);
        AbonatRepositoryInterface abonatRepository = new AbonatRepository(props);
        CarteRepositoryInterface carteRepository = new CarteRepository(props);
        ImprumutRepositoryInterface imprumutRepository = new ImprumutRepository(props);

        Service service = new Service(bibliotecarRepository, abonatRepository, carteRepository, imprumutRepository);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);

        primaryStage.setTitle("Login terminal");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
