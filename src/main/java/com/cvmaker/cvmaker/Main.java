package com.cvmaker.cvmaker;

import com.cvmaker.cvmaker.database.Database;
import com.cvmaker.cvmaker.utils.ResumeTemplate;
import com.cvmaker.cvmaker.utils.TwoPagerOfficialResume;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public final static boolean IS_DEV = true;
    @Override
    public void start(Stage stage) throws IOException {

        Database db = new Database(); // default connection credentials

        if (db.ping()){
            System.out.println("PONG");
        }
        else {
            System.out.println("ERROR");
        }

        ResumeTemplate rt = new TwoPagerOfficialResume();
        try {
            rt.getTemplate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}