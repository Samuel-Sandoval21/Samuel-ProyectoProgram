package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JFrameReservas extends JFrame {

    private Estacionamiento estacionamiento;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JButton btnVolver;
    private JComboBox<String> comboFiltro;

    public JFrameReservas(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Lista de Reservas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(220, 20, 60));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Lista de Todas las Reservas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel de filtro
        JPanel panelFiltro = new JPanel();
        panelFiltro.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelFiltro.setBackground(new Color(250, 250, 250));

        JLabel lblFiltro = new JLabel("Filtrar por tipo:");
        lblFiltro.setFont(new Font("Arial", Font.BOLD, 14));
        panelFiltro.add(lblFiltro);

        String[] filtros = {"Todos", "Cubículo", "Cancha", "Auditorio", "Sala de Reuniones", "Espacio Recreativo"};
        comboFiltro = new JComboBox<>(filtros);
        comboFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFiltro.add(comboFiltro);

        btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(220, 20, 60));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        panelFiltro.add(btnActualizar);

        // Panel para la tabla de reservas
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Crear tabla con reservas
        String[] columnas = {"Tipo", "ID", "Nombre Reservante", "Personas", "Fecha", "Inicio", "Fin"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaReservas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaReservas.setRowHeight(30);

        // Configurar anchos de columnas
        tablaReservas.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaReservas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tablaReservas.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaReservas.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaReservas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaReservas.getColumnModel().getColumn(5).setPreferredWidth(80);
        tablaReservas.getColumnModel().getColumn(6).setPreferredWidth(80);

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
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });

        comboFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
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
        add(panelFiltro, BorderLayout.PAGE_START);
        add(panelTabla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Actualizar tabla inicialmente
        actualizarTabla();

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Consultar todas las reservas directamente del estacionamiento
        if (estacionamiento != null) {
            // Obtener las reservas como String
            String reservasStr = estacionamiento.getTodasLasReservasComoString();
            if (!reservasStr.contains("No hay reservas")) {
                // Hay reservas registradas, procesarlas
                String[] lineas = reservasStr.split("\n");
                for (String linea : lineas) {
                    // Ignora las líneas de encabezado/pie
                    if (linea.contains("===") || linea.trim().isEmpty()) {
                        continue;
                    }

                    try {
                        // Extraer información del formato esperado
                        // Ejemplo: "1. Cubículo #0 - Reservado por: Juan Pérez - Personas: 1 - Desde: 11-04-2025 14:00 - Hasta: 11-04-2025 15:00"
                        String sinNumero = linea.substring(linea.indexOf(". ") + 2);

                        // Separar en partes usando " - " como delimitador
                        String[] partes = sinNumero.split(" - ");

                        if (partes.length >= 5) {
                            // Extraer tipo y ID
                            String tipoConId = partes[0]; // "Cubículo #0"
                            String[] tipoIdPartes = tipoConId.split(" #");
                            String tipo = tipoIdPartes[0];
                            String id = tipoIdPartes[1];

                            // Resto de información
                            String nombre = partes[1].replace("Reservado por: ", "");
                            String personasStr = partes[2].replace("Personas: ", "");
                            String fechaHoraInicio = partes[3].replace("Desde: ", "");
                            String fechaHoraFin = partes[4].replace("Hasta: ", "");

                            // Separar fecha y hora
                            String[] fechaHoraInicioPartes = fechaHoraInicio.split(" ");
                            String[] fechaHoraFinPartes = fechaHoraFin.split(" ");

                            // Filtrar según el tipo seleccionado
                            String filtro = (String) comboFiltro.getSelectedItem();
                            if (filtro.equals("Todos") || tipo.contains(filtro)) {
                                // Agregar fila a la tabla
                                String[] fila = {
                                    tipo, // Tipo
                                    id, // ID
                                    nombre, // Nombre
                                    personasStr, // Personas
                                    fechaHoraInicioPartes[0], // Fecha
                                    fechaHoraInicioPartes[1], // Hora inicio
                                    fechaHoraFinPartes[1] // Hora fin
                                };
                                modeloTabla.addRow(fila);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error procesando línea: " + linea);
                        e.printStackTrace();
                    }
                }
            }
        }

        // Si no hay reservas en la tabla después de actualizar
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No hay reservas registradas en el sistema para el filtro seleccionado.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author jqcha
 */
/**
 * Creates new form JFrameReservas
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
/*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
 *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


*/
