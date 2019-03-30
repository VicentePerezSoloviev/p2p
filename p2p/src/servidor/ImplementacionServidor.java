package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import modelo.Usuario;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    
    private final DAOUsuario daoUsuario;
    
    public ImplementacionServidor () throws RemoteException {
        super();
        this.daoUsuario = new DAOUsuario();
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
}
