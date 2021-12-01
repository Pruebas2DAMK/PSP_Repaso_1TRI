package Repasando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class imitarPipe2 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb1 = new ProcessBuilder("ls","-la");
        ProcessBuilder pb2 = new ProcessBuilder("tr","Joel","JOEL");

        StringBuilder sb = new StringBuilder();

        Process ls = pb1.start();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(ls.getInputStream()))) {
            String linea;
            while((linea = br.readLine())!=null){
                sb.append(linea).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Process tr = pb2.start();

        PrintStream ps = new PrintStream(tr.getOutputStream());
        ps.print(sb);
        ps.close();

        try(BufferedReader br2 = new BufferedReader(new InputStreamReader(tr.getInputStream()))){
            String linea;
            while ((linea = br2.readLine())!= null){
                System.out.println(linea);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
