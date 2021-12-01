package Productor_Consumidor;

/*
 * Consumidor crearemos una instancia del almacén
 * y en el método run() solo llamaremos al método consumir() de este.
 * */
public class Consumidor extends Thread{
    private Almacen almacen;

    public Consumidor(String name, Almacen almacen) {
        super(name);
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while(true){
            almacen.consumir(this.getName());
        }
    }
}