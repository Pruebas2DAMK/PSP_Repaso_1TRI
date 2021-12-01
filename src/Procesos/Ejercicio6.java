package Procesos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio6 {
    private static ArrayList<Process> list;
    private static String[] params; // = {"notepad", "calc"};
    private static int[] estadoProcesos;

    /*
     *Lanza todos los procesos recibidos
     */
    private static void lanzarProcesos(){
        for (int i=0; i<params.length; i++)
        {
            try{
                Process p = Runtime.getRuntime().exec(params[i]);
                list.add(p);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    /*
     *	Pregunta al usuario si quiere matar o no cada proceso lanzado.
     */
    private static void matarProcesos(){
        Scanner teclado = new Scanner(System.in);
        String opcion;
        for (int i=0; i<params.length; i++)
        {
            System.out.println("Cerrar el proceso: "+params[i]+"? s/n ");
            opcion = teclado.next();
            /*
             *	estadoProcesos = 0 indica que se eliminó el proceso
             *	estadoProcesos = 1indica que  el proceso se dejó abierto
             */

            if (opcion.compareToIgnoreCase("s")==0){
                list.get(i).destroy();
                estadoProcesos[i]=0;
            }
            else{
                estadoProcesos[i]=1;
            }
        }
        teclado.close();
    }
    /*
     * Muestra el estado de los procesos al finalizar
     */
    private static void resumen(){
        for (int i=0; i<estadoProcesos.length; i++)
        {
            if (estadoProcesos[i]==0){
                System.out.println("El proceso: "+params[i]+" fue eliminado.");
            }
            else{
                System.out.println("El proceso: "+params[i]+" continúa abierto.");
            }
        }
    }
    /*
     *	Método ppal
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Numero de parámetros incorrecto");

        }
        else{
            params = args;
            list = new ArrayList<Process>();
            estadoProcesos = new int[params.length];
            lanzarProcesos();
            matarProcesos();
            resumen();
        }
    }
}
