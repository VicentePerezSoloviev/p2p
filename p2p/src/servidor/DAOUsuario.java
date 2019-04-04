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
        String query = "INSERT INTO usuarios (nombre, password) VALUES ('" + usuario.getNombreUsuario() + "','" + usuario.getPassword() + "')";
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
    
    public ArrayList<String> listarPeticiones (String usuario){
        ArrayList<String> array = new ArrayList<>();
        
        String query = "SELECT nombreB FROM AMG_PET WHERE nombreA='"+usuario+"' AND EST_PET=0;";
        String name;
        
        /*Cogemos todos los amigos cuando usuario es el nombreA*/
        
        try {
            conn = DriverManager.getConnection(url, nombre, passwd);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                name = rs.getString("nombreB");
                array.add(name);
            }
            
            st.close();
            
        }catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        /*Cogemos todos los amigos cuando usuario es el nombreB*/
        
        query = "SELECT nombreA FROM AMG_PET WHERE nombreB='"+usuario+"';";
        boolean check;
        
        try {
            conn = DriverManager.getConnection(url, nombre, passwd);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                name = rs.getString("nombreA");
                check = checkifExist(array, name);
                if (!check) array.add(name);
            }
            
            st.close();
            
        }catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        return array;
    }
    
    public ArrayList<String> listarAmigos (String usuario){
        ArrayList<String> array = new ArrayList<>();
        
        String query = "SELECT nombreB FROM AMG_PET WHERE nombreA='"+usuario+"' AND EST_PET=1;";
        String name;
        
        /*Cogemos todos los amigos cuando usuario es el nombreA*/
        
        try {
            conn = DriverManager.getConnection(url, nombre, passwd);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                name = rs.getString("nombreB");
                array.add(name);
            }
            
            st.close();
            
        }catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        /*Cogemos todos los amigos cuando usuario es el nombreB*/
        
        query = "SELECT nombreA FROM AMG_PET WHERE nombreB='"+usuario+"';";
        boolean check;
        
        try {
            conn = DriverManager.getConnection(url, nombre, passwd);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                name = rs.getString("nombreA");
                check = checkifExist(array, name);
                if (!check) array.add(name);
            }
            
            st.close();
            
        }catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        
        return array;
    }
    
    /*
    devuelve:
    true si existe
    false si no existe
    */
    private boolean checkifExist (ArrayList<String> array, String u) {
        for (String us: array) {
            if (us.equals(u)) return true;
        }
        return false;
    }

    public void crearPeticion(String emisor, String receptor) {
        String query = "INSERT INTO AMG_PET values ('"+emisor+"','"+receptor+"', 0);";
        
        try {
            ejecutarSentencia(query);
            conn.close();
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
    }

    public void responder(String emisor, String receptor, boolean respuesta) {
        int a;
        
        if (respuesta) a=1;
        else a=0;
        
        String query = "UPDATE AMG_PET SET EST_PET=" + a + " where (nombreA='"+emisor+"' and nombreB='"+receptor+"') or (nombreB='"+receptor+"' and nombreA='"+emisor+"');";
        
        try {
            ejecutarSentencia(query);
            conn.close();
          }
          catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
          }
    }
    
}


