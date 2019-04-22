package modelo;

import cliente.ImplementacionCliente;
import cliente.InterfazCliente;
import cliente.VAmigos;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Usuario implements Serializable{
    private final String nombreUsuario;
    private String password;
    private InterfazCliente cliente;
    
    public Usuario (String n, String p) throws RemoteException {
        this.nombreUsuario = n;
        this.password = p;
    }
    
    public Usuario (String n) throws RemoteException {
        this.nombreUsuario = n;
    }
    
    public void setCliente (VAmigos graficos) throws RemoteException{
        this.cliente=new ImplementacionCliente(graficos);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public InterfazCliente getCliente(){
        return cliente;
    }   
}
