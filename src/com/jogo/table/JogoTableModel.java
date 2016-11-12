/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.table;

import com.jogo.entity.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mizael
 */
public class JogoTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    private static final int COL_NOME = 0;
    private static final int COL_PONTOS = 1;

    public List<Usuario> valores;
    
    public JogoTableModel(List<Usuario> usuarios){
        this.valores = usuarios;
    }
    
    
    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return valores.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Usuario usuario = valores.get(rowIndex);
       if(columnIndex == COL_NOME){
           return usuario.getNome();
       }else if(columnIndex == COL_PONTOS){
           return usuario.getPontuacao();
       }
       return null;
    }
    
    @Override
    public String getColumnName(int column){
        String coluna = "";
        switch(column){
            case COL_NOME:
               coluna = "JOGADOR(A)";  
               break; 
            case COL_PONTOS:
                coluna = "PONTUAÇÂO";
                break;
            default:
                throw new IllegalAccessError("Coluna Invalida");
        }
        return coluna;
    }
    
    @Override
    public Class<?> getColumnClass(int ColumnIndex){
        if(ColumnIndex == COL_NOME){
            return String.class;
        }else if(ColumnIndex == COL_PONTOS){
            return Long.class;
        }
        return null;
    }
    
    public Usuario get(int row){
        return valores.get(row);
    }
}
