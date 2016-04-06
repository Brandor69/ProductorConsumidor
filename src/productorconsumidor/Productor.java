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

    public Productor(Buffer b, int iteraciones, ControlHilos interfaz) {

        this.b = b;
        this.iteracion = iteraciones;
        this.interfaz = interfaz;
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
                Productor.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

}
