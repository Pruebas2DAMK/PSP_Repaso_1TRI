package Repasando;

import java.util.Scanner;

public class Carrera implements Runnable{
    Thread t;
    int km;
    String corredor;

    public Carrera(int km) {
        t = new Thread(this);
        this.km = km;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Como te llamas?");
        corredor = sc.nextLine();
        for (int i = 1; i <=km ; i++) {
            System.out.print(i+" Km ");
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nHa llegado a la meta "+corredor);

    }
    //*********Main*******/
    public static void main(String[] args) {
        Carrera c = new Carrera(50);
        c.t.start();
    }

}
