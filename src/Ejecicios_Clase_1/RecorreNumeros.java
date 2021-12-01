package Ejecicios_Clase_1;

class Ejercicio2  implements Runnable{ //Clase que implementa Runnable

    public void run(){ //Método run en donde se ejecuta nuestro código
        for(int num =1; num<=20;num++){
            System.out.print(" " +num+" ");
        }
    }
    public static void main(String []args){
        //se define y ejecuta el hilo
        Thread miHilo = new Thread (new Ejercicio2());  //Crea un hilo de ejecución a partir de la clase runnable
        miHilo.start();
        System.out.println("En el programa principal.");
    }
}