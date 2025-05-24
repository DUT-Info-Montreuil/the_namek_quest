package universite_paris8.iut.fan.the_namek_quest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/terrain-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 900);
        stage.setTitle("The Namek Quest!");
        stage.setScene(scene);
        scene.getRoot().requestFocus();
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}
