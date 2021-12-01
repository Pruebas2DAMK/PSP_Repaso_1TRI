package Sincronizados;

import java.util.TreeMap;

class Palillo{
    private int numero;
    private boolean cogido;

    public Palillo(int id){
        this.numero = id;
        this.cogido = false;
    }

    public int getId(){
        return this.numero;
    }

    synchronized public void coger(){
        while (cogido){
            try{
                System.out.println("Palillo "+numero+" bloqueado.");
                wait();
            }catch (InterruptedException e){
                System.out.println("Exception");
            }
        }

        cogido = true;
        System.out.println("Palillo "+numero+ " ha sido cogido.");
    }

    synchronized public void soltar(){
        cogido = false;
        System.out.println("Palillo "+numero+ " ha sido soltado.");
        notify();
    }
}

class MesaCircular{
    private Palillo palillos[];
    private int filosofos;

    public MesaCircular(int personas){
        this.filosofos = personas;
        palillos = new Palillo[personas];
        for (int i=0;i<personas;i++){
            palillos[i] = new Palillo(i);
        }
    }

    public Palillo palillo_derecho(int i){
        return palillos[(i+1)%filosofos];
    }

    public Palillo palillo_izquierdo(int i){
        return palillos[i];
    }
}

class FilosofoM extends Thread{
    protected Palillo ind_izq, ind_der;
    protected int identidad;
    static final protected int MAX_DELAY = 1000;

    public FilosofoM(int id){
        this.identidad = id;

        ind_izq = FilosofosMonitores.mesa.palillo_izquierdo(id);
        ind_der = FilosofosMonitores.mesa.palillo_derecho(id);
    }

    protected void pensar(){
        try{
            System.out.println("Filósofo "+identidad+" está pensando.");
            Thread.sleep((int)Math.random()*MAX_DELAY);
        }catch (InterruptedException e){
            System.out.println("Exception.");
        }
    }
    protected void queriendo_comer(){
        System.out.println("Filósofo "+identidad+" quiere comer.");
        //para evitar interbloqueo, modificar la forma en la que se cogen los palillos, identificando si
        //el filosofo que coge el palillo es para o impar para coger el palillo izq o derecho y que
        //no cojan siempre todos el palillo izquierdo.
        if(identidad%2==0){
            ind_izq.coger();
            ind_der.coger();
        }
        else{
            ind_der.coger();
            ind_izq.coger();
        }

    }
    protected void comer(){
        try{
            System.out.println("Filósofo "+identidad+" está comiendo.");
            Thread.sleep((int)Math.random()*MAX_DELAY);
        }catch (InterruptedException e){
            System.out.println("Exception.");
        }
    }

    protected void dejando_de_comer(){
        ind_izq.soltar();
        ind_der.soltar();
    }

    public void run(){
        while (true){
            pensar();
            queriendo_comer();
            comer();
            dejando_de_comer();
        }
    }
}

public class FilosofosMonitores {
    public static MesaCircular mesa;
    public static void main(String[] args) {

        mesa = new MesaCircular(5);
        System.out.println("Sentados 5 filósofos.");

        FilosofoM f1 = new FilosofoM(0);
        FilosofoM f2 = new FilosofoM(1);
        FilosofoM f3 = new FilosofoM(2);
        FilosofoM f4 = new FilosofoM(3);
        FilosofoM f5 = new FilosofoM(4);
        f1.start();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println("Excepcion");

        }
        f2.start();
        f3.start();
        f4.start();
        f5.start();
    }
}
