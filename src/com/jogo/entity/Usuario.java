/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.entity;

/**
 *
 * @author mizae
 */
public class Usuario implements Comparable<Usuario> {
    
    private String nome;
    private int pontuacao;

    public Usuario(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }
    
    public Usuario(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public int compareTo(Usuario usuario) {
        if(this.getPontuacao() > usuario.pontuacao){
           return -1; 
        }else if(this.getPontuacao() < usuario.pontuacao){
            return 1;
        }
        return this.getNome().compareToIgnoreCase(usuario.getNome());
    }
    
}
