/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jogo.view;

import com.jogo.telasplash.GameOver;
import com.jogo.controller.JogoController;
import com.jogo.entity.Perguntas;
import com.jogo.entity.Usuario;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mizael
 */
public class Jogar extends javax.swing.JFrame {

    /**
     * Creates new form Jogar
     */
    private Usuario usuario;
    private List<Perguntas> perguntas;
    private final int INDEX = 0;
    private String reposta;
    private int contPontos = 0;
    private int contadorPulos = 0;

    public Jogar(Usuario usuario, int index) {
        perguntas = new JogoController().ListarPerguntas(index);
        this.usuario = usuario;
        initComponents();
        embaralharPerguntas();
        iniPerguntas();
        ClassLoader loader = getClass().getClassLoader();
        setIconImage(Toolkit.getDefaultToolkit().createImage(loader.getResource("folder/ranked2.png")));
    }

    public void playSound() {
        try {
            URL url = getClass().getResource("/sound/Certo.wav");
            AudioClip audio = Applet.newAudioClip(url);
            audio.play();   
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void acertou() {
        playSound();
        contPontos += 10;
        lblPontos.setText("Pontos: " + contPontos);
        grupoPerguntas.clearSelection();
        iniPerguntas();
    }

    private void errou() {
        this.usuario.setPontuacao(contPontos);
        new JogoController().atualizarPontos(usuario);
        GameOver go = new GameOver();
        go.setVisible(true);
        go.setLocationRelativeTo(null);
        this.dispose();
    }

    private Jogar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void embaralharPerguntas() {
        Collections.shuffle(perguntas);
    }

    public void iniPerguntas() {
        lblErroPulo.setText("");
        if (!perguntas.isEmpty()) {

            Perguntas p = perguntas.get(INDEX);
            lblPergunta.setText(p.getPergunta());

            rbAlt2.setText(p.getAlt1().trim());
            rbAlt3.setText(p.getAlt2().trim());
            rbAlt4.setText(p.getAlt3().trim());
            rbAlt1.setText(p.getAlt4().trim());

            reposta = p.getResposta().trim();
            perguntas.remove(INDEX);
        } else {
            this.usuario.setPontuacao(contPontos);
            new JogoController().atualizarPontos(usuario);
            Ranked ranked = new Ranked();
            ranked.setVisible(true);
            ranked.setLocationRelativeTo(null);
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoPerguntas = new javax.swing.ButtonGroup();
        lblErroPulo = new javax.swing.JLabel();
        rbAlt1 = new javax.swing.JRadioButton();
        rbAlt2 = new javax.swing.JRadioButton();
        rbAlt3 = new javax.swing.JRadioButton();
        rbAlt4 = new javax.swing.JRadioButton();
        lblPontos = new javax.swing.JLabel();
        btnAbandonar = new javax.swing.JButton();
        btnPular = new javax.swing.JButton();
        lblPergunta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblErroPulo.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblErroPulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 140, 20));

        grupoPerguntas.add(rbAlt1);
        rbAlt1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbAlt1.setForeground(new java.awt.Color(255, 255, 255));
        rbAlt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlt1ActionPerformed(evt);
            }
        });
        getContentPane().add(rbAlt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        grupoPerguntas.add(rbAlt2);
        rbAlt2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbAlt2.setForeground(new java.awt.Color(255, 255, 255));
        rbAlt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlt2ActionPerformed(evt);
            }
        });
        getContentPane().add(rbAlt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        grupoPerguntas.add(rbAlt3);
        rbAlt3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbAlt3.setForeground(new java.awt.Color(255, 255, 255));
        rbAlt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlt3ActionPerformed(evt);
            }
        });
        getContentPane().add(rbAlt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        grupoPerguntas.add(rbAlt4);
        rbAlt4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbAlt4.setForeground(new java.awt.Color(255, 255, 255));
        rbAlt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlt4ActionPerformed(evt);
            }
        });
        getContentPane().add(rbAlt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        lblPontos.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblPontos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 100, 20));

        btnAbandonar.setText("Abandonar");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbandonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, -1));

        btnPular.setText("Pular");
        btnPular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPularActionPerformed(evt);
            }
        });
        getContentPane().add(btnPular, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));

        lblPergunta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPergunta.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblPergunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbAlt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlt2ActionPerformed
        // TODO add your handling code here:
        if (rbAlt2.getText().equalsIgnoreCase(reposta)) {
            acertou();
        } else {
            errou();
        }
    }//GEN-LAST:event_rbAlt2ActionPerformed

    private void rbAlt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlt3ActionPerformed
        // TODO add your handling code here:
        if (rbAlt3.getText().equalsIgnoreCase(reposta)) {
            acertou();
        } else {
          errou();
        }
    }//GEN-LAST:event_rbAlt3ActionPerformed

    private void rbAlt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlt4ActionPerformed
        if (rbAlt4.getText().equalsIgnoreCase(reposta)) {
            acertou();
        } else {
            errou();
        }
    }//GEN-LAST:event_rbAlt4ActionPerformed

    private void rbAlt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlt1ActionPerformed
        // TODO add your handling code here:
        if (rbAlt1.getText().equalsIgnoreCase(reposta)) {
            acertou();
        } else {
            errou();
        }
    }//GEN-LAST:event_rbAlt1ActionPerformed

    private void btnPularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPularActionPerformed
        // TODO add your handling code here:
        if (contadorPulos < 3) {
            contadorPulos++;
            contPontos -= 10;
            lblPontos.setText("Pontos: " + contPontos);
            grupoPerguntas.clearSelection();
            iniPerguntas();
        } else {
            lblErroPulo.setText("Só pular ate 3x");
        }
    }//GEN-LAST:event_btnPularActionPerformed

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        // TODO add your handling code here:
        //Reutilizando o metodo errou para abandonar as perguntas.
        errou();
    }//GEN-LAST:event_btnAbandonarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jogar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jogar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jogar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jogar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jogar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnPular;
    private javax.swing.ButtonGroup grupoPerguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblErroPulo;
    private javax.swing.JLabel lblPergunta;
    private javax.swing.JLabel lblPontos;
    private javax.swing.JRadioButton rbAlt1;
    private javax.swing.JRadioButton rbAlt2;
    private javax.swing.JRadioButton rbAlt3;
    private javax.swing.JRadioButton rbAlt4;
    // End of variables declaration//GEN-END:variables
}
