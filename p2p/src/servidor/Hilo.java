package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

public class Hilo extends Thread {
    
    private final ImplementacionServidor impleSer;
    
    private final ArrayList<Usuario> listaUsuariosConectados;
    
    public Hilo(ImplementacionServidor implementacion) {
        super();
        this.impleSer = implementacion;
        this.listaUsuariosConectados = new ArrayList<>();
    }
    
    public void anadirUsuarioConectado (Usuario al){
        if (al != null){
            synchronized (listaUsuariosConectados) {       //bloqueo
                this.listaUsuariosConectados.add(al);      //añadir al array
            }
        }
    }
    
    public void eliminarUsuarioConectado (Usuario al) {
        if (al != null){
            synchronized (listaUsuariosConectados) {       //bloqueo
                this.listaUsuariosConectados.remove(al);      //añadir al array
            }
        }
    }
    
    @Override
    public void run() {
        
    }
    
    
    
}
