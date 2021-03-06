/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Usuario;
import servidor.InterfazServidor;

/**
 *
 * @author Usuario
 */
public class VOpciones extends javax.swing.JPanel {

    private InterfazServidor servidor;
    boolean flagNombre,flagContra;
    Usuario usuario;
    
    public VOpciones(InterfazServidor servidor, String usuario) throws RemoteException{
        this.servidor = servidor;
        this.usuario=new Usuario(usuario, this.servidor.obtenerContra(usuario));
                initComponents();
        this.nombreUsuario.setText(this.usuario.getNombreUsuario());
        this.contra.setText(this.usuario.getPassword());
        this.errorContra.setVisible(false);
        this.errorContraNueva.setVisible(false);
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
        nombreUsuario = new javax.swing.JTextField();
        botonConfirmar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        contra = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nuevaContra = new javax.swing.JLabel();
        fieldNuevaContra = new javax.swing.JPasswordField();
        nuevaContraRepe = new javax.swing.JLabel();
        fieldNuevaContraRepe = new javax.swing.JPasswordField();
        botonEliminar = new javax.swing.JButton();
        errorContra = new javax.swing.JLabel();
        errorContraNueva = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Opciones");

        nombreUsuario.setEditable(false);
        nombreUsuario.setText("Nombre de usuario");

        botonConfirmar.setText("Confirmar");
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        contra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contraKeyPressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre de usuario");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña actual");

        nuevaContra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nuevaContra.setText("Nueva contraseña");

        fieldNuevaContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldNuevaContraKeyPressed(evt);
            }
        });

        nuevaContraRepe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nuevaContraRepe.setText("Repetir la nueva contraseña");

        fieldNuevaContraRepe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldNuevaContraRepeKeyPressed(evt);
            }
        });

        botonEliminar.setText("Eliminar cuenta");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        errorContra.setForeground(new java.awt.Color(255, 51, 51));
        errorContra.setText("La contraseña actual no es correcta");

        errorContraNueva.setForeground(new java.awt.Color(255, 51, 51));
        errorContraNueva.setText("La contraseña actual no es correcta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contra)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonConfirmar))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldNuevaContra)
                    .addComponent(nuevaContra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldNuevaContraRepe)
                    .addComponent(nuevaContraRepe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorContra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorContraNueva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(contra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorContra)
                .addGap(8, 8, 8)
                .addComponent(nuevaContra)
                .addGap(18, 18, 18)
                .addComponent(fieldNuevaContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nuevaContraRepe)
                .addGap(18, 18, 18)
                .addComponent(fieldNuevaContraRepe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorContraNueva)
                .addGap(18, 18, 18)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        try {
            VAmigos graficos = new VAmigos(this.servidor,usuario);
            JFrame frame = new JFrame("Lista de amigos conectados");
            WindowListener exitListener = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int confirm = JOptionPane.showOptionDialog(
                            null, nombreUsuario.getText() + ", seguro que quieres cerrar sesión?",
                            "Salir", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == 0) {
                        try {
                            servidor.cerrarSesion(nombreUsuario.getText());
                        } catch (RemoteException ex) {
                            Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            frame.addWindowListener(exitListener);
            frame.add(graficos);
            frame.setVisible(true);
            frame.revalidate();        
            frame.pack();
            graficos.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(VOpciones.class.getName()).log(Level.SEVERE, null, ex);
        }
                            SwingUtilities.getWindowAncestor(this).dispose();

    }//GEN-LAST:event_botonCancelarActionPerformed

    
    private boolean comprobarCampos() throws RemoteException{
        boolean flag = true;
        if(!this.servidor.comprobarCredenciales(new Usuario(this.usuario.getNombreUsuario(), this.contra.getText()))){
            this.errorContra.setVisible(true);
            flag = false;
        }
        
        if(!this.fieldNuevaContra.getText().equals(this.fieldNuevaContraRepe.getText())){
            this.errorContraNueva.setVisible(true);
            flag = false;
        }
        this.repaint();
        SwingUtilities.getWindowAncestor(this).pack();
        return flag;
    }
    
    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        try {
            if(comprobarCampos()){
                try{
                    servidor.cambiarContrasena(usuario,this.fieldNuevaContra.getText());
                    VAmigos graficos = new VAmigos(this.servidor,usuario);
                    JFrame frame = new JFrame("Lista de amigos conectados");
                    WindowListener exitListener = new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            int confirm = JOptionPane.showOptionDialog(
                                    null, nombreUsuario.getText() + ", seguro que quieres cerrar sesión?",
                                    "Salir", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                            if (confirm == 0) {
                                try {
                                    servidor.cerrarSesion(nombreUsuario.getText());
                                } catch (RemoteException ex) {
                                    Logger.getLogger(VPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    };
                    frame.addWindowListener(exitListener);
                    frame.add(graficos);
                    frame.setVisible(true);
                    frame.revalidate();
                    frame.pack();
                    graficos.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(VOpciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VOpciones.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }//GEN-LAST:event_botonConfirmarActionPerformed

    private void contraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
             this.botonConfirmar.doClick();
   }  
        if(!flagContra){
        this.contra.setText("");
             flagContra=true;
}    }//GEN-LAST:event_contraKeyPressed

    private void fieldNuevaContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNuevaContraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNuevaContraKeyPressed

    private void fieldNuevaContraRepeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNuevaContraRepeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNuevaContraRepeKeyPressed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        try {
            this.servidor.cerrarSesion(usuario.getNombreUsuario());
            this.servidor.eliminarUsuario(usuario);
            SwingUtilities.getWindowAncestor(this).dispose();
            
        } catch (RemoteException ex) {
            Logger.getLogger(VOpciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.rmi.NotBoundException
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JPasswordField contra;
    private javax.swing.JLabel errorContra;
    private javax.swing.JLabel errorContraNueva;
    private javax.swing.JPasswordField fieldNuevaContra;
    private javax.swing.JPasswordField fieldNuevaContraRepe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JLabel nuevaContra;
    private javax.swing.JLabel nuevaContraRepe;
    // End of variables declaration//GEN-END:variables
}
