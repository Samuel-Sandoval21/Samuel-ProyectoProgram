package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class JFrameCanchasDeportivas extends JFrame {

    private Estacionamiento estacionamiento;
    private JComboBox<String> comboCancha;
    private JTextField txtNombre;
    private JSpinner spinnerPersonas;
    private JButton btnReservar;
    private JButton btnVolver;
    private JTable tablaCanchas;
    private DefaultTableModel modeloTabla;

    public JFrameCanchasDeportivas(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Reservar Canchas Deportivas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(106, 90, 205));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Canchas Deportivas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel de formulario de reserva
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelFormulario.setBackground(new Color(240, 240, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiquetas y campos
        JLabel lblCancha = new JLabel("Seleccione Cancha:");
        lblCancha.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblCancha, gbc);

        // En una implementación real, estas opciones vendrían del sistema
        String[] canchas = {
            "0 - Cancha de Fútbol 1",
            "1 - Cancha de Fútbol 2",
            "2 - Cancha de Baloncesto",
            "3 - Cancha de Tenis 1",
            "4 - Cancha de Tenis 2"
        };
        comboCancha = new JComboBox<>(canchas);
        comboCancha.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulario.add(comboCancha, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulario.add(txtNombre, gbc);

        JLabel lblPersonas = new JLabel("Cantidad de Personas:");
        lblPersonas.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(lblPersonas, gbc);

        spinnerPersonas = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
        spinnerPersonas.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulario.add(spinnerPersonas, gbc);

        // Botón de reservar
        btnReservar = new JButton("Reservar Cancha");
        btnReservar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReservar.setBackground(new Color(106, 90, 205));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnReservar, gbc);

        // Panel para la tabla de canchas
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Estado de Canchas Deportivas",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14)));

        // Crear tabla con estado de canchas
        String[] columnas = {"ID", "Tipo", "Capacidad", "Estado", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaCanchas = new JTable(modeloTabla);
        tablaCanchas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaCanchas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaCanchas.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tablaCanchas);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(128, 128, 128));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        panelInferior.add(btnVolver);

        // Añadir action listeners
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarCancha();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Listener para el combo de cancha
        comboCancha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar spinner según la capacidad de la cancha seleccionada
                String seleccion = (String) comboCancha.getSelectedItem();
                if (seleccion.contains("Fútbol")) {
                    spinnerPersonas.setModel(new SpinnerNumberModel(10, 1, 12, 1));
                } else if (seleccion.contains("Baloncesto")) {
                    spinnerPersonas.setModel(new SpinnerNumberModel(5, 1, 10, 1));
                } else if (seleccion.contains("Tenis")) {
                    spinnerPersonas.setModel(new SpinnerNumberModel(2, 1, 4, 1));
                }
            }
        });

        // Añadir paneles al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Actualizar tabla inicialmente
        actualizarTabla();

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private void reservarCancha() {
        // Obtener datos del formulario
        String seleccion = (String) comboCancha.getSelectedItem();
        int id = Integer.parseInt(seleccion.split(" - ")[0]);
        String nombre = txtNombre.getText().trim();
        int cantidadPersonas = (Integer) spinnerPersonas.getValue();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese su nombre",
                    "Dato Faltante",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean reservado = estacionamiento.reservarCanchaDeportiva(id, nombre, cantidadPersonas);

        if (reservado) {
            JOptionPane.showMessageDialog(this,
                    "Reserva exitosa de la cancha " + id,
                    "Reserva Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla con el nuevo estado
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error en la reserva. La cancha " + id + " no está disponible o la cantidad de personas excede la capacidad.",
                    "Error en Reserva",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // En una implementación real, estos datos vendrían del sistema
        if (estacionamiento != null) {
            // Simulación para la interfaz
            String[][] datos = {
                {"0", "Fútbol", "12", "Disponible", "Cancha de fútbol 12"},
                {"1", "Fútbol", "12", "Ocupada", "Cancha de fútbol 12"},
                {"2", "Baloncesto", "10", "Disponible", "Cancha de baloncesto"},
                {"3", "Tenis", "2", "Disponible", "Cancha de tenis 1"},
                {"4", "Tenis", "2", "Ocupada", "Cancha de tenis 2"}
            };

            for (String[] fila : datos) {
                modeloTabla.addRow(fila);
            }
        }
    }
}


/*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
/**
 * @param args the command line arguments
 */
//public static void main(String args[]) {
/* Set the Nimbus look and feel */
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameCanchasDeportivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCanchasDeportivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCanchasDeportivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCanchasDeportivas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
/*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCanchasDeportivas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
*/
