package Procesos;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
/*
Joel Sempere Durá - Ejercicio 4
Programa que admite como parametro (args) de entrada la ruta de un archivo ejecutable , ejecuta y muestra por pantalla aplicacion finalizada.
 */
public class Ejercicio104JSD {
    public static void main(String[] args) {
        if (args.length > 0) {
            ProcessBuilder pb = new ProcessBuilder(args);
            try {
                //p equivale al lanzamiento del proceso
                Process p = pb.start();
                //retorno es la espera del proceso hasta que este termina
                int retorno = p.waitFor();
                //if que devuelve fin del programa si es true
                if (retorno == 0)
                {
                    System.out.println("Aplicacion Finalizada\n---------------------\n" + Arrays.toString(args));
                } else
                    {
                        System.out.println("ERROR");
                    }
                //capturas de excepciones
            } catch (IOException e) {
                System.err.println("Excepcion de E/S");
                System.exit(-1);
            } catch (InterruptedException ie) {
                System.err.println("El proceso hijo finalizó de forma incorrecta");
                System.exit(-1);
            }
        } else {
            System.err.println("No has introducido ningun argumento");
        }
    }
}
