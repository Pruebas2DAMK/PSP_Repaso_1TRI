package Sincronizados;

import java.util.Random;

class Barrera{
    private int plazas[];
    private int n_plazas;
    private int libres;

    Barrera (int N){
        n_plazas = N;
        plazas = new int[N];
        for (int i=0;i<n_plazas;i++){
            plazas[i]=0;
            libres = N;
        }
    }
    synchronized  public int entrada(int coche) throws InterruptedException{
        int plaza=0;
        imprimir();

        while(libres==0){
            System.out.println("Coche "+coche+" esperando.");
            wait();
        }

        while(plazas[plaza]!=0){
            plaza++;
        }
        plazas[plaza] = coche;
        libres--;
        return plaza;
    }

    synchronized public void salida(int plaza){
        plazas[plaza]=0;
        libres++;
        notify();
    }

    public void imprimir(){
        System.out.println("Parking: ");
        for (int i=0;i<n_plazas;i++){
            System.out.println("["+plazas[i]+"]");
        }
        System.out.println();
    }
}

class Coche extends Thread{
    private static final int MAX_DELAY = 2000;
    private int id;
    private Barrera barrera;

    Coche(int id, Barrera barr){
        this.id = id;
        this.barrera = barr;
    }

    public void run(){
        try{
            Thread.sleep(new Random().nextInt(MAX_DELAY));
            System.out.println("Coche "+id+" intenta entrar en parking.");

            int plaza = barrera.entrada(id);
            System.out.println("Coche "+id+" aparcando en la plaza "+plaza);
            barrera.imprimir();

            Thread.sleep(new Random().nextInt(MAX_DELAY));

            barrera.salida(plaza);
            System.out.println("Coche "+id+" saliendo");
            barrera.imprimir();

        }catch (InterruptedException e){
            System.out.println("Exception");
        }
    }
}

public class Parking {
    public static void main(String[] args) {
        int N = 5;
        //Inicializamos el parking
        Barrera barrera = new Barrera(N);

        int C = 15;
        Coche coches[] = new Coche[C];
        for (int i=0;i<C;i++){
            coches[i] = new Coche(i+1, barrera);
            coches[i].start();
        }

        try{
            for (int i=0;i<C;i++){
                coches[i].join();
            }

        }catch (InterruptedException e){
            System.out.println("Exception en el hilo principal");
        }
    }
}