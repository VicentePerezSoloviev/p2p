package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    
    private final DAOUsuario daoUsuario;
    public ArrayList<Usuario> listaUsuariosConectados;
    
    public ImplementacionServidor () throws RemoteException {
        super();
        this.daoUsuario = new DAOUsuario();
        this.listaUsuariosConectados = new ArrayList<>();
        
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
    public boolean comprobarCredenciales (Usuario u) throws RemoteException {
        return this.daoUsuario.comprobarCredenciales(u);
    }

    @Override
    public boolean iniciarSesion(Usuario u) throws RemoteException{
        /*Comprobamos si el usuario ya esta en linea*/
        synchronized(listaUsuariosConectados) {
            for (Usuario s: this.listaUsuariosConectados){
                if (s.getNombreUsuario().equals(u.getNombreUsuario())){
                    System.out.println("El usuario ya esta en linea");
                    return false;
                }
            }
        }
        /*Comprobamos si existe el usuario que se pasa por parametro*/
        
        ArrayList<Usuario> array = this.listarUsuarios();
        
        for (Usuario array1 : array) {
            if (array1.getNombreUsuario().equals(u.getNombreUsuario()) && array1.getPassword().equals(u.getPassword())){
                synchronized(listaUsuariosConectados) {
                    this.listaUsuariosConectados.add(u);
                }
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cerrarSesion(String u) throws RemoteException{
            for (Usuario us: this.listaUsuariosConectados) {
                if (us.getNombreUsuario().equals(u)){
                    this.listaUsuariosConectados.remove(us);
                }
            }
        
        return true;
        
    }

    public ArrayList<Usuario> listarUsuariosConectados() {
        synchronized(listaUsuariosConectados) {
            return this.listaUsuariosConectados;
        }
    }

    @Override
    public ArrayList<String> listarAmigos(String u) throws RemoteException{
        return daoUsuario.listarAmigos(u);
    }

    
    @Override
    public ArrayList<Usuario> listarAmigosConectados(Usuario u) throws RemoteException{
        ArrayList<Usuario> usuariosConectados = new ArrayList<>(this.listarUsuariosConectados());
        for (Usuario us: usuariosConectados) System.out.println(us.getNombreUsuario());
        ArrayList<String> amigosUsuario = this.daoUsuario.listarAmigos(u.getNombreUsuario());
        
        if (usuariosConectados.contains(u)) usuariosConectados.remove(u);       //nunca se ejecuta??
        
        ArrayList<Usuario> arrayEliminar = new ArrayList<>();

        for (Usuario us: usuariosConectados) {
            if (!amigosUsuario.contains(us.getNombreUsuario())){
                arrayEliminar.add(us);
            }
        }

        for (Usuario us: arrayEliminar) {
            usuariosConectados.remove(us);
        }

        for (Usuario us: usuariosConectados) {
            u.getCliente().registrarAmigo(us);
        }

        return usuariosConectados;
    }

    @Override
    public void crearPeticionAmistad(String emisor, String receptor) throws RemoteException, SQLException{
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
    public String obtenerContra(String usuario) throws RemoteException{
        return this.daoUsuario.obtenerPassword(usuario);
    }
    
}
