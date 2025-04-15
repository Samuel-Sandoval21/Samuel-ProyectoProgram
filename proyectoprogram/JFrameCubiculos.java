package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class JFrameCubiculos extends JFrame {

    private Estacionamiento estacionamiento;
    private JComboBox<String> comboCubiculo;
    private JComboBox<Integer> comboTurno;
    private JTextField txtNombre;
    private JButton btnReservar;
    private JButton btnVolver;
    private JTable tablaTurnos;
    private DefaultTableModel modeloTabla;

    public JFrameCubiculos(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Reservar Cubículos Privados");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(60, 179, 113));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Cubículos Privados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel de formulario de reserva
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelFormulario.setBackground(new Color(240, 255, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiquetas y campos
        JLabel lblCubiculo = new JLabel("Seleccione Cubículo:");
        lblCubiculo.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblCubiculo, gbc);

        String[] cubiculos = {"Cubículo 0", "Cubículo 1", "Cubículo 2", "Cubículo 3", "Cubículo 4"};
        comboCubiculo = new JComboBox<>(cubiculos);
        comboCubiculo.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulario.add(comboCubiculo, gbc);

        JLabel lblTurno = new JLabel("Seleccione Turno:");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(lblTurno, gbc);

        Integer[] turnos = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        comboTurno = new JComboBox<>(turnos);
        comboTurno.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulario.add(comboTurno, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulario.add(txtNombre, gbc);

        // Botón de reservar
        btnReservar = new JButton("Reservar Cubículo");
        btnReservar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReservar.setBackground(new Color(46, 139, 87));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnReservar, gbc);

        // Panel para la tabla de turnos
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Estado de Cubículos",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14)));

        // Crear tabla con estado de turnos
        String[] columnas = {"Cubículo", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        modeloTabla = new DefaultTableModel(columnas, 5) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaTurnos = new JTable(modeloTabla);
        tablaTurnos.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaTurnos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaTurnos.setRowHeight(25);

        // Inicializar los valores de la tabla (todos disponibles al principio)
        initTablaReservas();

        JScrollPane scrollPane = new JScrollPane(tablaTurnos);
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
                reservarCubiculo();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Listener para el combo de cubículo y turno que actualice la tabla
        comboCubiculo.addActionListener(e -> actualizarTabla());
        comboTurno.addActionListener(e -> actualizarTabla());

        // Añadir paneles al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private void initTablaReservas() {
        // Inicializar nombres de cubículos
        for (int i = 0; i < 5; i++) {
            modeloTabla.setValueAt("Cubículo " + i, i, 0);
        }

        // Obtener el estado real de los cubículos desde el sistema
        actualizarTablaCompleta();
    }

    private void reservarCubiculo() {
        int indiceCubiculo = comboCubiculo.getSelectedIndex();
        int turno = (Integer) comboTurno.getSelectedItem();
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese su nombre",
                    "Dato Faltante",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean reservado = estacionamiento.reservarCubiculo(indiceCubiculo, turno, nombre);

        if (reservado) {
            JOptionPane.showMessageDialog(this,
                    "Reserva exitosa del cubículo " + indiceCubiculo + " para el turno " + turno,
                    "Reserva Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            // Actualizar solo la celda específica que ha sido reservada
            actualizarCeldaEspecifica(indiceCubiculo, turno);

            // Limpiar el campo de nombre
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error en la reserva. El cubículo " + indiceCubiculo + " para el turno " + turno + " no está disponible.",
                    "Error en Reserva",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCeldaEspecifica(int cubiculo, int turno) {
        // Calcular la columna correspondiente al turno (turno 9 = columna 1, turno 10 = columna 2, etc.)
        int columna = turno - 8; // 9-8=1, 10-8=2, etc.

        // Consultar si el cubículo está realmente reservado
        boolean estaReservado = false;

        // En una implementación real, aquí consultarías al sistema
        // Ejemplo: estaReservado = !estacionamiento.cubiculoDisponible(cubiculo, turno);
        // Para esta demostración, asumimos que sí se reservó correctamente
        estaReservado = true;

        // Actualizar la celda correspondiente
        if (estaReservado) {
            modeloTabla.setValueAt("Reservado", cubiculo, columna);
        } else {
            modeloTabla.setValueAt("Disponible", cubiculo, columna);
        }
    }

    private void actualizarTabla() {
        // Esta función actualiza la visualización para mostrar claramente el estado
        // del cubículo y turno seleccionados actualmente
        int indiceCubiculo = comboCubiculo.getSelectedIndex();
        int turno = (Integer) comboTurno.getSelectedItem();

        // Consultar el estado real del cubículo seleccionado para el turno específico
        boolean disponible = true; // Por defecto asumimos que está disponible

        // En una implementación real, consultaríamos al sistema:
        // disponible = estacionamiento.cubiculoDisponible(indiceCubiculo, turno);
        // Aquí simulamos que algunos turnos están ocupados
        if (estacionamiento != null) {
            // Esta es una simulación básica para comprobar si un cubículo está reservado
            try {
                // Intentar obtener el estado real llamando a un método hipotético
                // disponible = estacionamiento.cubiculoDisponible(indiceCubiculo, turno);

                // Para la demostración, simulamos aleatoriamente
                disponible = !((indiceCubiculo + turno) % 3 == 0); // Un patrón simple para simular ocupación
            } catch (Exception e) {
                System.out.println("Error al consultar disponibilidad: " + e.getMessage());
            }
        }

        // Actualizar la UI con información sobre la disponibilidad
        if (disponible) {
            btnReservar.setEnabled(true);
            btnReservar.setText("Reservar Cubículo");
        } else {
            btnReservar.setEnabled(false);
            btnReservar.setText("Cubículo No Disponible");
        }
    }

    private void actualizarTablaCompleta() {
        // Recorrer todos los cubículos y turnos para actualizarlos
        for (int cubiculo = 0; cubiculo < 5; cubiculo++) {
            for (int hora = 9; hora <= 17; hora++) {
                int columna = hora - 8; // 9-8=1, 10-8=2, etc.
                boolean disponible = true;

                // En una implementación real, consultaríamos al sistema:
                // disponible = estacionamiento.cubiculoDisponible(cubiculo, hora);
                // Para la simulación
                if (estacionamiento != null) {
                    // Simulamos algunos cubículos ocupados con un patrón simple
                    disponible = !((cubiculo + hora) % 3 == 0);
                }

                modeloTabla.setValueAt(disponible ? "Disponible" : "Reservado", cubiculo, columna);
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

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
*/
