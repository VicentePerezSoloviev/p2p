package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import modelo.Usuario;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    
    private final DAOUsuario daoUsuario;
    private final Hilo hilo;
    
    public ImplementacionServidor () throws RemoteException {
        super();
        this.hilo = new Hilo(this);
        this.daoUsuario = new DAOUsuario();
        this.hilo.start();
    }
    
    @Override
    public boolean introducirUsuario (Usuario usuario) throws RemoteException {
        return this.daoUsuario.introducirUsuario(usuario);
    }
    
    
    
    @Override
    public boolean cambiarContrasena (Usuario usuario, String nuevaContrasena) throws RemoteException{
        return this.daoUsuario.cambiarContrasena(usuario, nuevaContrasena);
    }
    
    @Override
    public boolean eliminarUsuario (Usuario usuario) throws RemoteException{
        return this.daoUsuario.eliminarUsuario(usuario);
    }
    
    @Override
    public ArrayList<Usuario> listarUsuarios () throws RemoteException{
        return this.daoUsuario.listarUsuarios();
    }
    
    @Override
    public ArrayList<String> listarUsuariosString () throws RemoteException{
        ArrayList<String> array = new ArrayList<>();
        for (Usuario u: this.daoUsuario.listarUsuarios()) {
            array.add(u.getNombreUsuario());
        }
        return array;
    }

    @Override
    public boolean iniciarSesion(Usuario u) throws RemoteException{
        /*Comprobamos si el usuario ya esta en linea*/
        
        for (Usuario s: this.hilo.getListaUsuariosConectados()){
            if (s.getNombreUsuario().equals(u.getNombreUsuario())){
                System.out.println("El usuario ya esta en linea");
                return false;
            }
        }
        /*Comprobamos si existe el usuario que se pasa por parametro*/
        
        ArrayList<Usuario> array = this.listarUsuarios();
                
        for (Usuario array1 : array) {
            if (array1.getNombreUsuario().equals(u.getNombreUsuario()) && array1.getPassword().equals(u.getPassword())){
                
                this.hilo.anadirUsuarioConectado(u);        //anadimos a array de usuarios conectados de hilo
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cerrarSesion(String u) throws RemoteException{
        Usuario usuario = new Usuario (u, null);
        this.hilo.eliminarUsuarioConectado(usuario);
        return true;
    }

    public ArrayList<String> listarUsuariosConectados() {
        ArrayList<String> arrayString = new ArrayList<>();
        
        for (Usuario us: this.hilo.getListaUsuariosConectados()){
            arrayString.add(us.getNombreUsuario());
        }
        
        return arrayString;
    }

    @Override
    public ArrayList<String> listarAmigosConectados(String u) throws RemoteException{
        ArrayList<String> usuariosConectados = this.listarUsuariosConectados();
        ArrayList<String> amigosUsuario = this.daoUsuario.listarAmigos(u);
        System.out.println(usuariosConectados);
        System.out.println(amigosUsuario);
        
        //usuariosConectados.retainAll(amigosUsuario);
        if (usuariosConectados.contains(u)) usuariosConectados.remove(u);       //nunca se ejecuta??
        System.out.println(usuariosConectados);
        ArrayList<String> arrayEliminar = new ArrayList<>();
        for (String s: amigosUsuario) {
            if (!usuariosConectados.contains(s)){
                arrayEliminar.add(s);       //guardo todos los usuarios para eliminar pk no estan conectados
            }
        }
        for (String s: arrayEliminar){
            amigosUsuario.remove(s);
        }
        System.out.println(amigosUsuario);
        System.out.println(usuariosConectados);
        
        return amigosUsuario;
    }

    @Override
    public void crearPeticionAmistad(String emisor, String receptor) throws RemoteException{
        if (!emisor.equals(receptor)) {
            int a=0;
            for (Usuario u: this.daoUsuario.listarUsuarios()) {
                if (u.getNombreUsuario().equals(emisor)) a++;
                if (u.getNombreUsuario().equals(receptor)) a++;
            }
            if (a>=2) this.daoUsuario.crearPeticion(emisor,receptor);
        }
        
    }
    
    @Override
    public ArrayList<String> listarPeticionesPendientes (String usuario) throws RemoteException{
        return this.daoUsuario.listarPeticiones(usuario);
    }

    @Override
    public void responderPeticionAmistad(String emisor, String receptor, boolean respuesta) throws RemoteException{
        this.daoUsuario.responder(emisor, receptor, respuesta);
    }

    @Override
    public String obtenerContra(String usuario) {
        return this.daoUsuario.obtenerPassword(usuario);
    }
    
}
