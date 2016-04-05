package productorconsumidor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class Consumidor extends Thread{
    private Buffer b;
    private int iteracion;
    
    public Consumidor(Buffer b, int iteracion){
        this.b = b;
        this.iteracion = iteracion;
    }
    @Override
    public void run(){
    
        for(int i = 0; i<iteracion; i++){
            try {
                int numero = b.extraer();
                System.out.println(i+": Consumidor Consume "+numero);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        
        }
    
    }
    
    
}
