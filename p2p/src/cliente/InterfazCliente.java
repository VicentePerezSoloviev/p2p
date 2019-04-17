 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public interface InterfazCliente extends java.rmi.Remote{
       
    public void abrirConversacion(Usuario usuario1, Usuario usuario2)throws RemoteException;

    public void mostrarMensaje(String usuario, String mensaje)throws RemoteException;
    
    
    public void registrarAmigo (Usuario u) throws RemoteException;
}
