package modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    private final String nombreUsuario;
    private String password;
    
    public Usuario (String n, String p) {
        this.nombreUsuario = n;
        this.password = p;
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
    
    

    
    
}
