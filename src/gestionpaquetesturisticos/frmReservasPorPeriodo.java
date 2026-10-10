/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionpaquetesturisticos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author auxil
 */
public class frmReservasPorPeriodo extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmReservasPorPeriodo.class.getName());

    /**
     * Creates new form frmInformePaquete
     */
    public frmReservasPorPeriodo() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tblInformesPaquetes.setAutoCreateRowSorter(true);
        crearInforme();
    }
    
    public void crearInforme(){
        String sql = "select pa.nombre, concat(pa.ciudad, ', ', pa.pais) destino, pa.precio, ve.fecha, em.nombre asesor, cl.nombre cliente from paquetes pa inner join ventas ve on pa.id_paquete = ve.paquete inner join empleados em on ve.empleado = em.id_empleado inner join clientes cl on ve.cliente = cl.id_cliente";
        
        DefaultTableModel modeloTabla = (DefaultTableModel) tblInformesPaquetes.getModel();
        modeloTabla.setRowCount(0);
        ((javax.swing.table.TableRowSorter<?>) tblInformesPaquetes.getRowSorter()).setComparator(2, java.util.Comparator.nullsLast(java.util.Date::compareTo));
        
        DecimalFormat formato = new DecimalFormat("#,###.00");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        try (Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql)) {

            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Date fecha = formatoFecha.parse(resultado.getString("fecha"));
                String nombre = resultado.getString("nombre");
                String destino = resultado.getString("destino");
                String precio = formato.format(resultado.getDouble("precio"));
                String asesor = resultado.getString("asesor");
                String cliente = resultado.getString("cliente");

                modeloTabla.addRow(new Object[]{nombre, destino, fecha, cliente, asesor, precio});
            }
            tblInformesPaquetes.setModel(modeloTabla);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    
    public void actualizarInforme(){
        String sql = "select pa.nombre, concat(pa.ciudad, ', ', pa.pais) destino, pa.precio, ve.fecha, em.nombre asesor, cl.nombre cliente from paquetes pa inner join ventas ve on pa.id_paquete = ve.paquete inner join empleados em on ve.empleado = em.id_empleado inner join clientes cl on ve.cliente = cl.id_cliente where ve.fecha between ? and ?";
        
        DefaultTableModel modeloTabla = (DefaultTableModel) tblInformesPaquetes.getModel();
        modeloTabla.setRowCount(0);
        ((javax.swing.table.TableRowSorter<?>) tblInformesPaquetes.getRowSorter()).setComparator(2, java.util.Comparator.nullsLast(java.util.Date::compareTo));
        
        DecimalFormat formato = new DecimalFormat("#,###.00");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        
        
        try (Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql)) {
                
            Date fechaInicio = dcInicio.getDate();
            Date fechaFinal = dcFinal.getDate();
            
            statement.setString(1, formatoFecha.format(fechaInicio));
            statement.setString(2, formatoFecha.format(fechaFinal));
            
            ResultSet resultado = statement.executeQuery();
            
            while (resultado.next()) {
                Date fecha = formatoFecha.parse(resultado.getString("fecha"));
                String nombre = resultado.getString("nombre");
                String destino = resultado.getString("destino");
                String precio = formato.format(resultado.getDouble("precio"));
                String asesor = resultado.getString("asesor");
                String cliente = resultado.getString("cliente");

                modeloTabla.addRow(new Object[]{nombre, destino, fecha, cliente, asesor, precio});
            }
            tblInformesPaquetes.setModel(modeloTabla);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInformesPaquetes = new javax.swing.JTable();
        dcInicio = new com.toedter.calendar.JDateChooser();
        dcFinal = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblInformesPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Destino", "Fecha", "Cliente", "Asesor", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tblInformesPaquetes);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dcFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        actualizarInforme();
    }//GEN-LAST:event_btnFiltrarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmReservasPorPeriodo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private com.toedter.calendar.JDateChooser dcFinal;
    private com.toedter.calendar.JDateChooser dcInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInformesPaquetes;
    // End of variables declaration//GEN-END:variables
}
