package Ejecicios_Clase_1;

class Ejercicio2_prioridades extends Thread
{
    int cont=0;
    String name = "";
    public Ejercicio2_prioridades(String nombre){
        name = nombre;
    }
    public void run()
    {   try {
            if (Thread.currentThread().getPriority() == Thread.MIN_PRIORITY) {
                sleep(500);
            }
            else if(Thread.currentThread().getPriority() == Thread.NORM_PRIORITY) {
                sleep(300);
            }
        }catch (InterruptedException e){
            System.out.println("Exception... ");
        }
        System.out.println("Dentro del método run = "+name);
    }

    public static void main(String[]args)
    {
        Ejercicio2_prioridades t1 = new Ejercicio2_prioridades("Hilo 1");
        Ejercicio2_prioridades t2 = new Ejercicio2_prioridades("Hilo 2");
        Ejercicio2_prioridades t3 = new Ejercicio2_prioridades("Hilo 3");

        System.out.println("Prioridad del hilo t1 : " +t1.getPriority());
        System.out.println("Prioridad del hilo t2 : " + t2.getPriority());
        System.out.println("Prioridad del hilo t3 : " + t3.getPriority());

        t1.setPriority(Thread.NORM_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);

        //t3.setPriority(21); //arrojará IllegalArgumentException
        System.out.println("Prioridad del hilo t1 : " + t1.getPriority());
        System.out.println("Prioridad del hilo t2 : " + t2.getPriority());
        System.out.println("Prioridad del hilo t3 : " + t3.getPriority());

        t1.start();
        t2.start();
        t3.start();

    }
}