package productorconsumidor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Brandon
 */
public class Consumidor extends Thread {

    private Buffer b;
    private int iteracion;
    private ControlHilos interfaz;

    public Consumidor(Buffer b, int iteracion, ControlHilos interfaz) {
        this.b = b;
        this.iteracion = iteracion;
        this.interfaz = interfaz;
    }

    @Override
    public void run() {

        for (int i = 0; i < iteracion; i++) {
            try {
                int numero = b.extraer();
                System.out.println(i + ": Consumidor consume " + numero);
                final int ite = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        interfaz.agregarConsumido(ite, numero);
                    }
                });
                Consumidor.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

}
