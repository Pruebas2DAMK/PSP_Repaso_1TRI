package Ejecicios_Clase_1;

import java.util.*;

public class HoraEntrada extends Thread{

    Scanner dato = new Scanner(System.in);
    String n, d;
    double h;

    public HoraEntrada(String nombre, String día, double hora){
        n=nombre;
        d=día;
        h=hora;

        System.out.println("Introduzca el nombre del empleado :");
        n = dato.next();

        System.out.println("Indique el día :");
        d = dato.next();

        System.out.println("Indique la hora :");
        h = dato.nextDouble();
    }

    public void run(){
        if(h>8.00){
            System.out.println(n + " llegó tarde el día " + d);
        }else{
            System.out.println(n + " llegó temprano el día " + d);
        }
    }

    public static void main(String []args){
        //try {
            Thread usuario1 = new HoraEntrada("", "", 0);  //Crea primer hilo de ejecución
            usuario1.start();
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e){
                System.out.println("Exception...");
            }
            //Thread.sleep(3000);                         //espera 3 segundos
            //usuario1.join();                              //esppera a que el hijo usuario1 finalice su ejecución
            Thread usuario2 = new HoraEntrada("", "", 0);  //Crea segundo hilo de ejecución
            usuario2.start();                                   //arranca el hilo
       /* }
        catch (InterruptedException e){
            System.out.println("Exception = "+e.getStackTrace());
        }*/

        usuario1.interrupt();

    }

}

