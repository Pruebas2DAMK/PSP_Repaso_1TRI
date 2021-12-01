package Procesos;

import java.io.IOException;

public class avisoCierre {
    public static void main(String[] args){
        //Declaro en una variable el programa a ejecutar
        String comando="notepad.exe";
        ProcessBuilder pb = new ProcessBuilder(comando);
        try{
            //p equivale al lanzamiento del proceso
            Process p = pb.start();
            //retorno es la espera del proceso hasta que este termina
            int retorno = p.waitFor();
            //ternario que devuelve fin del programa si es true
            String comprueba=retorno==0?"La ejecución de "+comando+" finalizo de forma correcta":"Error";
            System.out.println(comprueba);
        //capturas de excepciones
        }catch (IOException e){
            System.err.println("Excepcion de E/S");
            System.exit(-1);
        } catch(InterruptedException ie){
            System.err.println("El proceso hijo finalizó de forma incorrecta");
            System.exit(-1);
        }

    }
}
