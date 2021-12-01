package Ejecicios_Clase_1;

class Ejercicio2_1  implements Runnable{ //Clase que hereda de Thread
    Thread t;//Objeto de la clase Thread
    Ejercicio2_1 () {
        t = new Thread(this, "Nuevo Thread");//inicializa el hilo
        t.start();// Arranca el nuevo hilo de ejecución. Ejecuta run
    }
    public void run(){//Método run en donde se ejecuta nuestro código
        for(int num =1; num<=20;num++){
            System.out.print(" " +num+" ");
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                System.out.println("Exception: = "+e.getStackTrace());
            }
        }
    }
    public static void main(String []args){

        Ejercicio2_1 ejer = new Ejercicio2_1();//Crea un hilo de ejecución
        System.out.println("En el programa principal.");
    }
}