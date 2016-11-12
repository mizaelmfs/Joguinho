/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.controller;

import com.jogo.entity.Usuario;
import com.jogo.facade.JogoFacade;
import java.util.List;

/**
 *
 * @author Mizael
 */
public class JogoController{
        
    private JogoFacade controller;
   
    public JogoController(){
        this.controller = new JogoFacade();
    }
    
    public List ListarUsuario() {
        return this.controller.importar();
    }

    public List ListarPerguntas(int index) {
        return this.controller.importarPerguntas(index);
    }


    public int criarUsuario(Usuario usuario) {
        return this.controller.cadastro(usuario);
    }

    public void atualizarPontos(Usuario usuario) {
        this.controller.update(usuario);
    }
    
}
