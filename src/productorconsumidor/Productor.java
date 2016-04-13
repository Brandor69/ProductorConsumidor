package productorconsumidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Brandon
 */
public class Productor extends Thread {

    private Random r = new Random();
    private Buffer b;
    private int iteracion;
    private ControlHilos interfaz;
    private int velocidad;

    public Productor(Buffer b, int iteraciones, ControlHilos interfaz,int velocidad) {

        this.b = b;
        this.iteracion = iteraciones;
        this.interfaz = interfaz;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        for (int i = 0; i < iteracion; i++) {
            try {
                int numero = r.nextInt(100);
                System.out.println(i + ": Productor produce " + numero);
                final int ite = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        interfaz.agregarProducido(ite, numero);
                    }
                });
                b.ingresar(numero);
                Productor.sleep(velocidad);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

}
