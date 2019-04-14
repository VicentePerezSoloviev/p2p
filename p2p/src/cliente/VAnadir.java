/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import servidor.InterfazServidor;

/**
 *
 * @author Usuario
 */
public class VAnadir extends javax.swing.JPanel {

    /**
     * Creates new form VAnadir
     */
    
    private final String usuario;
    private final ImageIcon iconoMas;
    private InterfazServidor servidor;
    
    ArrayList<String> arrayUsuarios;
    
    ModeloTablaAmigos modelo;
    
    
    
    public VAnadir(InterfazServidor servidor, String usuario) throws RemoteException {
        
        this.servidor = servidor;
        this.usuario=usuario;
        arrayUsuarios = servidor.listarUsuariosString();
        iconoMas=new ImageIcon(this.getClass().getResource("/iconos/anadir.jpg"));
        
        initComponents();
        
        modelo = (ModeloTablaAmigos) this.tablaUsuarios.getModel();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        jTextField1.setText("Nombre de usuario");

        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N

        tablaUsuarios.setModel(new ModeloTablaAmigos(arrayUsuarios,iconoMas));
        tablaUsuarios.setFocusable(false);
        tablaUsuarios.setRowHeight(30);
        tablaUsuarios.setShowHorizontalLines(false);
        tablaUsuarios.setShowVerticalLines(false);
        tablaUsuarios.setTableHeader(null);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseReleased

        try {
            servidor.crearPeticionAmistad(usuario,modelo.getUsuario(tablaUsuarios.getSelectedRow()));
        } catch (RemoteException ex) {
            Logger.getLogger(VAnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaUsuariosMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
