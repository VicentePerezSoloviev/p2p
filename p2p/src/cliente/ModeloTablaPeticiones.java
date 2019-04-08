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
public class ModeloTablaPeticiones extends AbstractTableModel{

    private ArrayList<String> peticiones=new ArrayList<>();
    ImageIcon iconoAceptar,iconoRechazar;
    
    public ModeloTablaPeticiones(ArrayList<String> amigos, ImageIcon iconoAceptar, ImageIcon iconoRechazar){
        for(int i=0;i<amigos.size();i++) {
            this.peticiones.add(amigos.get(i));
        }
        this.iconoAceptar=iconoAceptar;
        this.iconoRechazar=iconoRechazar;
    }
    
    @Override
    public int getRowCount() {
        return peticiones.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int c){
        switch(c){
            case 0:
                return "Peticiones";
            case 1:
                return "Aceptar";
            case 2:
                return "Rechazar";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return this.peticiones.get(rowIndex);
            case 1:
                return this.iconoAceptar;
            case 2:
                return this.iconoRechazar;
        }
        return null;
    }
    
    public void setFilas(ArrayList<String> peticiones){
        if(!peticiones.isEmpty()){
                    for(int i=0;i<peticiones.size();i++) {
            this.peticiones.add(peticiones.get(i));
        }
        fireTableDataChanged();
        }

    }
   
    public void limpiarTabla(){
        peticiones.clear();
    }
    
}
