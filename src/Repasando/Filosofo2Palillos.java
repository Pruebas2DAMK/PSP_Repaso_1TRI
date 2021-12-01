package Repasando;

import java.util.concurrent.Semaphore;

public class Filosofo2Palillos implements Runnable{

    Semaphore palillo1;
    Semaphore palillo2;
    String nombre;
    Thread t;

    public Filosofo2Palillos(Semaphore palillo1, Semaphore palillo2, String nombre) {
        this.palillo1 = palillo1;
        this.palillo2 = palillo2;
        this.nombre = nombre;
        t = new Thread(this);

    }

    public Thread getT() {
        return t;
    }

    @Override
    public void run() {

        while(true){
            try {
                palillo1.acquire(); //permiso al palillo
                System.out.println("Filosofo "+nombre+" coje palillo izquierdo");
                palillo2.acquire();//permiso al palillo
                System.out.println("Filosofo "+nombre+" coje palillo derecho");

                System.out.println("Filosofo "+nombre+" Se pone a comer");
                Thread.sleep(1000); //pausa mientras come
                System.out.println("Filosofo "+nombre+" ha dejado de comer"); //deja de comer
                palillo1.release(); //suelta el palillo
                palillo2.release(); //suelta el palillo
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Semaphore palillo1 = new Semaphore(1);
        Semaphore palillo2 = new Semaphore(1);

        Filosofo2Palillos f1 = new Filosofo2Palillos(palillo1,palillo2,"Pepe");
        Filosofo2Palillos f2 = new Filosofo2Palillos(palillo1,palillo2,"Manolo");
        Filosofo2Palillos f3 = new Filosofo2Palillos(palillo1,palillo2,"Antonio");
        Filosofo2Palillos f4 = new Filosofo2Palillos(palillo1,palillo2,"Richard");
        Filosofo2Palillos f5 = new Filosofo2Palillos(palillo1,palillo2,"Ruben");

        f1.getT().start(); f2.getT().start(); f3.getT().start(); f4.getT().start(); f5.getT().start();
        try{
            f1.getT().join(); f2.getT().join(); f3.getT().join(); f4.getT().join(); f5.getT().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
