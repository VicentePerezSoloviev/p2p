/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import modelo.Usuario;
import servidor.InterfazServidor;

/**
 *
 * @author Usuario
 */
public class VAmigos extends javax.swing.JPanel {

    /**
     * Creates new form VAmigos
     * @param usuario
     */
    
    private ImageIcon icono;
    private InterfazServidor servidor;
    private ArrayList<String> amigos;
    ModeloTablaAmigos modelo;
    Usuario usuario;
    HashMap <String, JFrame> conversacionesAbiertas;

    
    public VAmigos(InterfazServidor servidor,Usuario usuario) throws RemoteException {
                amigos = servidor.listarAmigosConectados(usuario);
                this.usuario = usuario;
        this.servidor=servidor;
        icono=new ImageIcon(this.getClass().getResource("/iconos/chat.png"));
        initComponents();
        this.nombreUsuario.setText(this.usuario.getNombreUsuario());
        modelo = (ModeloTablaAmigos) this.tablaAmigos.getModel();
        
            JPopupMenu menu = new JPopupMenu();
        JMenuItem opcion1 = new JMenuItem("Cambiar contraseña");
        
        opcion1.addActionListener((ActionEvent a) -> {
                    try {
                        VOpciones graficos = new VOpciones(this.servidor,usuario.getNombreUsuario());
                        JFrame frame = new JFrame("P2P");
                        frame.add(graficos);
                        frame.setVisible(true);
                        frame.revalidate();
                        frame.pack();
                        graficos.setVisible(true);
                        SwingUtilities.getWindowAncestor(this).dispose();
                    } catch (RemoteException ex) {
                        Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        
        
        JMenuItem opcion2 = new JMenuItem("Cerrar Sesión");
        
                opcion2.addActionListener((ActionEvent a) -> {
                    try {
                        servidor.cerrarSesion(usuario.getNombreUsuario());
                        System.exit(1);
                    } catch (RemoteException ex) {
                        Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        
        menu.add(opcion1); menu.add(opcion2);      
        
        this.botonOpciones.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        menu.show(botonOpciones, botonOpciones.getWidth()/2, botonOpciones.getHeight()/2);
                    }
                } );
        
        new Thread(){
                @Override
                public void run(){
                    while(true){
                            try {
                                sleep(5000);
                                synchronized(amigos){
                                    amigos=servidor.listarAmigosConectados(this.usuario);
                                }                                
                            } catch (IOException | InterruptedException ex) {
                                Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            modelo.limpiarTabla();
                            modelo.setFilas(amigos);
                            modelo.fireTableDataChanged();
                    }
                }
            }.start();
        
        conversacionesAbiertas = new HashMap();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonAnadirAmigo = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        nombreUsuario = new javax.swing.JLabel();
        circuloConectado = new javax.swing.JButton();
        botonPeticiones = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAmigos = new javax.swing.JTable();
        botonOpciones = new javax.swing.JButton();

        botonAnadirAmigo.setText("Añadir amigo");
        botonAnadirAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirAmigoActionPerformed(evt);
            }
        });

        jTextField1.setText("Nombre de amigo");

        nombreUsuario.setForeground(new java.awt.Color(0, 153, 0));
        nombreUsuario.setText("nombreUsuario");

        circuloConectado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/circuloVerde.png"))); // NOI18N
        circuloConectado.setBorder(null);
        circuloConectado.setBorderPainted(false);
        circuloConectado.setContentAreaFilled(false);
        circuloConectado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        circuloConectado.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        circuloConectado.setFocusPainted(false);
        circuloConectado.setFocusable(false);

        botonPeticiones.setText("Peticiones de amistad");
        botonPeticiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPeticionesActionPerformed(evt);
            }
        });

        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N

        tablaAmigos.setModel(new cliente.ModeloTablaAmigos(amigos,icono));
        tablaAmigos.setFocusable(false);
        tablaAmigos.setRowHeight(40);
        tablaAmigos.setRowMargin(5);
        tablaAmigos.setShowHorizontalLines(false);
        tablaAmigos.setShowVerticalLines(false);
        tablaAmigos.setTableHeader(null);
        tablaAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaAmigosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAmigos);

        botonOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/opciones.png"))); // NOI18N
        botonOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(botonAnadirAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(circuloConectado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonPeticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(circuloConectado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreUsuario)
                            .addComponent(botonPeticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(botonAnadirAmigo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonPeticionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPeticionesActionPerformed
        VPeticiones graficos;
        try {
            graficos = new VPeticiones(this.servidor,this.usuario);
                JFrame frame = new JFrame("Peticions de amistad pendientes");
        frame.add(graficos);
        frame.setVisible(true);
        frame.revalidate();
        frame.pack();
        graficos.setVisible(true);
                SwingUtilities.getWindowAncestor(this).dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonPeticionesActionPerformed

    private void botonAnadirAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirAmigoActionPerformed
        VAnadir graficos;
        try {
            graficos = new VAnadir(servidor,this.nombreUsuario.getText());
                    JFrame frame = new JFrame("Añadir amigos");
        frame.add(graficos);
        frame.setVisible(true);
        frame.revalidate();
        frame.pack();
        graficos.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonAnadirAmigoActionPerformed

    private void botonOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcionesActionPerformed
 
    }//GEN-LAST:event_botonOpcionesActionPerformed

    private void tablaAmigosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAmigosMouseReleased
        try {
            Usuario usuario2 = this.usuario.getCliente().getAmigo(modelo.getUsuario(this.tablaAmigos.getSelectedRow()));
            JFrame f;
            if((f = this.conversacionesAbiertas.get(usuario2.getNombreUsuario()))==null){         
                VChat graficos = new VChat(usuario,usuario2);
                JFrame frame = new JFrame("Chat con " + usuario2.getNombreUsuario());
                 WindowListener exitListener = new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            conversacionesAbiertas.remove(usuario2.getNombreUsuario());
                        }
                    };
                frame.addWindowListener(exitListener);
                frame.add(graficos);
                frame.setVisible(true);
                frame.revalidate();
                frame.pack();
                graficos.setVisible(true);
                this.conversacionesAbiertas.put(usuario2.getNombreUsuario(),frame);
            }
            else{
                f.setVisible(true);
                f.toFront();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VAmigos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaAmigosMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnadirAmigo;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonOpciones;
    private javax.swing.JButton botonPeticiones;
    private javax.swing.JButton circuloConectado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nombreUsuario;
    private javax.swing.JTable tablaAmigos;
    // End of variables declaration//GEN-END:variables
}
