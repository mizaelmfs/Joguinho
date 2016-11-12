/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.facade;

import com.jogo.dao.IRepositorioDao;
import com.jogo.dao.RepositorioDao;
import com.jogo.entity.Perguntas;
import com.jogo.entity.Usuario;
import java.io.File;
import java.util.List;

/**
 *
 * @author Mizael
 */
public class JogoFacade {
    
    private IRepositorioDao dao;
    
    public JogoFacade(){
        this.dao = new RepositorioDao();
    }

    public List importar() {
        return this.dao.importar();
    }

    public List importarPerguntas(int index) {
       return this.dao.importarPerguntas(index);
    }
    
    public int cadastro(Usuario usuario) {
        return this.dao.cadastro(usuario);
    }

    public void update(Usuario usuario) {
        this.dao.update(usuario);
    }
     
}
