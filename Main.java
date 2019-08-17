/***package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.cert.CertPathValidatorException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Group botones = new Group();
        //Scene scene = new Scene(botones,1280,720);
        primaryStage.setTitle("Extraclase 1");
        //primaryStage.setScene(scene);
       // Button boton = new Button("Cargar CSV");
        //boton.setDefaultButton(true);
        //boton.setPrefSize(100,50);
        //boton.setLayoutX(610);
        //boton.setLayoutY(500);
        //botones.getChildren().add(boton);
        primaryStage.show();
        class botones implements Initializable{
            private Button btnBuscar;

            private Label txtMostrar;
            private Object Stage;
            Group botones = new Group();
            Scene scene = new Scene(botones,1280,720);
            Button btnBuscar = new Button("Cargar CSV");
            primaryStage.setScene(scene);
            btnBuscar.setDefaultButton(true);
            btnBuscar.setPrefSize(100,50);
            btnBuscar.setLayoutX(610);
            btnBuscar.setLayoutY(500);
            botones.getChildren().add(btnBuscar);

            @Override
            public void initialize(URL url, ResourceBundle rb) {
                btnBuscar.setOnAction(actionEvent -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Seleccione el archivo CSV");

                    File fileName = fileChooser.showOpenDialog((javafx.stage.Window) Stage);
                    if (fileName != null){
                        File file = new File("datos.csv");
                        txtMostrar.setText(String.valueOf(file));
                    }});
            }
        }



        static void cargar(Stage primaryStage){

            String fileName = "datos.csv";
            File file = new File(fileName);
            try {
                Scanner inputStream = new Scanner(file);
                while (inputStream.hasNext()) {
                    String data = inputStream.next();
                    System.out.println(data);
                }
                inputStream.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
***/
