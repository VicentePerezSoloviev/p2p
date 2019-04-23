/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class ImplementacionCliente extends UnicastRemoteObject implements InterfazCliente {
    
    private VAmigos graficos;
    private ArrayList <Usuario> usuarios;
    
    public ImplementacionCliente(VAmigos graficos) throws RemoteException {
        super();
        usuarios = new ArrayList<>();
        this.graficos = graficos;
    }
    
    @Override
    public void registrarAmigo (Usuario usuario) throws RemoteException{
        if(!usuarios.contains(usuario)){
            usuarios.add(usuario);
        }
    }

//    @Override
//    public VChat abrirConversacion(Usuario usuario1, String usuario2, HashMap <String, VChat> conversaciones) throws RemoteException {
//                Usuario aux = this.getAmigo(usuario2);
//                graficos = new VChat(usuario1,aux, conversaciones);
//                JFrame frame = new JFrame("Chat con " + aux.getNombreUsuario());
//                frame.add(graficos);
//                frame.setVisible(true);
//                frame.revalidate();
//                frame.pack();
//                graficos.setVisible(true);
//                return graficos;
//    }
//
//    @Override
//    public void mostrarMensaje(String usuario, String mensaje) throws RemoteException {
//        graficos.mostrarMensaje(usuario, mensaje);
//    }

    @Override
    public Usuario getAmigo(String usuario) throws RemoteException {
        for(int i = 0; i< usuarios.size();i++){
            if(usuarios.get(i).getNombreUsuario().equals(usuario)){
                return usuarios.get(i);
            }
        }
        return null;
    }

    public void setGraficos (VAmigos graficos)throws RemoteException{
        this.graficos=graficos;
    }
    
    
    @Override
    public void recbirMensaje(Usuario usuario, String mensaje) throws RemoteException {
        this.graficos.recibirMensaje(usuario, mensaje);
    }

    @Override
    public void recibirArchivo(Usuario usuario, File archivo) {
        this.graficos.recibirArchivo(usuario, archivo);
    }
    
}
