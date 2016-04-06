package productorconsumidor;
import java.util.concurrent.*;

/**
 *
 * @author Brandon
 */


public class Buffer {
    private int[]b;
    private int i = 0, j=0;
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore hayDatos = new Semaphore(0,true);
    private Semaphore hayEspacio;
    
    public Buffer(int tam){
        b = new int[tam];
        hayEspacio = new Semaphore(b.length,true);
        
    }

    public int[] getB() {
        return b;
    }
    
    
    public void ingresar(int dato) throws InterruptedException{
        hayEspacio.acquire();
        mutex.acquire();
        b[i] = dato;
        //System.out.println("Productor Produce:"+dato);
        i = (i+1)% b.length;
        mutex.release();
        hayDatos.release();
    
    }
    
    public int extraer() throws InterruptedException{
        
        hayDatos.acquire();
        mutex.acquire();
        int aux = j;
        j = (j+1)%b.length;
        //System.out.println("Consumidor Consume:"+b[aux]);
        mutex.release();
        hayEspacio.release();
        int dato = b[aux];
        b[aux] = 0;
        return dato;
        
    }
}
