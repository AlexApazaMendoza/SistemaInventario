/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaz;

import Conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class Usuario extends javax.swing.JFrame {

    /** Creates new form Usuario */
    public Usuario() {
        initComponents();
    }
    
    public boolean band=false;
    public String codReporte;
    public String codigoUsuario;
    
    public void obtenerCodigo(String codigo){
        codigoUsuario=codigo;
    }
    
    /**/
    public void Listar(){
        DefaultTableModel modelo=(DefaultTableModel) jTableListaEquipos.getModel();
        modelo.setRowCount(0);//limpiar el modelo
        try{
            Conectar cnx = new Conectar();
            Connection registros = cnx.getConnection();
            String sql="select Equipo.codEquipo,TipoDeEquipo.nombreEquipo,Equipo.estado,Equipo.descripcion,Area.nombreArea\n" +
            "from Equipo\n" +
            "inner join TipoDeEquipo on Equipo.idTipoEquipo=TipoDeEquipo.idTipoEquipo\n" +
            "inner join Area on Equipo.codArea=Area.codArea";
            PreparedStatement st = registros.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                modelo.addRow(v);
            }  
            jTableListaEquipos.setModel(modelo);
        }catch(SQLException e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public void Buscar(){
        DefaultTableModel modelo=(DefaultTableModel) jTableListaEquipos.getModel();
        modelo.setRowCount(0);//limpiar el modelo
        try{
            Conectar cnx = new Conectar();
            Connection registros = cnx.getConnection();
            String sql="select Equipo.codEquipo,TipoDeEquipo.nombreEquipo,Equipo.estado,Equipo.descripcion,Area.nombreArea\n" +
            "from Equipo\n" +
            "inner join TipoDeEquipo on Equipo.idTipoEquipo=TipoDeEquipo.idTipoEquipo\n" +
            "inner join Area on Equipo.codArea=Area.codArea\n" +
            "where Equipo.codEquipo='"+jTextFieldCodigo.getText()+"'";
            PreparedStatement st = registros.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                modelo.addRow(v);
            }  
            jTableListaEquipos.setModel(modelo);
        }catch(SQLException e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public void AñadirEquipos(){
        try{
                Conectar cnx = new Conectar();
                Connection registro = cnx.getConnection();
                String sql2="insert into Equipo_Reporte(codEquipo,codReporte,fecha,observacion) values(?,?,?,?);";
                PreparedStatement st2 = registro.prepareStatement(sql2);
                st2.setString(1, jTextFieldCodigoReporte.getText());
                st2.setString(2, codReporte);
                st2.setString(3, jTextFieldFecha.getText());
                st2.setString(4, jTextAreaObservacion.getText());        
                st2.executeQuery();
                JOptionPane.showMessageDialog(null, "Equipo añadido al reporte");
        }catch(SQLException e){
            System.out.println("Error :Añadir Equipos:"+e.getMessage());
        }
    }
    public void codigoReporte(){
        /*capturar el codigo del reporte*/
        try{
            Conectar cnxObtener = new Conectar();
            Connection cod = cnxObtener.getConnection();
            String sqlCod="select *from Reporte where codReporte = (select max(codReporte) from Reporte)";
            PreparedStatement stC = cod.prepareStatement(sqlCod);
            ResultSet rsC= stC.executeQuery();
            if(rsC.next()){
                codReporte=rsC.getString(1);
            }
        }catch(SQLException e){
            System.out.println("Error :codigoReporte:"+e.getMessage());
        }
    }
    public void AgregarAlReporte(){
        if(band==false){
            try{
                Conectar cnx = new Conectar();
                Connection reporte = cnx.getConnection();
                String sql="insert into Reporte(codReporte,codUsuario) values(Next value FOR Reporte_cod,?);";
                PreparedStatement st = reporte.prepareStatement(sql);
                st.setString(1,codigoUsuario);
                st.executeQuery();
            }catch(SQLException e){
                System.out.println("Error: "+e.getMessage());
            }
            codigoReporte();
            AñadirEquipos();
            JOptionPane.showMessageDialog(null, "Se añadió el equipo");
            band=true;
            //añado para que no editen la fecha
            jTextFieldFecha.setEditable(false);
        }else{
            AñadirEquipos();
            JOptionPane.showMessageDialog(null, "Se añadió el equipo");
        }
        
    }
    
    /**/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldCodigo = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaEquipos = new javax.swing.JTable();
        jButtonReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodigoReporte = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jButtonAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacion = new javax.swing.JTextArea();
        jButtonReportar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonResporteLista = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextFieldCodigo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jTextFieldCodigo.setForeground(java.awt.Color.red);

        jButtonBuscar.setBackground(java.awt.Color.red);
        jButtonBuscar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButtonBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jTableListaEquipos.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        jTableListaEquipos.setForeground(java.awt.Color.blue);
        jTableListaEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Estado", "Descripcion", "Area"
            }
        ));
        jTableListaEquipos.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(jTableListaEquipos);

        jButtonReset.setBackground(java.awt.Color.red);
        jButtonReset.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButtonReset.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReset.setText("REFRESCAR");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reportar Incidencia"));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("CODIGO:");

        jTextFieldCodigoReporte.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jTextFieldCodigoReporte.setForeground(java.awt.Color.red);

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("FECHA:");

        jTextFieldFecha.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jTextFieldFecha.setForeground(java.awt.Color.red);
        jTextFieldFecha.setToolTipText("");
        jTextFieldFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jButtonAgregar.setBackground(java.awt.Color.red);
        jButtonAgregar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButtonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAgregar.setText("AGREGAR AL REPORTE");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jTextAreaObservacion.setForeground(java.awt.Color.red);
        jTextAreaObservacion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacion);

        jButtonReportar.setBackground(java.awt.Color.red);
        jButtonReportar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButtonReportar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReportar.setText("REPORTAR");
        jButtonReportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("aa/mm/dd");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("DETALLA  EL PROBLEMA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldCodigoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReportar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAgregar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodigoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonReportar)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Algerian", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("REPORTE DE INCIDENCIAS");

        jButtonResporteLista.setBackground(java.awt.Color.red);
        jButtonResporteLista.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButtonResporteLista.setForeground(java.awt.Color.white);
        jButtonResporteLista.setText("Reportes");
        jButtonResporteLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResporteListaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonBuscar)
                        .addGap(57, 57, 57)
                        .addComponent(jButtonReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jButtonResporteLista)
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonResporteLista))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonReset))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        Buscar();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Listar();
    }//GEN-LAST:event_formWindowOpened

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
        Listar();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jButtonReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportarActionPerformed
        // TODO add your handling code here:
        Reporte r=new Reporte();
        r.obtenCodigo(codReporte);
        r.obtenUsuario(codigoUsuario);
        r.setVisible(true);    
    }//GEN-LAST:event_jButtonReportarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // TODO add your handling code here:
        AgregarAlReporte();
        //jTextFieldCodigoReporte.setText(" ");
        //jTextAreaObservacion.setText(" ");
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonResporteListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResporteListaActionPerformed
        // TODO add your handling code here:
        Registros re=new Registros();
        re.setVisible(true);
    }//GEN-LAST:event_jButtonResporteListaActionPerformed

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonReportar;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonResporteLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableListaEquipos;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldCodigoReporte;
    private javax.swing.JTextField jTextFieldFecha;
    // End of variables declaration//GEN-END:variables

}
