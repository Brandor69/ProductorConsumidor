
package productorconsumidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brandon
 */
public class Productor extends Thread{
    private Random r = new Random();
    private Buffer b;
    private int iteracion;
    
    public Productor(Buffer b, int iteraciones){
    
        this.b = b;
        this.iteracion = iteraciones;
    }
    
    @Override
    public void run(){
        for(int i = 0; i < iteracion; i++){
            try {
                int numero = r.nextInt(100);
                System.out.println(i+": Productor Produce "+numero);
                b.ingresar(numero);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        
        }
    
    }
    
    
}
