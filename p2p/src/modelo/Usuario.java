package modelo;

import cliente.ImplementacionCliente;
import cliente.InterfazCliente;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Usuario implements Serializable{
    private final String nombreUsuario;
    private String password;
    private InterfazCliente cliente;
    
    public Usuario (String n, String p) throws RemoteException {
        this.nombreUsuario = n;
        this.password = p;
        this.cliente=new ImplementacionCliente();
    }
    
    public Usuario (String n) throws RemoteException {
        this.nombreUsuario = n;
        this.cliente=new ImplementacionCliente();
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
