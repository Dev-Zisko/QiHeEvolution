/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Persistencia.BeneficiariosXml;
import Persistencia.FechasXml;
import controller.Controladora;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Christian D
 */
public class VentanaRegistrarAsistentes extends javax.swing.JFrame {
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
    BeneficiariosXml datos = new BeneficiariosXml();
    FechasXml datos2 = new FechasXml();
    Controladora control;
    String nombreEvento, fecha, tipo;
    
    public VentanaRegistrarAsistentes(String nombreEvento, String fecha, String tipo) {
        initComponents();
        this.nombreEvento=nombreEvento;
        this.fecha=fecha;
        this.tipo = tipo;
        control = new Controladora (this, panelPrincipal);
        control.IconoVentana(this);
        control.IniciarCursor(this);
        control.LlenarTablaBeneficiariosAsistentes(tablaBeneficiarios, datos.todosLosBeneficiarios());
    }

    public VentanaRegistrarAsistentes() {
        
    }

   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        panelPrincipal = new interfaz.personalizados.PanelFondo();
        scrollPanelBeneficiarios = new javax.swing.JScrollPane();
        tablaBeneficiarios = new javax.swing.JTable();
        registrarAsistentes = new javax.swing.JButton();
        atras = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Asistentes");
        getContentPane().setLayout(new java.awt.CardLayout());

        scrollPanelBeneficiarios.setOpaque(false);

        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionar", "Nombre", "Correo", "Teléfono", "Fecha de Ingreso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanelBeneficiarios.setViewportView(tablaBeneficiarios);

        registrarAsistentes.setBackground(new java.awt.Color(51, 153, 0));
        registrarAsistentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrarAsistentes.setForeground(new java.awt.Color(255, 255, 255));
        registrarAsistentes.setText("Registrar Asistentes");
        registrarAsistentes.setBorder(null);
        registrarAsistentes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrarAsistentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarAsistentesActionPerformed(evt);
            }
        });

        atras.setBackground(new java.awt.Color(51, 153, 0));
        atras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        atras.setForeground(new java.awt.Color(255, 255, 255));
        atras.setText("Atrás");
        atras.setBorder(null);
        atras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atrasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollPanelBeneficiarios, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(registrarAsistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(registrarAsistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPanelBeneficiarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMouseClicked
        VentanaPrincipal ventana = new VentanaPrincipal();
        control.ActivarVentana(ventana, this);
        if(tipo.equals("Seminario"))
        control.IntercambiarPaneles(ventana.panelSeminarios, ventana.panelBeneficiarios, ventana.panelIngresosyEgresos, ventana.panelSesiones, ventana.panelSeminariosD); 
        if(tipo.equals("Sesion"))
          control.IntercambiarPaneles(ventana.panelSesiones, ventana.panelBeneficiarios, ventana.panelIngresosyEgresos, ventana.panelSeminarios,ventana.panelSeminariosD);    
    }//GEN-LAST:event_atrasMouseClicked

    private void registrarAsistentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarAsistentesActionPerformed
          try {
            if(tablaBeneficiarios.getSelectedRowCount()!=0){
            control.DespejarTablaBeneficiariosCheck(tablaBeneficiarios, datos, datos2, nombreEvento,fecha,tipo);
            JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.");
            }
            else
            JOptionPane.showMessageDialog(null, "Seleccione al menos una persona para continuar.");
        } catch (ParseException ex) {
            Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }//GEN-LAST:event_registrarAsistentesActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistrarAsistentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private interfaz.personalizados.PanelFondo panelPrincipal;
    private javax.swing.JButton registrarAsistentes;
    private javax.swing.JScrollPane scrollPanelBeneficiarios;
    public javax.swing.JTable tablaBeneficiarios;
    // End of variables declaration//GEN-END:variables
}
