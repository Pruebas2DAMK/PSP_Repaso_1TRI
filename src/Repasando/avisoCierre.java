package Repasando;

import java.io.IOException;

public class avisoCierre {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        Process p = pb.start();
        int retorno = p.waitFor();
        String salida=retorno==0?"Cerrado bien":"Error";
        System.out.println(salida);
    }

}
