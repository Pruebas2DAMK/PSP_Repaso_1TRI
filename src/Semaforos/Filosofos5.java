package Semaforos;

import java.util.concurrent.Semaphore;

class Filosofo5 extends Thread{
    Semaphore palillo1, palillo2, palillo3, palillo4, palillo5;
    String nombre;

    public Filosofo5(Semaphore palillo1, Semaphore palillo2, Semaphore palillo3, Semaphore palillo4, Semaphore palillo5, String nombre){
        this.palillo1 = palillo1;
        this.palillo2 = palillo2;
        this.palillo3 = palillo3;
        this.palillo4 = palillo4;
        this.palillo5 = palillo5;
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void run(){
        try{
            //se supone que los filósofos están siempre pensando(bucle infinito)...
            //pero le ponemos un total de 3 vueltas
            //System.out.println("El Filosofo5 "+getNombre()+" entra en juego... ");
            int i=0;
            while(i<3){

                if(getNombre()=="F1")   //Si es el filosofo 1 el que quiere comer, necesita los palillos 5 y 1
                {
                    palillo5.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 5");
                    palillo1.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 1");


                    //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                    System.out.println(getNombre() + " se pone a comer..... ");
                    sleep((int) (Math.random() * 2000 + 1000));

                    //Suelta los palillos y piensa durante un tiempo
                    System.out.println(getNombre() + "Suelta los palillos y vuelve a pensar... ");
                    palillo5.release();
                    palillo1.release();
                    System.out.println();
                    sleep((int) (Math.random() * 2000 + 1000));
                }
                else if(getNombre()=="F2")   //Si es el filosofo 1 el que quiere comer, necesita los palillos 5 y 1
                {
                    palillo1.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 1");
                    palillo2.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 2");


                    //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                    System.out.println(getNombre() + " se pone a comer..... ");
                    sleep((int) (Math.random() * 2000 + 1000));

                    //Suelta los palillos y piensa durante un tiempo
                    System.out.println(getNombre() + "Suelta los palillos y vuelve a pensar... ");
                    palillo1.release();
                    palillo2.release();
                    System.out.println();
                    sleep((int) (Math.random() * 2000 + 1000));
                }
                else if(getNombre()=="F3")   //Si es el filosofo 1 el que quiere comer, necesita los palillos 5 y 1
                {
                    palillo2.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 2");
                    palillo3.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 3");


                    //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                    System.out.println(getNombre() + " se pone a comer..... ");
                    sleep((int) (Math.random() * 2000 + 1000));

                    //Suelta los palillos y piensa durante un tiempo
                    System.out.println(getNombre() + "Suelta los palillos y vuelve a pensar... ");
                    palillo2.release();
                    palillo3.release();
                    System.out.println();
                    sleep((int) (Math.random() * 2000 + 1000));
                }
                else if(getNombre()=="F4")   //Si es el filosofo 1 el que quiere comer, necesita los palillos 5 y 1
                {
                    palillo3.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 3");
                    palillo4.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 4");


                    //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                    System.out.println(getNombre() + " se pone a comer..... ");
                    sleep((int) (Math.random() * 2000 + 1000));

                    //Suelta los palillos y piensa durante un tiempo
                    System.out.println(getNombre() + "Suelta los palillos y vuelve a pensar... ");
                    palillo3.release();
                    palillo4.release();
                    System.out.println();
                    sleep((int) (Math.random() * 2000 + 1000));
                }
                else if(getNombre()=="F5")   //Si es el filosofo 1 el que quiere comer, necesita los palillos 5 y 1
                {
                    palillo4.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 4");
                    palillo5.acquire();
                    System.out.println("El filósofo: " + getNombre() + " coge el palillo 5");


                    //si el filósofo ha conseguido coger los dos palillos, come durante un tiempo:
                    System.out.println(getNombre() + " se pone a comer..... ");
                    sleep((int) (Math.random() * 2000 + 1000));

                    //Suelta los palillos y piensa durante un tiempo
                    System.out.println(getNombre() + "Suelta los palillos y vuelve a pensar... ");
                    palillo4.release();
                    palillo5.release();
                    System.out.println();
                }
                i++;
            }
        }
        catch (InterruptedException e){
            System.out.println("Error en ejecución Filósofo.");
        }
    }
}

public class Filosofos5 {
    public static void main(String[] args) {
        Semaphore palillo1 = new Semaphore(1);
        Semaphore palillo2 = new Semaphore(1);
        Semaphore palillo3 = new Semaphore(1);
        Semaphore palillo4 = new Semaphore(1);
        Semaphore palillo5 = new Semaphore(1);

        Filosofo5 f1 = new Filosofo5(palillo1, palillo2, palillo3, palillo4, palillo5,"F1");
        Filosofo5 f2 = new Filosofo5(palillo1, palillo2, palillo3, palillo4, palillo5,"F2");
        Filosofo5 f3 = new Filosofo5(palillo1, palillo2, palillo3, palillo4, palillo5,"F3");
        Filosofo5 f4 = new Filosofo5(palillo1, palillo2, palillo3, palillo4, palillo5,"F4");
        Filosofo5 f5 = new Filosofo5(palillo1, palillo2, palillo3, palillo4, palillo5,"F5");

        f1.start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("Exception");
        }
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
        System.out.println("Fin de la ejecución");
    }
}