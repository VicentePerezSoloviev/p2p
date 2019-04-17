/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JFrame;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class ImplementacionCliente extends UnicastRemoteObject implements InterfazCliente {
    
    private VChat graficos;
    private ArrayList <Usuario> usuarios;
    
    public ImplementacionCliente() throws RemoteException {
        super();
        usuarios = new ArrayList<>();
    }
    
    @Override
    public void registrarAmigo (Usuario usuario){
        if(!usuarios.contains(usuario)){
            usuarios.add(usuario);
        }
    }

    @Override
    public void abrirConversacion(Usuario usuario1, String usuario2) throws RemoteException {
        for(int i = 0; i< usuarios.size();i++){
            if(usuarios.get(i).getNombreUsuario().equals(usuario2)){
                graficos = new VChat(usuarios.get(i),usuario1);
                JFrame frame = new JFrame("Chat con " + usuario1.getNombreUsuario());
                frame.add(graficos);
                frame.setVisible(true);
                frame.revalidate();
                frame.pack();
                graficos.setVisible(true);
            }
        }
 
    }

    @Override
    public void mostrarMensaje(String usuario, String mensaje) throws RemoteException {
        graficos.mostrarMensaje(usuario, mensaje);
    }
    
}
