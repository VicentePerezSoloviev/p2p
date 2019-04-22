package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    private static String nombreHost = "localhost";
    private static int puerto = 1099;
    
    public static void main(String[] args) throws IOException, RemoteException{
        String url;
        
        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);

//            //Solicitamos nombre de host de RMIRegistry
//            System.out.println("¿Nombre de host de RMIRegistry? ");
//            if (args.length == 0) { //leer por cmd
//                nombreHost = br.readLine();
//            } else { //argumento
//                nombreHost = args[0];
//                System.out.println(nombreHost);
//            }
//
//            //Solicitamos numero de puerto de RMIRegistry
//            System.out.println("\n¿Numero de puerto de RMIRegistry? ");
//            if (args.length <= 1) { //leer por cmd
//                numPuerto = Integer.parseInt(br.readLine());
//            } else { //argumento
//                numPuerto = Integer.parseInt(args[1]);
//                System.out.println(numPuerto);
//            }

            /*Activamos RMI en el puerto que se espera. Si no esta creado lo creamos*/
            startRegistry(Servidor.puerto);
            
            ImplementacionServidor objetoExportar = new ImplementacionServidor();
            
            url = "rmi://" + Servidor.nombreHost + ":" + Servidor.puerto + "/mensajeria";      //creo URL
            
            //Conecta con RMIRegistry en este ordenador y le avisa que exportedObj debe publicarse al exterior
            Naming.rebind(url, objetoExportar);
            
            System.out.println("Servidor preparado");
        } catch (MalformedURLException | RemoteException e) {
            System.out.println("ERROR (servidor): " + e);
        }
    }

    @SuppressWarnings("UnusedAssignment")
    private static void startRegistry (int numPuerto) throws RemoteException {
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry(numPuerto);
            reg.list();
        } catch (RemoteException ex) {
            System.out.println("Error al establecer RMI en puerto " + numPuerto + ". Se va a crear RMI");
            reg = LocateRegistry.createRegistry(numPuerto);
            System.out.println("RMI creado en puerto " + numPuerto + " con exito");
        }
        
    }
    
    private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {
       System.out.println("Registry " + registryURL + " contains: ");
       String [ ] names = Naming.list(registryURL);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
