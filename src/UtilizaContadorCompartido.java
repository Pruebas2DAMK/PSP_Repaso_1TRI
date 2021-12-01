public class UtilizaContadorCompartido {
    //main
    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador();
        for (int i = 0; i <10 ; i++) {
            HiloSumador hiloSumador = new HiloSumador(c);
            HiloRestador hiloRestador = new HiloRestador(c);
        }


    }
}

class Contador{
//Donde se mantiene el contador
int cont = 0;
    public Contador() {

    }

    public synchronized void sumar(){
            cont ++;
            System.out.println("Sumando: "+cont);


}

public synchronized void restar(){
        if (cont >= 0){
            cont --;
            System.out.println("Restando: "+cont);
        }
    }

}

class HiloRestador implements Runnable{
Thread t;
Contador c;
    public HiloRestador(Contador c) {
        this.c = c;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        c.restar();
    }
}
class HiloSumador implements Runnable{
    Thread t;
    Contador c;
    public HiloSumador(Contador c) {
        this.c = c;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        c.sumar();
    }
}
