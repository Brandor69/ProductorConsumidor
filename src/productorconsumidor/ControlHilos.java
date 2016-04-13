/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorconsumidor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Brandon
 */
public class ControlHilos {

    @FXML
    private ToggleButton botonIA; // boton de iniciar/apagar
    @FXML
    private ListView consumidor; // lista de lo consumido
    @FXML
    private ListView productor; // lista de lo producido
    @FXML
    private Label buffer;//muestra el buffer
    @FXML
    private Button animacion;//muestra la animacion
    @FXML
    private CheckBox mismaVelocidad;
    @FXML
    private CheckBox productorMasRap;
    @FXML
    private CheckBox consumidorMasRap;

    private Buffer bufferH;
    private Productor productorH;
    private Consumidor consumidorH;

    private ObservableList<String> listaProducido;
    private ObservableList<String> listaConsumido;

    private int tamañoBuffer;
    private int iteracionesProd;
    private int iteracionesCon;

    private int velocidadCon;
    private int velocidadPro;

    public ControlHilos() {
        super();
        tamañoBuffer = 8;
        iteracionesProd = 100;
        iteracionesCon = 100;

        listaProducido = FXCollections.observableArrayList();
        listaConsumido = FXCollections.observableArrayList();

//        productor.setItems(listaProducido);
//        consumidor.setItems(listaConsumido);
    }

    private void iniciar() {
        bufferH = new Buffer(tamañoBuffer);
        productorH = new Productor(bufferH, iteracionesProd, this, velocidadPro);
        consumidorH = new Consumidor(bufferH, iteracionesCon, this, velocidadCon);
        //inicio de hilos
        productorH.start();
        consumidorH.start();

        listaProducido.clear();
        listaConsumido.clear();
        buffer.setText("");

        productor.setItems(listaProducido);
        consumidor.setItems(listaConsumido);

    }

    public void detener() {
        productorH.stop();
        consumidorH.stop();

    }

    public void agregarProducido(int iteracion, int dato) {
        listaProducido.add(iteracion + " : Se produjo el dato: " + dato);
        mostrarBuffer();

    }

    public void agregarConsumido(int iteracion, int dato) {
        listaConsumido.add(iteracion + " : Se consumio el dato: " + dato);
        mostrarBuffer();
    }

    private void mostrarBuffer() {
        String buffer = "";
        for (int i = 0; i < bufferH.getB().length; i++) {
            buffer += bufferH.getB()[i] + " | ";
        }
        this.buffer.setText(buffer);
    }

    @FXML
    private void cambiarSeleccion() {
       
      
        if(mismaVelocidad.isFocused()){
            productorMasRap.setSelected(false);
            consumidorMasRap.setSelected(false);
            
            velocidadCon = 1000;
            velocidadPro = 1000;
        }
        else if (productorMasRap.isFocused()){
            mismaVelocidad.setSelected(false);
            consumidorMasRap.setSelected(false);
            
            
            velocidadCon = 3000;
            velocidadPro = 1000;
        }
        else if (consumidorMasRap.isFocused()){
            mismaVelocidad.setSelected(false);
            productorMasRap.setSelected(false);
            
            velocidadCon = 1000;
            velocidadPro = 3000;
        }
    }

    @FXML
    private void botonIA() {
        if (botonIA.isSelected() == false) {
            botonIA.setText("Iniciar Procesos");
            detener();
        } else {
            botonIA.setText("Detener Procesos");
            iniciar();
        }
    }
    
    @FXML
    private void abrirAnimacion(){
        try {
           	String cmd = "ejec.bat"; 
                Runtime.getRuntime().exec(cmd);
            
        } catch (IOException ex) {
            Logger.getLogger(ControlHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
