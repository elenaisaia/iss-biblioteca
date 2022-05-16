import Repository.*;
import Services.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Main extends Application {
    private static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        initialize();

        BibliotecarRepositoryInterface bibliotecarRepository = new BibliotecarRepository(props);
        AbonatRepositoryInterface abonatRepository = new AbonatRepository(props);
//        CarteRepositoryInterface carteRepository = new CarteRepository(props);
        CarteRepositoryInterface carteRepository = new CarteRepositoryORM(sessionFactory);
        ImprumutRepositoryInterface imprumutRepository = new ImprumutRepository(props);

        Service service = new Service(bibliotecarRepository, abonatRepository, carteRepository, imprumutRepository);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);

        primaryStage.setTitle("Login terminal");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();

        //close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
