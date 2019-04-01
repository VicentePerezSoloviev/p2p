/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author Usuario
 */
public interface InterfazCliente {
    
    /* Funcion para comprobar al principio de la conexión los amigos conectados de cada usuario */
    public void amigosConectados(String [] nombres);
    
    /*Funcion para notificar una nueva conexión de un amigo de usuario una vez la aplicación se está ejecutando, se ha de usar en bucle*/
    public void notificarAmigoConectado(String nombre);
    
    /* */
    
    public void peticionAmistad(String emisor);
    
    
    
}
