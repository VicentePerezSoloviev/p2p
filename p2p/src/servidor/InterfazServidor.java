package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import modelo.Usuario;

public interface InterfazServidor extends Remote {
    
    /*Funcion para introducir un usuario nuevo en la base de datos
    Devuelve:
    - true: se ha insertado correctamente usuario en la base de datos
    - false: ha habido algun error y el usuario no se ha introducido correctamente*/
    public boolean introducirUsuario (Usuario usuario) throws RemoteException;
    
    
    /*Funcion para cambiar la contrasena de un usuario. Se le debe pasar el usuario con
    la contrasena vieja y un string con la nueva.
    Devuelve:
    - usuario con contrasena cambiada: en caso de exito
    - null: en caso de error*/
    public boolean cambiarContrasena (Usuario usuario, String nuevaContrasena) throws RemoteException;
    
    /*
    Devuelve la contraseña acutal del usuario
    -contra en caso exito
    - null en caso de error*/
    public String obtenerContra (String usuario) throws RemoteException;
    
    /*Funcion para eliminar un usuario de la base de datos de usuarios. Se le debe pasar
    el usuario entero.
    Devuelve:
    - true: en caso de exito
    - false: en caso de error*/
    public boolean eliminarUsuario (Usuario usuario) throws RemoteException;
    
    
    /*Funcion para listar todos los usuarios existentes en la base de datos
    Devuelve:
    - array con todos los usuarios en caso de exito
    - null en caso de error*/
    public ArrayList<Usuario> listarUsuarios () throws RemoteException;
    
    
    /*Funcion para añadir el usuario a la lista de usuarios conectados
    Tambien comprueba si existe en la BBDD con usuario y contrasena
    Devuelve:
    - true: en caso de exito
    - false: error*/
    public boolean iniciarSesion(Usuario u) throws RemoteException;
    
    
    /*Quitar de lista de usuarios conectados*/
    public boolean cerrarSesion(String u) throws RemoteException;
     
    
    /*Devuelve un array con la lista de amigos que esten conectados a partir de la tabla
    relacion entre amigos : grupos de amigos*/
    public ArrayList<String> listarAmigosConectados(Usuario u) throws RemoteException;
    
    
    /*Devuelve la lista de peticiones que todavia estan sin responder para el usuarii
    que se pasa por parametro*/
    public ArrayList<String> listarPeticionesPendientes (String usuario) throws RemoteException;
    
    
    /*Creamos peticion */
    public void crearPeticionAmistad(String emisor, String receptor) throws RemoteException;
    
    
    /*Responder B si o no
    Actualizar en bbdd*/
    public void responderPeticionAmistad(String emisor, String receptor, boolean respuesta) throws RemoteException;

    
    public ArrayList<String> listarUsuariosString () throws RemoteException;
    
    
    
    
}
