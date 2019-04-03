/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class ModeloTabla extends AbstractTableModel{

    private ArrayList<String> empresas=new ArrayList<>();
    private ArrayList<Double> valores=new ArrayList<>();
    
    public ModeloTabla(HashMap<String,Double> valores){
        Iterator it = valores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            empresas.add((String) pair.getKey());
            this.valores.add((Double) pair.getValue());
            it.remove();
        }
    }
    
    @Override
    public int getRowCount() {
        return empresas.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int c){
        switch(c){
            case 0:
                return "EMPRESA";
            case 1:
                return "VALORES";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return this.empresas.get(rowIndex);
            case 1:
                return this.valores.get(rowIndex);
        }
        return null;
    }
    
    public void setFilas(HashMap<String,Double> valores){
        Iterator it = valores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            empresas.add((String) pair.getKey());
            this.valores.add((Double) pair.getValue());
            it.remove();
        }
        fireTableDataChanged();
    }
   
    public void limpiarTabla(){
        empresas.clear();
        valores.clear();
    }
    
}
