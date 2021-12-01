package Ejecicios_Clase_1;

public class CarreraMascotas extends Thread {

    String nombre;
    public CarreraMascotas(int prioridad, String nombre){
        this.nombre=nombre;
        setPriority(prioridad);
    }
    public void run(){
        for(int c=1;c<=10;c++){
            System.out.print(c+"mt ");
        }
        //yield();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Excepcion... ");
        }
        System.out.println("\n Llegó a la meta " + nombre);
    }
    public static void main(String []args) throws InterruptedException {
        CarreraMascotas conejo = new CarreraMascotas(Thread.MIN_PRIORITY," con ");
        CarreraMascotas liebre = new CarreraMascotas(Thread.NORM_PRIORITY," lie ");
        CarreraMascotas tortuga = new CarreraMascotas(Thread.MAX_PRIORITY," tor ");

        conejo.start();
        conejo.join();
        liebre.start();
        tortuga.start();

    }
}