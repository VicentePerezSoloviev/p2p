/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JFrame;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class ImplementacionCliente extends UnicastRemoteObject implements InterfazCliente {
    
    private VChat graficos;
    
    public ImplementacionCliente() throws RemoteException {
        super();
    }

    @Override
    public void abrirConversacion(Usuario usuario1, Usuario usuario2) throws RemoteException {
        graficos = new VChat(usuario2,usuario1);
        JFrame frame = new JFrame("Chat con " + usuario1.getNombreUsuario());
        frame.add(graficos);
        frame.setVisible(true);
        frame.revalidate();
        frame.pack();
        graficos.setVisible(true);
    }

    @Override
    public void mostrarMensaje(Usuario usuario, String mensaje) throws RemoteException {
        graficos.mostrarMensaje(usuario.getNombreUsuario(), mensaje);
    }
    
}
