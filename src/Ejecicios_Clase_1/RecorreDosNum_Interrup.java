package Ejecicios_Clase_1;

class Hilo1i  implements Runnable {//Clase que implementa runnable
    private int id, n;//atributos

    public Hilo1i(int id, int n) {
        this.id = id;
        this.n = n;
    }

    public void run() {//Método run en donde se ejecuta nuestro código
        for (int i = 1; i <= n; i++) {
            //if (!Thread.currentThread().isInterrupted()) {
            if (!Thread.interrupted()) {
                System.out.println("Hilo " + id + " con valor = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Se interrumpe el hilo!!!! con id =  "+id);
                    return;
                }
            }
        }
    }
}
class Hilo2i extends Thread {//Clase que implementa runnable
    private int id, n;//atributos

    public Hilo2i(int id, int n) {
        this.id = id;
        this.n = n;
    }

    public void run() {//Método run en donde se ejecuta nuestro código
        for (int i = 1; i <= n; i++) {
            System.out.println("Hilo " + id + " con valor = " + i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Exception: = " + e.getStackTrace());
            }
        }

    }
}
class Ejercicio2_2_Interrrupt {
    public static void main(String[] args) {
        //se definen dos hilos, uno de cada tipo
        Thread hilo1 = new Thread(new Hilo1i(1, 10));//Implementa runnable
        Thread hilo2 = new Hilo2i(2, 10);//Hereda de Thread
        //se arrancan los hilos
        hilo1.start();
        try {
            Thread.sleep(2000);// Espera dos segundos desde que empieza hilo1 y se empieza a ejecutar hilo2
            //hilo1.join();                 //Espera a que finalice la ejecució del hilo1 y luego ejecuta hilo2
        }catch (InterruptedException e){
            System.out.println("Exception = "+e.getStackTrace());
        }
        hilo1.interrupt();
        hilo2.start();
    }
}
