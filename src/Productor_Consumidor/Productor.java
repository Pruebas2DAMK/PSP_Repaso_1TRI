package Productor_Consumidor;

/*
 * Consumidor crearemos una instancia del almacén
 * y en el método run() solo llamaremos al método producir() de este.
 * */
public class Productor extends Thread {

    private Almacen almacen;

    public Productor(String name, Almacen almacen) {
        super(name);
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true) {
            almacen.producir(this.getName());
        }
    }

}