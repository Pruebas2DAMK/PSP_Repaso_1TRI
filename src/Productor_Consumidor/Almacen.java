package Productor_Consumidor;
/*
* definimos un limite máximo de productos a almacenar
* y declararemos los 3 semáforos, el productor, el consumidor y un mutex.
* */

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Almacen {

    private final int MAX_LIMITE = 20;      //limite máx de productos a almacenar
    private int producto = 0;
    private Semaphore productor = new Semaphore(MAX_LIMITE);
    private Semaphore consumidor = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    /**
     * En el método producir(), daremos permisos al semáforo productor y al mutex,
     * aumentaremos los productos y liberaremos los permisos del mutex y del semáforo consumidor
     * (para que pueda empezar a consumir).
     */
    public void producir(String nombreProductor) {
        System.out.println(nombreProductor + " intentando almacenar un producto");
        try {
            productor.acquire();
            mutex.acquire();

            producto++;

            System.out.println(nombreProductor + " almacena un producto. "
                    + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
            mutex.release();

            Thread.sleep(500);

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            consumidor.release();

        }

    }

    /*
    * Daremos permisos al consumidor y mutex,
    * restaremos los productos
    * y liberaremos el mutex y el productor (para que pueda trabajar).
    * */
    public void consumir(String nombreConsumidor) {
        System.out.println(nombreConsumidor + " intentando retirar un producto");
        try {
            consumidor.acquire();
            mutex.acquire();

            producto--;

            System.out.println(nombreConsumidor + " retira un producto. "
                    + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
            mutex.release();

            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            productor.release();
        }
    }

}