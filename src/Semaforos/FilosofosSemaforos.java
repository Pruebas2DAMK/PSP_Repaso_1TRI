package Semaforos;

import java.util.concurrent.Semaphore;

class Filosofo extends Thread{
    Semaphore palillo1, palillo2;
    String nombre;

    public Filosofo(Semaphore palillo1, Semaphore palillo2, String nombre){
        this.palillo1 = palillo1;
        this.palillo2 = palillo2;
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void run(){
        try{
            //se supone que los filósofos están siempre pensando(bucle infinito)...
            //pero le ponemos un total de 3 vueltas
            //System.out.println("El filosofo "+getNombre()+" entra en juego... ");
            int i=0;
            while(i<3){
                palillo1.acquire();
                System.out.println("El filósofo: "+getNombre()+" coge el palillo 1");
                palillo2.acquire();
                System.out.println("El filósofo: "+getNombre()+" coge el palillo 2");


                //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                System.out.println(getNombre()+" se pone a comer..... ");
                sleep((int)(Math.random()*2000+1000));

                //Suelta los palillos y piensa durante un tiempo
                System.out.println("Suelta los palillos y vuelve a pensar... ");
                palillo1.release();
                palillo2.release();
                System.out.println();
                sleep((int)(Math.random()*2000+1000));
                i++;
            }
        }
        catch (InterruptedException e){
            System.out.println("Error en ejecución Filósofo.");
        }
    }
}

public class FilosofosSemaforos {
    public static void main(String[] args) {
        Semaphore palillo1 = new Semaphore(1);
        Semaphore palillo2 = new Semaphore(1);

        Filosofo f1 = new Filosofo(palillo1, palillo2,"F1");
        Filosofo f2 = new Filosofo(palillo1, palillo2,"F2");
        Filosofo f3 = new Filosofo(palillo1, palillo2,"F3");
        Filosofo f4 = new Filosofo(palillo1, palillo2,"F4");
        Filosofo f5 = new Filosofo(palillo1, palillo2,"F5");

        f1.start();
        f2.start();
        f3.start();
        f4.start();
        f5.start();

        try{
            f1.join();
            f2.join();
            f3.join();
            f4.join();
            f5.join();
        }catch (InterruptedException e){
            System.out.println("Error en ejecución Filósofo.");
        }
    }
}
