/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class ModeloTablaAmigos extends AbstractTableModel{

    private ArrayList<String> amigos=new ArrayList<>();
    ImageIcon icono;
    
    public ModeloTablaAmigos(ArrayList<String> amigos, ImageIcon icono){
        for(int i=0;i<amigos.size();i++) {
            this.amigos.add(amigos.get(i));
        }
        this.icono=icono;
    }
    
    @Override
    public int getRowCount() {
        return amigos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public Class getColumnClass(int column){
        if (column == 1) return ImageIcon.class; 
        return Object.class;
    }
    
    @Override
    public String getColumnName(int c){
        switch(c){
            case 0:
                return "Amigos";
            case 1:
                return "Chatear";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return this.amigos.get(rowIndex);
            case 1:
                return this.icono;
        }
        return null;
    }
    
    public void setFilas(ArrayList<String> amigos){
        if(!amigos.isEmpty()){
                    for(int i=0;i<amigos.size();i++) {
            this.amigos.add(amigos.get(i));
        }
        fireTableDataChanged();
        }

    }
   
    public void limpiarTabla(){
        amigos.clear();
    }
        
    public String getUsuario(int row){
        return this.amigos.get(row);
    }
    
}
