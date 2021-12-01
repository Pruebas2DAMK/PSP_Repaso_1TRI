package Semaforos;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Supermercado {
    public static void main ( String[] args) {
        int cajaRandom = (int) (Math.random()*5+1);
        int compradorRandom = (int) (Math.random()*20+1);
        cajaRandom = 2;
        compradorRandom = 4;
        //Objeto compartido por todos los hilos, lo primero que se debe de crear.
        SuperMarket superMarket = new SuperMarket();
        System.out.println(compradorRandom + " Compradores serán atendidas por " + cajaRandom + " cajas ");
//Creación de compradores
        Comprador[] c = new Comprador[compradorRandom];
        for (int i=0; i < compradorRandom; i++) {
            c[i] = new Comprador(i + 1, superMarket);
            c[i].start();
        }
// Creación de cajas
        Caja[] a = new Caja[cajaRandom];
        for (int i=0; i < cajaRandom; i++) {
            a[i] = new Caja(i + 1, superMarket);
            a[i].start();
        }
// Se espera a que terminen todas las cajas
        for (int i=0; i < cajaRandom; i++) {
            try {
                a[i].join();
            } catch( InterruptedException e) {
                e.getStackTrace();
            }
        }
// Se espera a que terminen todos los compradores
        for (int i=0; i>compradorRandom; i++) {
            try {
                c[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
// Se cierra el supermercado
        System.out.println("Se cierre el supermercado con un tiempo acumulado de " + superMarket.getTiempoTotal() );
    }
}

//Objeto compartido:
class SuperMarket {
    //atributos del objeto compartido
    //semaforo que nos dirá si puede avanzar(1) o no(0)
    Semaphore semaforo = new Semaphore(1);
    //almacena cada uno de los compradores que van a a ser atendidos
    PriorityQueue listaComprador = new PriorityQueue <Integer>();
    //tiempo total de compra en el supermercado
    int tiempoTotal = 0;

    public int getTiempoTotal(){
        return tiempoTotal;
    }

    //Metodo que ejecutará un comprador cuando vaya a comprar al supermercado
    public void nuevoComprador(Integer nuevoComprador) {
        try {
            //Mira a ver si tiene posibilidad de ser atendido
            semaforo.acquire();
            //Si tiene posibilidad de ser atendido añade su identificador a la lista de compradores
            listaComprador.add(nuevoComprador);
            //System.out.println("Comprador que se añade a la lista = "+nuevoComprador);
            //libera el recurso compartido que será el semáforo creado en el supermercado
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Cuando un comprador termina la compra, sacará el comprador de la lista
    //se le indica el tiempo parcial para actualizar el tiempo total
    public int terminarCompra(Integer tiempoParcial) {
        int comprador = 0;
        try {
            if (isCompradoresPendientes()) {
                //Sección crítica: gestión de la cola, que no se puede ejecutar en paralelo,
                //se debe ejecutar secuencialmente porque dos cajas no coger el mismo comprador a la vez
                semaforo.acquire();
                //Si tiene posibilidad de salir de la cola, lo sacará
                comprador = (int)listaComprador.poll();
                //System.out.println("comprador que finaliza la compra = "+comprador);
                tiempoTotal += tiempoParcial;
                //liberará recurso
                semaforo.release();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return comprador;

    }

    public boolean isCompradoresPendientes(){
        if(listaComprador.isEmpty())
            return false;
        else
            return true;
    }
}

class Caja extends Thread {
    int identif;
    SuperMarket superMarket;
    int tiempoCaja;

    public Caja(int identif, SuperMarket superMarket) {
        this.identif = identif;
        this.superMarket = superMarket;
        this.tiempoCaja = 0;
    }

    //Preguntar contínuamente si el supermercado tiene compradores pendientes
    public void run() {
        int retardo;
        int numeroComprador;
        //Mientras haya compradores pendientes
        while (superMarket.isCompradoresPendientes()) {
            try {
                //se genera un tiempo aleatorio
                retardo = (int) (Math.random() * 3000 + 1000);
                tiempoCaja += retardo;
                //Le comunica al supermercado que va a acabar la compra
                numeroComprador = superMarket.terminarCompra(retardo);
                //simula el retardo
                sleep(retardo);
                //se saca por consola la información de qué caja ha sido atendida por qué comprador
                System.out.println("La caja " + identif + " ha atendido al comprador "+numeroComprador + " en un tiempo de "+retardo);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //Finalmente cuando ya no haya compradores pendientes, se indica que la caja ha finalizado
        System.out.println("Fin de la caja " + identif + " que termina con un tiempo parcial de  "+tiempoCaja);
    }
}

class Comprador extends Thread{

    int identif;
    SuperMarket superMarket;

    //se incicializan sus atributos
    public Comprador(int identif, SuperMarket superMarket) {
        this.identif = identif;
        this.superMarket = superMarket;
    }

    //Con el método del super nuevoComprador se meten en la lista de compradores
    public void run() {
        superMarket.nuevoComprador(identif);
    }
}
