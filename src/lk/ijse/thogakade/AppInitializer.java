package lk.ijse.thogakade;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = this.getClass().getResource("/lk/ijse/thogakade/view/DashboardForm.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard Form");
        primaryStage.centerOnScreen();

        primaryStage.show();


        Session session = SessionFactoryConfiguration.getInstance().getSession();
        session.save(new Customer("C003","DilanMal","Galle",20000));
        System.out.println(session.get(Customer.class,"C001"));
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }
}
