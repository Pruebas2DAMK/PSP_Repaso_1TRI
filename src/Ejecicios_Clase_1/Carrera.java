package Ejecicios_Clase_1;
import java.util.Scanner;

public class Carrera extends Thread{
    Scanner dato = new Scanner(System.in);
    String nombre;
    public void run(){
        System.out.println("Indique el nombre del atleta :");
        nombre = dato.next();
        try{
            for(int c=1;c<=30;c++){
                System.out.print(c + " km ");
                sleep(300);
            }
            sleep(1000);
        }catch(InterruptedException e){
        }
        System.out.println("\n Llega a la meta " + nombre );
    }
    public static void main(String []args){
        Carrera eje =new Carrera();
        eje.start();
    }
}