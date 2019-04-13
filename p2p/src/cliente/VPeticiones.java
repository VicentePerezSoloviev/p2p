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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import servidor.InterfazServidor;

/**
 *
 * @author Usuario
 */
public class VPeticiones extends javax.swing.JPanel {

    /**
     * Creates new form VPeticiones
     */
    
    private String usuario;
    private InterfazServidor servidor;
    private ArrayList<String> peticiones;
    private ImageIcon iconoAceptar, iconoRechazar;
    private ModeloTablaPeticiones modelo;

    
    public VPeticiones(InterfazServidor servidor, String usuario) throws RemoteException {
        this.servidor=servidor;
        this.usuario=usuario;
        peticiones=this.servidor.listarPeticionesPendientes(usuario);
        iconoAceptar=new ImageIcon(this.getClass().getResource("/iconos/aceptar.png"));
        iconoRechazar=new ImageIcon(this.getClass().getResource("/iconos/rechazar.png"));
        initComponents();
        modelo = (ModeloTablaPeticiones) this.tablaPeticiones.getModel();

        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPeticiones = new javax.swing.JTable();
        tablaPeticiones.setModel(new cliente.ModeloTablaPeticiones(peticiones,iconoAceptar,iconoRechazar));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PETICIONES DE AMISTAD PENDIENTES");

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        tablaPeticiones.setCellSelectionEnabled(true);
        tablaPeticiones.setFocusable(false);
        tablaPeticiones.setRowHeight(40);
        tablaPeticiones.setRowMargin(5);
        tablaPeticiones.setShowHorizontalLines(false);
        tablaPeticiones.setShowVerticalLines(false);
        tablaPeticiones.setTableHeader(null);
        tablaPeticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaPeticionesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPeticionesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPeticiones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonVolver)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(235, 235, 235)
                .addComponent(botonVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(56, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(56, 56, 56)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        try {
            VAmigos graficos = new VAmigos(this.servidor,usuario);
            JFrame frame = new JFrame("P2P");
            frame.add(graficos);
            frame.setVisible(true);
            frame.revalidate();
            frame.pack();
            graficos.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(VPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonVolverActionPerformed

    private void tablaPeticionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPeticionesMousePressed
    }//GEN-LAST:event_tablaPeticionesMousePressed

    private void tablaPeticionesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPeticionesMouseReleased
        try {
            switch (tablaPeticiones.getSelectedColumn()) {
                case 1:
                    servidor.responderPeticionAmistad(modelo.getUsuario(tablaPeticiones.getSelectedRow()), usuario, true);
                    break;
                case 2:
                    servidor.responderPeticionAmistad(modelo.getUsuario(tablaPeticiones.getSelectedRow()), usuario, false);
                    break;
                default:
                    String emisor = modelo.getUsuario(tablaPeticiones.getSelectedRow());
                    int input = JOptionPane.showConfirmDialog(null, "Do you like bacon?");
                    switch (input){
                        case 0:
                            servidor.responderPeticionAmistad(emisor, usuario, true);
                            break;
                        case 1:
                            servidor.responderPeticionAmistad(emisor, usuario, false);
                            break;
                        case 2:
                            break;
                    }   break;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaPeticionesMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPeticiones;
    // End of variables declaration//GEN-END:variables
}
