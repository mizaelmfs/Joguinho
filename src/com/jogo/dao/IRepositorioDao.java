/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.dao;

import com.jogo.entity.Usuario;
import java.util.List;

/**
 *
 * @author Mizael
 */
public interface IRepositorioDao {
    
    public List importar();
    
    public List importarPerguntas(int index);
    
    public int cadastro(Usuario usuario);
    
    public void update(Usuario usuario);
    
}
