/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mizael
 */
public class JogoCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        int pontos = (int)table.getValueAt(row,1);
        
        if(pontos <=50){
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        }else{
            setBackground(null);
            setForeground(Color.BLACK);
        }
        
        if(isSelected){
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        }
        
      //table.getColumnModel().getColumn(0).setMaxWidth(200);
      //table.getColumnModel().getColumn(1).setMaxWidth(100);
        return this;
    }
    
}
