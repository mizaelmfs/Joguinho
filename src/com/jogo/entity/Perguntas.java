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
public class Perguntas {
    
    private String pergunta;
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String resposta;

    public Perguntas(String pergunta, String alt1, String alt2, String alt3, String alt4, String resposta) {
        this.pergunta = pergunta;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
        this.resposta = resposta;
    }

    public Perguntas() {
    }
    
    

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getAlt1() {
        return alt1;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public String getAlt3() {
        return alt3;
    }

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    

}