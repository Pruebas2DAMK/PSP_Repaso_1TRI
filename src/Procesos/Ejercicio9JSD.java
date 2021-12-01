package Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

    public class Ejercicio9JSD {
        //Joel Sempere Durá
        public static void main(String[] args) throws IOException {
            //Creamos proceso ls
            ProcessBuilder pb = new ProcessBuilder("ls","-la");
            StringBuilder sb = new StringBuilder();
            //inicia ls
            Process ls = pb.start();
            //guarda salida del ls
            try(BufferedReader br = new BufferedReader(new InputStreamReader(ls.getInputStream()))){
                //inicializa linea que guarda cada linea de la salida del ls
                String linea;
                while ((linea=br.readLine())!= null) {
                    //cada linea del ls es guardada en el  StringBuilder
                    sb.append(linea).append("\n");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            //Creamos proceso tr
            ProcessBuilder pb2 = new ProcessBuilder("tr","d","D");
            //iniciamos tr
            Process tr = pb2.start();
            //Escribe datos en el output del tr
            PrintStream ps = new PrintStream(tr.getOutputStream());
            //al proceso se le pasan los datos del stringBuilder que contiene el resultado del ls
            ps.print(sb);
            ps.close();

            //Guarla el ls con la informacion del tr , listo para imprimir
            try(BufferedReader br2 = new BufferedReader(new InputStreamReader(tr.getInputStream()))) {
                String linea;
                while ((linea = br2.readLine()) != null) {
                    //Salida del comando ls -la | tr "d" "D"
                    System.out.println(linea);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
}