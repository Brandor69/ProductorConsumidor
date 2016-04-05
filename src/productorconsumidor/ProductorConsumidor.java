package productorconsumidor;
public class ProductorConsumidor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Productor productor = new Productor(buffer,100);
        Consumidor consumidor = new Consumidor(buffer, 100);
        productor.start();
        consumidor.start();
    }
    
}
