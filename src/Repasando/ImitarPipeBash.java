package Repasando;

import java.io.*;

public class ImitarPipeBash {

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb1 = new ProcessBuilder("ls ","-la");
        StringBuilder sb = new StringBuilder();

        Process ls = pb1.start();
        InputStream isLs = ls.getInputStream();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(isLs))){
            String linea;
            while((linea = br.readLine())!=null){
                sb.append(linea).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ProcessBuilder pb2 = new ProcessBuilder("tr","d","D");
        Process tr = pb2.start();
        PrintStream ps = new PrintStream(tr.getOutputStream());
        ps.print(sb);
        ps.close(); //IMPORTANTE

        try(BufferedReader br2 = new BufferedReader(new InputStreamReader(tr.getInputStream()))){
            String linea;
            while ((linea=br2.readLine()) != null){
                System.out.println(linea);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
