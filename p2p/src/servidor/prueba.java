package servidor;

import java.rmi.RemoteException;
import modelo.Usuario;

public class prueba {
    public static void main(String[] args) throws RemoteException {
        ImplementacionServidor impl = new ImplementacionServidor();
        
        Usuario a = new Usuario ("ei", "quetal");
        Usuario b = new Usuario ("q", "w");
        
        impl.crearPeticionAmistad("ei", "q");
        impl.crearPeticionAmistad("vicente", "q");
        
        impl.iniciarSesion(a);
        impl.iniciarSesion(b);
        impl.iniciarSesion(new Usuario("vicente", "password"));
        
        System.out.println(impl.listarAmigosConectados(b.getNombreUsuario()));
  }
}
