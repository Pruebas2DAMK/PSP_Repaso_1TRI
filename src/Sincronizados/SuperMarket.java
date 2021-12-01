package Sincronizados;

import java.util.Random;

class Resultados{
    public static int ganancias;
    public static long tiempo_espera;
    public static int clientes_atendidos;
}

class Caja{
    private  static final int MAX_TIME = 1000;

    class Nodo{
        int cliente;
        Nodo sig;
    }
    Nodo raiz, fondo;

    public Caja(){
        this.raiz = null;
        this.fondo = null;
    }

    private boolean vacia(){
        if(raiz==null)
            return true;
        else
            return false;
    }

    synchronized public void esperar(int id_cliente) throws InterruptedException{
        Nodo nuevo = new Nodo();

        nuevo.cliente = id_cliente;
        nuevo.sig = null;
        if(vacia()){
            raiz = nuevo;
            fondo = nuevo;
        }
        else{
            fondo.sig = nuevo;
            fondo = nuevo;
        }
        while(raiz.cliente!=id_cliente){
            //Se bloquea hasta que sea el turno
            wait();
        }
    }

    synchronized public void atender (int pago) throws InterruptedException{
        if (raiz==fondo){
            raiz = null;
            fondo = null;
        }
        else
            raiz = raiz.sig;

        int tiempo_atencion = new Random().nextInt(MAX_TIME);
        Thread.sleep(tiempo_atencion);
        Resultados.ganancias += pago;
        Resultados.clientes_atendidos++;
        notify();
    }

    synchronized public void imprimir(){
        Nodo reco = raiz;
        while(reco!=null){
            System.out.println(reco.cliente+"-");
            reco = reco.sig;
        }
        System.out.println();
    }
}

class Cliente extends Thread{
    private  static final int MAX_ESPERA = 2000;
    private  static final int MAX_COSTE = 100;
    private int id;
    private Caja caja;

    Cliente (int id, Caja caja){
        this.id = id;
        this.caja = caja;
    }

    public void run() {
        try{
            System.out.println("Cliente "+id+" realizando compra");
            Thread.sleep(new Random().nextInt(MAX_ESPERA));

            long s = System.currentTimeMillis();

            caja.esperar(id);
            System.out.println("Cliente "+id+" en cola con");
            caja.imprimir();

            caja.atender(new Random().nextInt(MAX_COSTE));
            System.out.println("Cliente "+id+" atendido");

            long espera = System.currentTimeMillis()-s;
            Resultados.tiempo_espera += espera;
            System.out.println("Cliente "+id+" saliendo despues de esperar "+espera);

        }catch (InterruptedException e){
            System.out.println("Interrumped exception");
        }
    }
}

public class SuperMarket {
    public static void main(String[] args) {
        //Suponemos iniciamente 3 cajas y 5 clientes
        int numCajas = 3;
        int numClientes = 5;
        Caja cajas[] = new Caja[numCajas];
        for(int i=0;i<numCajas;i++){
            cajas[i] = new Caja();
        }

        Cliente clientes[] = new Cliente[numClientes];
        int j;
        for (int i=0;i<numClientes;i++){
            //Seleccionamos ya en qué caja se situará, una aleatoria
            j = new Random().nextInt(3);
            clientes[i] = new Cliente(i, cajas[j]);
            clientes[i].start();
        }

        try{
            for (int i=0;i<numClientes;i++){
                clientes[i].join();
            }
        }catch (InterruptedException e){
            System.out.println("Hilo principal interrumpido.");
        }

        System.out.println("Supermercado cerrado.");
        System.out.println("Ganancias: "+Resultados.ganancias);
        System.out.println("Tiempo medio de espera: "+(Resultados.tiempo_espera/Resultados.clientes_atendidos));
    }
}
