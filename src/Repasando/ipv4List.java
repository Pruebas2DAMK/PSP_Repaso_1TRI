package Repasando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

class Ipv4List {
    ProcessBuilder pb;
    Process p;

    public Ipv4List(String comando) throws IOException {
        this.pb = new ProcessBuilder(comando);
        this.p = pb.start();
    }

    public static void main(String[] args) throws IOException {
        Ipv4List ipv4List = new Ipv4List("ipconfig");
        InputStream is = ipv4List.p.getInputStream();
        StringBuilder sb = new StringBuilder();
        String linea;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()))){
            while  ((linea = br.readLine()) != null){

                if (linea.contains("IPv4")){
                    sb.append(linea).append("\n");
                }


            }
            System.out.println(sb.toString());
        }

    }
}
