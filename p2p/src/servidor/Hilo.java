package servidor;

import java.util.ArrayList;
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
                
                for (Usuario u: listaUsuariosConectados){
                    if (u.getNombreUsuario().equals(al.getNombreUsuario())) {
                        this.listaUsuariosConectados.remove(u);      //añadir al array
                        break;
                    }
                }
            }
        }
    }

    public ArrayList<Usuario> getListaUsuariosConectados() {
        return listaUsuariosConectados;
    }
    
    
    @Override
    public void run() {
        
    }
    
    
    
}
