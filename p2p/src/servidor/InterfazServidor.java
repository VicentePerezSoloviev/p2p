package servidor;

import java.util.ArrayList;
import modelo.Usuario;

public interface InterfazServidor {
    
    /*
    Funcion para introducir un usuario nuevo en la base de datos
    Devuelve:
    - true: se ha insertado correctamente usuario en la base de datos
    - false: ha habido algun error y el usuario no se ha introducido correctamente
    */
    public boolean introducirUsuario (Usuario usuario);
    
    /*
    Funcion para cambiar la contrasena de un usuario. Se le debe pasar el usuario con
    la contrasena vieja y un string con la nueva.
    Devuelve:
    - usuario con contrasena cambiada: en caso de exito
    - null: en caso de error
    */
    public Usuario cambiarContrasena (Usuario usuario, String nuevaContrasena);
    
    /*
    Funcion para eliminar un usuario de la base de datos de usuarios. Se le debe pasar
    el usuario entero.
    Devuelve:
    - true: en caso de exito
    - false: en caso de error
    */
    public boolean eliminarUsuario (Usuario usuario);
    
    /*
    Funcion para listar todos los usuarios existentes en la base de datos
    Devuelve:
    - array con todos los usuarios en caso de exito
    - null en caso de error
    */
    public ArrayList<Usuario> listarUsuarios ();
    
    /*
    Funcion para añadir el usuario a la lista de usuarios conectados
    Tambien comprueba si existe en la BBDD con usuario y contrasena
    Devuelve:
    - true: en caso de exito
    - false: error
    */
    public boolean iniciarSesion(Usuario u);
    
    /*
    Quitar de lista de usuarios conectados
    */
    public boolean cerrarSesion(String u);
    
    /*
    Devuelve la lista de usuarios que tienen como amigo al usuario que se 
    pasa por parametro
    */
    public ArrayList<String> listarUsuariosConectados(String u);
    
    /*
    Devuelve un array con la lista de amigos que esten conectados a partir de la tabla
    relacion entre amigos : grupos de amigos
    */
    public ArrayList<String> listarAmigosConectados(String u);
    
    /*Creamos peticion */
    public void crearPeticionAmistad(String emisor, String receptor);
    
    /*
    Responder B si o no
    Actualizar en bbdd
    */
    public void responderPeticionAmistad(String emisor, String receptor, boolean respuesta);
    
    public ArrayList<String> obtenerPeticionesAmistad(String u);
    
    
    
    
    
}
