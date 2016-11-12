/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.dao;

import com.jogo.entity.Perguntas;
import com.jogo.entity.Usuario;
import java.io.*;
import java.util.*;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mizae
 */
public class RepositorioDao implements IRepositorioDao{
    
    //Caminho para buscar os dados no excel.
    private static String PATH = "C://Jogo//Jogo.xlsx";
    
    //Workbook criar o excel
    Workbook wb;
    
    //caminho do arquivo do excel 
    private final File patch = new File(PATH);
    
    //IMPORTAR OS USUARIOS DA FOLHA 1 DO EXCEL
    @Override
    public List importar(){
        
        //arry de usuario para armazenar os meus usuarios que pega do excel na folha 1
        List<Usuario> usuarios = new ArrayList<>();
        
        //CAPTURA OS DADOS DO USUARIO NO EXCEL
        try{
            //capturando o excel para meu wb
            wb = WorkbookFactory.create(new FileInputStream(patch));
            
            //CAPTURAR A PRIMEIRA FOLHA DO EXCEL 
            Sheet folha = wb.getSheetAt(0);
            
            //criO um iterator para interagir com as linhas
            Iterator filaIterator = folha.rowIterator();
            
            //ENQUANTO HOUVER LINHAS O ITERATOR ME TRAZ.
            while(filaIterator.hasNext()){
                
                //CAPTURO A LINHA DO EXCEL
                Row linha = (Row) filaIterator.next();
                
                //CRIO UM INTERATOR PARA INTERAGIR COM AS COLUNAS
                Iterator colunaIterator = linha.cellIterator();
                
                //CRIOU A CLASSE DE USUARIO E ADD DENTRO DO MEU ARRAY
                Usuario user = new Usuario();
                usuarios.add(user);
                
                //ENAUQNTO HOUVER COLUNAS O INTERATOR ME TRAZ.
                while(colunaIterator.hasNext()){
                    
                    //COM A LINHA E A COLUNA JA POSSO CRIAR UMA CELULA.
                    Cell celula = (Cell) colunaIterator.next();
                    
                    //APOS CAPTURAR O VALOR NA CELULA, SETO PARA MINHA CLASSE USUARIO QUE CRIEI LOGO ACIMA.
                    if(celula!=null){ 
                        //CAPTURO O TIPO DA CELULA, NESSE CASO E STRING E NUMERICO(INT)
                        switch(celula.getCellType()){
                            case Cell.CELL_TYPE_NUMERIC:
                                //CONVERTENDO O VALOR PARA INTEIRO.
                                user.setPontuacao((int)Math.round(celula.getNumericCellValue()));
                                break;
                            case Cell.CELL_TYPE_STRING:
                                user.setNome(celula.getStringCellValue());
                                break;
                            
                        }
                        
                    }
                    
                }
                
            }  
            
        }catch(IOException | InvalidFormatException | EncryptedDocumentException e){}
        return usuarios;
    }
    
