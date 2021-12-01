package Sincronizados;

import java.nio.IntBuffer;

class Escritor extends Thread{
    private int bloqueo;
    private IntBuffer buf;
    private Object mutex;
    private int contador;

    Escritor(int bloqueo, Object mutex, IntBuffer buf){
        this.bloqueo = bloqueo;
        this.mutex = mutex;
        this.buf = buf;
        this.contador = 0;
    }

    private void escribir(){
        int i;

        for (i=0; i<100; i++){
            buf.put(i,contador);
        }
        contador++;
    }

    public void run(){
        while(true){
            if(this.bloqueo==1){
                //accedemos en exclusión mutua
                synchronized (this.mutex){
                    escribir();
                }
            }
            else{
                escribir();
            }
        }
    }
}

class Lector extends Thread{
    private int bloqueo;
    private IntBuffer buf;
    private Object mutex;

    Lector(int bloqueo, Object mutex, IntBuffer buf){
        this.bloqueo = bloqueo;
        this.mutex = mutex;
        this.buf = buf;
    }

    public void comprobar(){
        int i;
        int elementoDistinto = 0;

        for(i=0;i<100;i++){
            if(buf.get(0)!=buf.get(i)){
                System.out.println("Thread Lector: Error elementos del buffer diferentes");
                elementoDistinto = 1;
                break;
            }
        }

        if(elementoDistinto==0){
            System.out.println("Thread Lector: Elementos del buffer iguales");
        }
    }

    public void run(){
        while(true){
            if(this.bloqueo==1){
                //accedemos en exclusión mutua
                synchronized (this.mutex){
                    comprobar();
                }
            }
            else{
                comprobar();
            }
        }
    }
}

public class Check {
    public static void main(String[] args) {
        //buffer con 100 elementos en lugar de 10000 = recurso compartido
        IntBuffer buf = IntBuffer.allocate(100);
        //Definimos un objeto mutex sobre el cual se accederá en exclusión mutua en el caso
        //en que se quiera utilizar el monitor
        Object mutex = new Object();

        //Modificar primer parámetro si queremos que use o no "Monitor" (1 ó 0)
        Lector l = new Lector(1, mutex, buf);
        Escritor e = new Escritor(1, mutex, buf);

        l.start();
        e.start();

        try{
            l.join();
            e.join();
        }
        catch (InterruptedException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}
