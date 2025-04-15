package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JFrameMesasRecreativas extends JFrame {

    private Estacionamiento estacionamiento;
    private JComboBox<String> comboTipoMesa;
    private JTextField txtNombre;
    private JSpinner spinnerHora;
    private JSpinner spinnerMinuto;
    private JButton btnReservar;
    private JButton btnVolver;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

    public JFrameMesasRecreativas(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Reservar Mesas Recreativas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 165, 0));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Mesas Recreativas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel de formulario de reserva
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelFormulario.setBackground(new Color(255, 250, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiquetas y campos
        JLabel lblTipoMesa = new JLabel("Tipo de Mesa:");
        lblTipoMesa.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblTipoMesa, gbc);

        String[] tiposMesa = {"Ping Pong", "Billar"};
        comboTipoMesa = new JComboBox<>(tiposMesa);
        comboTipoMesa.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulario.add(comboTipoMesa, gbc);

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

        JLabel lblHora = new JLabel("Hora (HH:MM):");
        lblHora.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(lblHora, gbc);

        JPanel panelHora = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        spinnerHora = new JSpinner(new SpinnerNumberModel(8, 8, 22, 1));
        spinnerHora.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerHora.setPreferredSize(new Dimension(60, 25));

        spinnerMinuto = new JSpinner(new SpinnerNumberModel(0, 0, 59, 15));
        spinnerMinuto.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerMinuto.setPreferredSize(new Dimension(60, 25));

        JLabel lblSeparador = new JLabel(":");
        lblSeparador.setFont(new Font("Arial", Font.BOLD, 14));

        panelHora.add(spinnerHora);
        panelHora.add(lblSeparador);
        panelHora.add(spinnerMinuto);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulario.add(panelHora, gbc);

        // Botón de reservar
        btnReservar = new JButton("Reservar Mesa");
        btnReservar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReservar.setBackground(new Color(255, 140, 0));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnReservar, gbc);

        // Panel para la tabla de reservas
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Reservas Actuales",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14)));

        // Crear tabla con reservas
        String[] columnas = {"Tipo", "Fecha", "Hora Inicio", "Hora Fin", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaReservas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaReservas.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tablaReservas);
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
                reservarMesa();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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

    private void reservarMesa() {
        // Obtener datos del formulario
        String tipoMesa = (String) comboTipoMesa.getSelectedItem();
        String nombre = txtNombre.getText().trim();
        int hora = (Integer) spinnerHora.getValue();
        int minuto = (Integer) spinnerMinuto.getValue();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese su nombre",
                    "Dato Faltante",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear la fecha y hora para la reserva
        LocalDateTime horaReserva = LocalDateTime.now()
                .withHour(hora)
                .withMinute(minuto)
                .withSecond(0)
                .withNano(0);

        // IMPORTANTE: Aquí está el cambio principal - En lugar de usar la clase mesaRecreativa,
        // usamos el método reservarEspacioRecreacion de la clase Estacionamiento
        // para integrarlo con el sistema de reservas general
        // Determinar el índice del espacio recreativo según el tipo de mesa
        int espacioId = -1;
        if (tipoMesa.equals("Ping Pong")) {
            espacioId = 0; // ID para Ping Pong (asumiendo que es el espacio 0)
        } else if (tipoMesa.equals("Billar")) {
            espacioId = 1; // ID para Billar (asumiendo que es el espacio 1)
        }

        boolean reservaExitosa = false;
        if (espacioId >= 0) {
            // Usar el método del sistema para reservar el espacio recreativo
            reservaExitosa = estacionamiento.reservarEspacioRecreacion(espacioId, nombre, 2); // 2 personas típicas
        }

        if (reservaExitosa) {
            JOptionPane.showMessageDialog(this,
                    "Reserva exitosa de mesa de " + tipoMesa + " a las "
                    + String.format("%02d:%02d", hora, minuto),
                    "Reserva Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla con la nueva reserva
            actualizarTabla();

            // Limpiar campos
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error en la reserva. La mesa no está disponible en el horario seleccionado.",
                    "Error en Reserva",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Aquí deberíamos mostrar las reservas reales de mesas recreativas
        // Para hacer esto correctamente, necesitamos consultar las reservas generales
        // y filtrar solo las que corresponden a espacios recreativos
        if (estacionamiento != null) {
            String reservasStr = estacionamiento.getTodasLasReservasComoString();

            if (reservasStr != null && !reservasStr.contains("No hay reservas")) {
                // Procesar el string de reservas
                String[] lineas = reservasStr.split("\n");
                for (String linea : lineas) {
                    // Filtrar solo líneas que contienen información de reservas de espacios recreativos
                    if (linea.contains("Espacio Recreativo")
                            && (linea.contains("Ping Pong") || linea.contains("Billar"))) {
                        try {
                            // Extraer información relevante
                            // Ejemplo: "1. Espacio Recreativo #0 - Reservado por: Nombre - Personas: 2 - Desde: fecha hora - Hasta: fecha hora"

                            // Obtener tipo (Ping Pong o Billar)
                            String tipo = "";
                            if (linea.contains("#0")) {
                                tipo = "Ping Pong";
                            } else if (linea.contains("#1")) {
                                tipo = "Billar";
                            }

                            // Extraer fecha y horas
                            int desdeIndex = linea.indexOf("Desde: ") + 7;
                            int hastaIndex = linea.indexOf("Hasta: ") + 7;

                            if (desdeIndex > 7 && hastaIndex > 7) {
                                String fechaHoraDesde = linea.substring(desdeIndex, linea.indexOf(" - Hasta:"));
                                String fechaHoraHasta = linea.substring(hastaIndex);

                                String[] partesDesde = fechaHoraDesde.split(" ");
                                String[] partesHasta = fechaHoraHasta.split(" ");

                                if (partesDesde.length >= 2 && partesHasta.length >= 2) {
                                    String[] fila = {
                                        tipo,
                                        partesDesde[0], // Fecha
                                        partesDesde[1], // Hora inicio
                                        partesHasta[1], // Hora fin
                                        "Confirmada"
                                    };
                                    modeloTabla.addRow(fila);
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error procesando línea de reserva: " + e.getMessage());
                        }
                    }
                }
            }

            // Si no hay reservas mostradas, podríamos añadir un mensaje
            if (modeloTabla.getRowCount() == 0) {
                // En lugar de mostrar mensaje, mostramos algunas reservas de ejemplo para visualización
                DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

                LocalDateTime ahora = LocalDateTime.now();

                // Añadir algunas reservas de ejemplo
                String[] fila1 = {
                    "Ping Pong",
                    ahora.format(formatterFecha),
                    ahora.plusHours(1).format(formatterHora),
                    ahora.plusHours(3).format(formatterHora),
                    "Ejemplo"
                };
                modeloTabla.addRow(fila1);

                String[] fila2 = {
                    "Billar",
                    ahora.plusDays(1).format(formatterFecha),
                    ahora.plusDays(1).plusHours(2).format(formatterHora),
                    ahora.plusDays(1).plusHours(4).format(formatterHora),
                    "Ejemplo"
                };
                modeloTabla.addRow(fila2);
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
//  public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(JFrameMesasRecreativas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMesasRecreativas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMesasRecreativas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMesasRecreativas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    /*    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMesasRecreativas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


*/
