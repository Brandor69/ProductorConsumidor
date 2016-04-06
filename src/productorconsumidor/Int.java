/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorconsumidor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Brandon
 */
public class Int extends Application {
    private ControlHilos hilos;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Int.class.getResource("Interfaz.fxml"));
            AnchorPane raiz = (AnchorPane) loader.load();

            //define la scene principal
            Scene scene = new Scene(raiz);           
            primaryStage.setScene(scene);
            primaryStage.setTitle("Productor-Consumidor");
            primaryStage.show();

            //carga la clase controladora del Quantum
            hilos = loader.getController();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Int.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void stop(){
        hilos.detener();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
