 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import java.util.HashMap;
import javax.swing.JFrame;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public interface InterfazCliente extends java.rmi.Remote{
    
    public Usuario getAmigo(String usuario) throws RemoteException;
       
    public void registrarAmigo (Usuario usuario) throws RemoteException;
    
    public void recbirMensaje (Usuario usuario, String mensaje) throws RemoteException;
        
    public void setGraficos (VAmigos graficos)throws RemoteException;
    
}
