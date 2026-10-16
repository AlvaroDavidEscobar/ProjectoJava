/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionpaquetesturisticos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


/**
 *
 * @author auxil
 */
public class frmAgregarVenta extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmAgregarVenta.class.getName());

    int tipoEmpleado;
    int idEmpleado;
    
    public frmAgregarVenta(int tipoEmpleado, int idEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
        this.idEmpleado = idEmpleado;
        initComponents();
        cargarPaquetes();
    }
    
    private int buscarClientePorDocumento(String documento)
        throws SQLException {

    String sql = "select id_cliente from clientes where documento = ?";

        try (Connection conexion = Conexion.conectar()) {
            if (conexion == null) {
                throw new SQLException("No se pudo conectar con MySQL.");
            }

            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, documento);

                try (ResultSet resultado = statement.executeQuery()) {
                    if (resultado.next()) {
                        return resultado.getInt("id_cliente");
                    }
                }
            }
        }
        
        return -1; // Cliente no encontrado
    }
    
    private void cargarPaquetes() {
        String sql = "SELECT id_paquete, nombre FROM paquetes ORDER BY nombre";

        cmbPaquete.removeAllItems();

        try (Connection conexion = Conexion.conectar()) {
            if (conexion == null) {
                JOptionPane.showMessageDialog(
                        this, "No se pudo conectar con MySQL."
                );
                return;
            }
        try (PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    int id = resultado.getInt("id_paquete");
                    String nombre = resultado.getString("nombre");

                    cmbPaquete.addItem(new Paquete(id, nombre));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los paquetes: " + e.getMessage()
            );
        }
    }
    
    private void agregarVenta() {
        String sql = "INSERT INTO ventas (cliente, paquete, empleado, fecha) "
                   + "VALUES (?, ?, ?, CURRENT_DATE)";

        if (cmbPaquete.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un paquete.");
            return;
        }

        try {
            String documento = txtCliente.getText().trim();

            if (documento.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresa el documento del cliente.");
                return;
            }

            int cliente = buscarClientePorDocumento(documento);

            if (cliente == -1) {
                JOptionPane.showMessageDialog(this,
                        "No existe un cliente con ese documento.");
                return;
            }

            Paquete paqueteSeleccionado = (Paquete) cmbPaquete.getSelectedItem();

            if (paqueteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un paquete.");
                return;
            }

            int paquete = paqueteSeleccionado.id;

            try (Connection conexion = Conexion.conectar()) {
                if (conexion == null) {
                    JOptionPane.showMessageDialog(
                            this, "No se pudo conectar con la base de datos."
                    );
                    return;
                }

                try (PreparedStatement statement =
                        conexion.prepareStatement(sql)) {

                    statement.setInt(1, cliente);
                    statement.setInt(2, paquete);
                    statement.setInt(3, idEmpleado);
                    System.out.println(cliente + " " + paquete + " " + idEmpleado);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(
                            this, "Venta registrada correctamente."
                    );

                    txtCliente.setText("");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "Asesor, cliente y paquete deben ser IDs numéricos."
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this, "Error al registrar la venta: " + e.getMessage()
            );
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

        btnAgregar = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        cmbPaquete = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregar.setText("Enviar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnAgregar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                        .addComponent(cmbPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(btnAgregar)
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas registrar esta venta?", "Confirmar venta", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            agregarVenta();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frmAgregarVenta(0, 0).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<Paquete> cmbPaquete;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