    //IMPORTAR AS PERGUNTAS(O INDEX SIGNIFICA A FOLHA,
   //PARA SABER QUE TIPO DE CATEGORIA QUERO BUSCAR).
    @Override
    public List importarPerguntas(int index){
        
        //CRIO UM ARRAY DE PERGUNTAS PARA ARMAZENAR AS PERGUNTAS BUSCAS DO EXCEL.
        List<Perguntas> perguntas = new ArrayList<>();
        
        try{
            
            //CAPTURANDO O EXCEL PARA MEU WB.    
            wb = WorkbookFactory.create(new FileInputStream(patch));

            //CAPTURO A FOLHA DO EXCEL PASSANDO O INDEX
            Sheet folha = wb.getSheetAt(index);
            
            //CRIO UM ITERATOR PARA INTERAGIR COM AS LINHAS.
            Iterator filaIterator = folha.rowIterator();

            //ENQUANTO HOUVER INTERAÇÃO PEGA UMA LINHA.
            while(filaIterator.hasNext()){

                //CAPTURO A LINHA DO EXCEL
                Row linha = (Row) filaIterator.next();
                //CRIO UM ITERATOR PARA PEGAR AS COLUNAS
                Iterator colunaIterator = linha.cellIterator();
                
                //AQUI DIGO QUE MINHAS COLUNAS NÃO PODE PASSAR DE 6, COMO TA A MINHA ESTRUTURA PARA O EXCEL
                //1 - PERGUNTA, 2- ALTERNATIVA, 3- ALTERNATIVA, 4- ATLTERNATIVA, 5- ALTERNATIVA, 6- RESPOSTA
                //CHEGOU MAIOR QUE 6 SAIU DO LOOP DE COLUNAS.
                if(linha.getLastCellNum() > 6){
                    break;
                }

                //CRIOU A CLASSE DE PERGUNTAS E ADD DENTRO DO MEU ARRAY
                Perguntas per = new Perguntas();
                perguntas.add(per);

                //INTERAGIR COM AS COLUNAS, PEGAR AS COLUNAS DO EXCEL
                while(colunaIterator.hasNext()){
                    
                    //TENDO A LINHA E COLUNA JA POSSO TER UMA CELULA.
                    Cell celula = (Cell) colunaIterator.next();

                    //APOS CAPTURAR O VALOR NA CELULA, SETO PARA MINHA CLASSE PERGUNTAS QUE CRIEI LOGO ACIMA.
                    if(celula!=null){ 
                        //CAPTURAR O TIPO DA CELULA, NO CASO TODAS AS PERGUNTAS E ALTERNATIVAS SÃO STRINGS.
                        //OBS: OLHE QUE NESSE CASO SÓ POSSO TRAZER STRING'S CASO CONTRARIO NÃO IRÁ FUNCIONAR.:/
                        switch(celula.getCellType()){
                            case Cell.CELL_TYPE_STRING:
                        switch (celula.getColumnIndex()) {
                            case 0:
                                per.setPergunta(celula.getStringCellValue());
                                break;
                            case 1:
                                per.setAlt1(celula.getStringCellValue());
                                break;
                            case 2:
                                per.setAlt2(celula.getStringCellValue());
                                break;
                            case 3:
                                per.setAlt3(celula.getStringCellValue());
                                break;
                            case 4:
                                per.setAlt4(celula.getStringCellValue());
                                break;
                            case 5:
                                per.setResposta(celula.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                        }

                    }

                }  
            }   
        }catch(IOException | InvalidFormatException | EncryptedDocumentException e){}
        return perguntas;
    }
    
    //SALVAR O EXCEL COM OS USUARIOS E PERGUNTAS.
    //PASSANDO TODO QUE CAPTUREI DO EXCEL NO IMPORTE E TODAS AS FOLHAS PASSANDO DE UMA SÓ VEZ, PARA SALVAR TODOS JUNTOS.
    public void exportar(File arquivo,List<Usuario> Usuario,List<Perguntas> capAmerica,
        List<Perguntas> oSenhorAneis,List<Perguntas> batmam,List<Perguntas> veloFur
            ,List<Perguntas> harryPotter,List<Perguntas> instrMortais,List<Perguntas> cronicNarnia,
            List<Perguntas> varios){
        //CRIO MEU ARQUIVO DE EXCEL. INSTANCIOANDO DE XSSFWorkbook
        wb = new XSSFWorkbook();
        
        try {
            
            //SALVAR A FOLHA 1 ONDE ESTÃO OS USUARIOS.    
            Sheet folha = wb.createSheet("USUARIO");
            int contador = 0;
            
            //CRIO UM FORACH DE ARRAY LIST DE USUARIO
            for(Usuario u : Usuario){
                
                //CRIO A LINHA PASSANDO O CONTADOR(INDEX DA LINHA) E CAPTURO A LINHA
                Row fila = folha.createRow(contador);
                
                //ENCREMENTO O CONTADOR PARA IR PARA A PROXIMA LINHA.
                contador++;
                
                //AGORA USO UM FOR PARA PEGAR AS COLUNAS, QUE SÃO DUAS COLUNAS.
                for (int coluna = 0; coluna < 2; coluna++) {
                    
                    //TENDO A LINHA E COLUNA JA POSSO TER MINHA CELULA.
                    Cell celula = fila.createCell(coluna);
                    //SE A COLUNA FOR A 0 EU ADD O NOME NELA, SENAO ADD A PONTUAÇÃO.
                    //FICARIA COLUNA 1 - NOME , COLUNA 2 - PONTUAÇÃO.
                    if(coluna==0){
                        celula.setCellValue(u.getNome());
                    }else{
                        celula.setCellValue(u.getPontuacao());
                    }
                    //SE O ARQUIVO NÃO EXISTIR ELE CRIAR E SE JÁ EXISTIR ELE SUBSTITUI.
                    //NESSE CASO O ARQUIVO SEMPRE EXISTIRAR, PÓIS É NELE QUE ESTÁ AS PERGUNTAS SALVAS.
                    if(!arquivo.exists()){
                        wb.write(new FileOutputStream(arquivo));
                    }else{
                        wb.write(new FileOutputStream(arquivo));
                    }

                }
            }
            //CHAMANDO O METODO E PASSANDO O INDEX PARA CADA FOLHA ONDE SERA SALVA.
            exportarPergunta(1, capAmerica);
            exportarPergunta(2, oSenhorAneis);
            exportarPergunta(3, batmam);
            exportarPergunta(4, veloFur);
            exportarPergunta(5, harryPotter);
            exportarPergunta(6, instrMortais);
            exportarPergunta(7, cronicNarnia);
            exportarPergunta(8, varios);
            wb.write(new FileOutputStream(arquivo));
            } catch (Exception e) {
        }    
    }
    //ESSE METODO SERVE PARA EXPORTAR VARIAS PERGUNTAS.
    public void exportarPergunta(int index,List<Perguntas> perguntas){
        //NOMEAR AS FOLHAS NO EXCEL COM O NOME DA CATEGORIA.
        String nome = "";
        switch(index){
            case 1:
                nome = "CAPITÃO AMERICA";
                break;
            case 2:
                nome = "O SENHOR DOS ANÉIS";
                break;
            case 3:
                nome = "BATMAN";
                break;
            case 4:
                nome = "VELOZES E FURIOSOS";
                break;
            case 5:
                nome = "HARRY POTTER";
                break;
            case 6:
                nome = "OS INSTRUMENTOS MORTAIS";
                break;
            case 7:
                nome = "AS CRONICAS DE NARNIA";
                break;
            case 8:
                nome = "VARIOS FILMES";
                break;    
        }
        
        //IGUAL AO PASSO DO USUARIO, AGORA SENDO COM AS PERGUNTAS.
        Sheet folha2 = wb.createSheet(nome);
            int contador2 = 0;
            for(Perguntas p : perguntas){

                Row fila2 = folha2.createRow(contador2);
                contador2++;
                for (int coluna2 = 0; coluna2 < 6; coluna2++) {
                    Cell celula2 = fila2.createCell(coluna2);

                    switch (coluna2) {
                        case 0:
                            celula2.setCellValue(p.getPergunta());
                            break;
                        case 1:
                            celula2.setCellValue(p.getAlt1());
                            break;
                        case 2:
                            celula2.setCellValue(p.getAlt2());
                            break;
                        case 3:
                            celula2.setCellValue(p.getAlt3());
                            break;
                        case 4:
                            celula2.setCellValue(p.getAlt4());
                            break;
                        case 5:
                            celula2.setCellValue(p.getResposta());
                            break;
                        default:
                            break;
                    }
                }
            }
        
    }
    
    //INSERIR USUARIOS NA FOLHA 1 DO EXCEL.
    //PRECISO CAPTUDO TUDO DO MEU EXCEL, ADD O QUE QUERO E SALVAR TUDO NOVAMENTE.
    @Override
    public int cadastro(Usuario usuario){
  
       List<Usuario> usuarios = importar();
       
       //PRECISO IMPORTAR TUDO DO EXCEL, ADD O QUE QUERO E SALVO NOVAMENTE.
       List<Perguntas> capAmerica = importarPerguntas(1);
       List<Perguntas> oSenhorAneis = importarPerguntas(2);
       List<Perguntas> batmam = importarPerguntas(3);
       List<Perguntas> veloFur = importarPerguntas(4);
       List<Perguntas> harryPotter = importarPerguntas(5);
       List<Perguntas> instrMortais = importarPerguntas(6);
       List<Perguntas> cronicNarnia = importarPerguntas(7);
       List<Perguntas> varios = importarPerguntas(8);
       
        //CHAMAR O METODO VERIFICAR PARA SABER SE O NOME DO USUARIO JÁ EXISTE.
        if(verificar(usuario, usuarios) == true){
           return 0;
        }else{
            usuarios.add(usuario);
            //DEPOIS DE IMPORTAR TUDO DO EXCEL E ADD, SALVO ELE.
            ////EXPORTO TUDO PARA MEU EXCEL NOVAMENTE.
            exportar(new File(PATH),usuarios, capAmerica,oSenhorAneis,batmam,veloFur,
                    harryPotter,instrMortais,cronicNarnia,varios);
        }
        return 1;
    }
    
    //ATUALIZAR AS PONTUAÇÕES AO FINAL DA PARTIDA.
    //PRECISO TRAZER TUDO DO EXCEL, ALTERAR O QUE QUERO E SALVAR TODOS NOVAMENTE.
    @Override
    public void update(Usuario usuario){
       File file = new File(PATH);
       List<Usuario> usuarios = importar();
       //PRECISO IMPORTAR TUDO DO EXCEL, ADD O QUE QUERO E SALVO NOVAMENTE.
       List<Perguntas> capAmerica = importarPerguntas(1);
       List<Perguntas> oSenhorAneis = importarPerguntas(2);
       List<Perguntas> batmam = importarPerguntas(3);
       List<Perguntas> veloFur = importarPerguntas(4);
       List<Perguntas> harryPotter = importarPerguntas(5);
       List<Perguntas> instrMortais = importarPerguntas(6);
       List<Perguntas> cronicNarnia = importarPerguntas(7);
       List<Perguntas> varios = importarPerguntas(8);
       for(Usuario u : usuarios){
           if(u.getNome().equalsIgnoreCase(usuario.getNome()) && usuario.getPontuacao() > u.getPontuacao()){
            u.setPontuacao(usuario.getPontuacao());
            break;

           } 
       }
       //DEPOIS DE IMPORTAR TUDO DO EXCEL E ATUALIZAR, SALVO ELE.
       //EXPORTO TUDO PARA MEU EXCEL NOVAMENTE.
       exportar(new File(PATH),usuarios, capAmerica,oSenhorAneis,batmam,veloFur,
                    harryPotter,instrMortais,cronicNarnia,varios);
        
    }
    
    //METODO VERIFICAR PARA SABER SE O USUARIO JA EXISTE, CASO EXISTA RETORNA TRUE.
    public boolean verificar(Usuario usuario, List<Usuario> usuarios){
        for(Usuario u : usuarios){
            if(u.getNome().equalsIgnoreCase(usuario.getNome())){
                return true;
            }
        }
        return false;
    }
}
