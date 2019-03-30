package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

public class DAOUsuario {
    
    private final String url = "jdbc:mysql://localhost:3306/practicap2p";
    
    private final String nombre = "practicap2p";
    private final String passwd = "practicap2p";
    
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    
    @SuppressWarnings("null")
    public boolean introducirUsuario (Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre, password) VALUES (" + usuario.getNombreUsuario() + "," + usuario.getPassword() + ")";
        boolean resultado=false;
        
        if (usuario == null) return false;       //el usuario no es valido
        
        try {
            ejecutarSentencia(query);
            resultado = true;
            conn.close();
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
        
        return resultado;
    }

    @SuppressWarnings("null")
    public Usuario cambiarContrasena(Usuario usuario, String nueva_contrasena) {
        String query = "UPDATE usuarios SET password='" + nueva_contrasena + "' WHERE nombre='" + usuario.getNombreUsuario() + "'";
        boolean resultado=false;
        
        if (usuario == null) return null;       //el usuario no es valido
        
        try {
            ejecutarSentencia(query);
            conn.close();
            
            /*Modificamos la contrasena del usuario para devolverlo corregido*/
            usuario.setPassword(nueva_contrasena);
            return usuario;
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
        
        return null;
    }

    @SuppressWarnings("null")
    public boolean eliminarUsuario(Usuario usuario) {
        String query = "DELETE FROM usuarios WHERE nombre='" + usuario.getNombreUsuario() + "'";
        
        boolean resultado=false;
        
        if (usuario == null) return false;       //el usuario no es valido
        
        try {
            ejecutarSentencia(query);
            resultado = true;
            conn.close();
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
        
        return resultado;
    }
    
    private void ejecutarSentencia(String query) throws SQLException {
        conn = DriverManager.getConnection(url, nombre, passwd);
        
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> array = new ArrayList <>();
        String query = "SELECT * FROM usuarios";
        Usuario u;
        String name, password;
        
        try {
            conn = DriverManager.getConnection(url, nombre, passwd);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                name = rs.getString("nombre");
                password = rs.getString("password");
                
                u = new Usuario (name, password);
                array.add(u);
            }
            
            st.close();
            
            return array;
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
        
        return null;
    }
    
    
    
}
