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
    public boolean introducirUsuario (Usuario usuario) {
        return this.daoUsuario.introducirUsuario(usuario);
    }
    
    @Override
    public Usuario cambiarContrasena (Usuario usuario, String nuevaContrasena) {
        return this.daoUsuario.cambiarContrasena(usuario, nuevaContrasena);
    }
    
    @Override
    public boolean eliminarUsuario (Usuario usuario) {
        return this.daoUsuario.eliminarUsuario(usuario);
    }
    
    @Override
    public ArrayList<Usuario> listarUsuarios () {
        return this.daoUsuario.listarUsuarios();
    }

    @Override
    public boolean iniciarSesion(Usuario u) {
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
    public boolean cerrarSesion(String u) {
        Usuario usuario = new Usuario (u, null);
        this.hilo.eliminarUsuarioConectado(usuario);
        return true;
    }

    @Override
    public ArrayList<String> listarUsuariosConectados(String u) {
        ArrayList<String> arrayString = new ArrayList<>();
        
        for (Usuario us: this.hilo.getListaUsuariosConectados()){
            arrayString.add(us.getNombreUsuario());
        }
        
        return arrayString;
    }

    @Override
    public ArrayList<String> listarAmigosConectados(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearPeticionAmistad(String emisor, String receptor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void responderPeticionAmistad(String emisor, String receptor, boolean respuesta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> obtenerPeticionesAmistad(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
