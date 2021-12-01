package Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Ejercicio107JSD {
    public static void main(String[] args) {
        try {
            //System.getProperties().list(System.out); //Comando interesante apuntar para futuro
            //Obtengo un true o false dependiendo del sistema operativo
            Boolean sistemaOperativo = System.getProperty("os.name").startsWith("Windows");
            String comandoLinux = "ifconfig";
            String comandoWindows = "ipconfig";
            //Si el sistema operativo es windows usa ipconfig si no usa ifconfig
            if (sistemaOperativo) {
                //metodo para obtener las ip, asi lo hacemos menos redundante
                System.out.println(obtenerIP(comandoWindows));
            } else {
                System.out.println(obtenerIP(comandoLinux));
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    /*
    Metodo que obtiene las ip's del dispositivo que ejecuta el programa.
    Guarda en una string todas las lineas del comando que contangan una ipv4
     */
    private static String obtenerIP(String comando) throws IOException {
        String salida ="\tLas direcciones IP de este dispositivo son: \n\t------------------------------------------\n";
        String linea;

        //creamos el proceso
        ProcessBuilder pb = new ProcessBuilder(comando);
        //lo iniciamos
        Process p = pb.start();
        //encapsulamos para lectura
        InputStream is = p.getInputStream();
        //He utilizado ese estandar de codificacion ya que es el unico que no me imprime un ? en dirección
        InputStreamReader isr = new InputStreamReader(is,StandardCharsets.ISO_8859_1 );
        BufferedReader br = new BufferedReader(isr);
        //Mientras que las lineas no esten vacias haz esto
        while ((linea = br.readLine()) != null ){
            //Si la linea contiene ipv4 guardamela en salida
            if (linea.contains("IPv4")){
                salida+= linea+"\n";
            }
            if (linea.contains ("inet")){
                salida+= linea.trim().split(" ")[1] +"\n";
            }

        }
        salida+="\n" +
                "┏━━┓╋╋╋╋╋╋╋┏┓╋╋╋╋╋┏┓╋╋┏━━━┳━━┓\n" +
                "┃┏┓┃╋╋╋╋╋╋╋┃┃╋╋╋╋╋┃┃╋╋┃┏━┓┃┏┓┃\n" +
                "┃┗┛┗┳┓╋┏┓╋╋┃┣━━┳━━┫┃╋╋┃┗━┛┃┗┛┗┓\n" +
                "┃┏━┓┃┃╋┃┃┏┓┃┃┏┓┃┃━┫┃╋┏┫┏━━┫┏━┓┃\n" +
                "┃┗━┛┃┗━┛┃┃┗┛┃┗┛┃┃━┫┗━┛┃┃╋╋┃┗━┛┃\n" +
                "┗━━━┻━┓┏┛┗━━┻━━┻━━┻━━━┻┛╋╋┗━━━┛\n" +
                "╋╋╋╋┏━┛┃\n";
        return salida;
    }
}
