package Ejecicios_Clase_1;

import java.util.Scanner;

public class Fibonacci extends Thread {         //Clase del hilo que hereda de Thread
    int numero, fibo1, fibo2;

    public Fibonacci(int numero){
        this.numero = numero;
    }
    public void run(){                          //Método run en donde se ejecuta el código
        System.out.println("Los "+numero+" primeros primeros términos de la serie de Fibonacci son: ");
        fibo1 = 1;
        fibo2 = 1;

        for(int i=2;i<=numero;i++){
            System.out.print(fibo2 + " ");
            fibo2 = fibo1+fibo2;
            fibo1 = fibo2-fibo1;
        }
    }
    public static void main(String []args){

        Scanner sc = new Scanner(System.in);
        int numero = 0;
        do {
            System.out.println("Introduce un número mayor que 1: ");
            numero = sc.nextInt();
        }while(numero<=1);
        Fibonacci fibo = new Fibonacci(numero);     //Crea un hilo de ejecución
        fibo.start();                               //Arranca el hilo
        sc.close();


    }
}
