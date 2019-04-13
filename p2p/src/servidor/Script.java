package servidor;

import cliente.VPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.rmi.NotBoundException;

public class Script {
    public static void main(String args[]) throws NotBoundException, IOException, InterruptedException {
        Servidor.main(args);
        
        sleep (1000);
        
        int port = 1099;
        System.out.println("Numero de clientes?");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        int numClient = Integer.valueOf(br.readLine().trim());
        
        for (int i=0;i<numClient; i++) VPrincipal.main(args, port);
        
    }
}
