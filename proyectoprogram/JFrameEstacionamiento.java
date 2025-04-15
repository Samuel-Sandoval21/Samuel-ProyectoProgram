package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class JFrameEstacionamiento extends JFrame {

    private Estacionamiento estacionamiento;
    private JPanel panelNivelS1;
    private JPanel panelNivelS2;
    private JPanel panelNivelS3;
    private JComboBox<String> comboNivel;
    private JComboBox<String> comboFila;
    private JComboBox<Integer> comboColumna;
    private JButton btnOcupar;
    private JButton btnLiberar;
    private JButton btnActualizar;
    private JButton btnVolver;
    private JTabbedPane pestanas;

    public JFrameEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Gestión de Estacionamiento");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(30, 144, 255));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Espacios de Estacionamiento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel central con pestañas para los niveles
        pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Arial", Font.BOLD, 14));

        // Crear paneles para cada nivel
        panelNivelS1 = crearPanelNivel("S1");
        panelNivelS2 = crearPanelNivel("S2");
        panelNivelS3 = crearPanelNivel("S3");

        // Añadir pestañas
        pestanas.addTab("Nivel S1", panelNivelS1);
        pestanas.addTab("Nivel S2", panelNivelS2);
        pestanas.addTab("Nivel S3", panelNivelS3);

        // Panel de control
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridBagLayout());
        panelControl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Control de Estacionamiento",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nivel
        JLabel lblNivel = new JLabel("Nivel:");
        lblNivel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelControl.add(lblNivel, gbc);

        String[] niveles = {"S1", "S2", "S3"};
        comboNivel = new JComboBox<>(niveles);
        comboNivel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelControl.add(comboNivel, gbc);

        // Fila
        JLabel lblFila = new JLabel("Fila:");
        lblFila.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelControl.add(lblFila, gbc);

        String[] filasS1 = {"A", "B", "C", "D"};
        comboFila = new JComboBox<>(filasS1);
        comboFila.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelControl.add(comboFila, gbc);

        // Columna
        JLabel lblColumna = new JLabel("Columna:");
        lblColumna.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelControl.add(lblColumna, gbc);

        Integer[] columnas = {1, 2, 3, 4, 5};
        comboColumna = new JComboBox<>(columnas);
        comboColumna.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelControl.add(comboColumna, gbc);

        // Panel de estado del espacio seleccionado
        JPanel panelEstadoEspacio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEstadoEspacio = new JLabel("Estado:");
        lblEstadoEspacio.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblEstadoValor = new JLabel("Desconocido");
        lblEstadoValor.setFont(new Font("Arial", Font.PLAIN, 14));
        panelEstadoEspacio.add(lblEstadoEspacio);
        panelEstadoEspacio.add(lblEstadoValor);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelControl.add(panelEstadoEspacio, gbc);

        // Botones
        btnOcupar = new JButton("Ocupar Espacio");
        btnOcupar.setFont(new Font("Arial", Font.BOLD, 14));
        btnOcupar.setBackground(new Color(46, 139, 87));
        btnOcupar.setForeground(Color.WHITE);
        btnOcupar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelControl.add(btnOcupar, gbc);

        btnLiberar = new JButton("Liberar Espacio");
        btnLiberar.setFont(new Font("Arial", Font.BOLD, 14));
        btnLiberar.setBackground(new Color(178, 34, 34));
        btnLiberar.setForeground(Color.WHITE);
        btnLiberar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelControl.add(btnLiberar, gbc);

        btnActualizar = new JButton("Actualizar Vista");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(70, 130, 180));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelControl.add(btnActualizar, gbc);

        // Leyenda
        JPanel panelLeyenda = new JPanel(new GridLayout(4, 1, 5, 5));
        panelLeyenda.setBorder(BorderFactory.createTitledBorder("Leyenda"));

        agregarItemLeyenda(panelLeyenda, "O", Color.GREEN, "Espacio libre");
        agregarItemLeyenda(panelLeyenda, "P", Color.RED, "Espacio ocupado");
        agregarItemLeyenda(panelLeyenda, "D", Color.ORANGE, "Reservado para directores");
        agregarItemLeyenda(panelLeyenda, "E", Color.BLUE, "Para personas con discapacidad");

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelControl.add(panelLeyenda, gbc);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(128, 128, 128));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        panelInferior.add(btnVolver);

        // Añadir action listeners
        comboNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFilasSegunNivel((String) comboNivel.getSelectedItem());
                pestanas.setSelectedIndex(comboNivel.getSelectedIndex());
                actualizarEstadoEspacioSeleccionado(lblEstadoValor);
            }
        });

        comboFila.addActionListener(e -> actualizarEstadoEspacioSeleccionado(lblEstadoValor));
        comboColumna.addActionListener(e -> actualizarEstadoEspacioSeleccionado(lblEstadoValor));

        pestanas.addChangeListener(e -> {
            comboNivel.setSelectedIndex(pestanas.getSelectedIndex());
            actualizarFilasSegunNivel((String) comboNivel.getSelectedItem());
            actualizarEstadoEspacioSeleccionado(lblEstadoValor);
        });

        btnOcupar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocuparEspacio();
                actualizarEstadoEspacioSeleccionado(lblEstadoValor);
            }
        });

        btnLiberar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liberarEspacio();
                actualizarEstadoEspacioSeleccionado(lblEstadoValor);
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVista();
                actualizarEstadoEspacioSeleccionado(lblEstadoValor);
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
        add(pestanas, BorderLayout.CENTER);
        add(panelControl, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

        // Inicializar vista
        actualizarVista();
        actualizarEstadoEspacioSeleccionado(lblEstadoValor);

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private void agregarItemLeyenda(JPanel panel, String texto, Color color, String descripcion) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton muestra = new JButton(texto);
        muestra.setBackground(color);
        muestra.setForeground(Color.WHITE);
        muestra.setPreferredSize(new Dimension(40, 25));
        muestra.setEnabled(false);

        JLabel desc = new JLabel(descripcion);
        desc.setFont(new Font("Arial", Font.PLAIN, 12));

        item.add(muestra);
        item.add(desc);
        panel.add(item);
    }

    private void actualizarEstadoEspacioSeleccionado(JLabel lblEstado) {
        String nivel = (String) comboNivel.getSelectedItem();
        String filaStr = (String) comboFila.getSelectedItem();
        int columna = (Integer) comboColumna.getSelectedItem();

        // Convertir fila de letra a índice (A=0, B=1, etc.)
        int fila = filaStr.charAt(0) - 'A';

        // Obtener el estado actual del espacio seleccionado
        JPanel panelNivel = null;
        if ("S1".equals(nivel)) {
            panelNivel = panelNivelS1;
        } else if ("S2".equals(nivel)) {
            panelNivel = panelNivelS2;
        } else if ("S3".equals(nivel)) {
            panelNivel = panelNivelS3;
        }

        if (panelNivel != null) {
            Component[] componentes = panelNivel.getComponents();
            int indiceBoton = 6 + fila * 6 + columna;

            if (indiceBoton < componentes.length && componentes[indiceBoton] instanceof JButton) {
                JButton btn = (JButton) componentes[indiceBoton];
                String estado = btn.getText();

                switch (estado) {
                    case "O":
                        lblEstado.setText("Espacio libre");
                        lblEstado.setForeground(Color.GREEN.darker());
                        btnOcupar.setEnabled(true);
                        btnLiberar.setEnabled(false);
                        break;
                    case "P":
                        lblEstado.setText("Espacio ocupado");
                        lblEstado.setForeground(Color.RED);
                        btnOcupar.setEnabled(false);
                        btnLiberar.setEnabled(true);
                        break;
                    case "D":
                        lblEstado.setText("Reservado para directores");
                        lblEstado.setForeground(Color.ORANGE.darker());
                        btnOcupar.setEnabled(false);
                        btnLiberar.setEnabled(false);
                        break;
                    case "E":
                        lblEstado.setText("Para personas con discapacidad");
                        lblEstado.setForeground(Color.BLUE);
                        btnOcupar.setEnabled(false);
                        btnLiberar.setEnabled(false);
                        break;
                    default:
                        lblEstado.setText("Desconocido");
                        lblEstado.setForeground(Color.BLACK);
                        btnOcupar.setEnabled(true);
                        btnLiberar.setEnabled(true);
                }
            }
        }
    }

    private JPanel crearPanelNivel(String nivel) {
        JPanel panel = new JPanel(new GridLayout(0, 6, 5, 5));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        // Encabezado
        panel.add(new JLabel(""));
        for (int i = 1; i <= 5; i++) {
            JLabel lbl = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(lbl);
        }

        int filas = 4; // Por defecto S1
        if (nivel.equals("S2")) {
            filas = 5;
        }
        if (nivel.equals("S3")) {
            filas = 6;
        }

        // Filas
        for (int i = 0; i < filas; i++) {
            char fila = (char) ('A' + i);
            JLabel lblFila = new JLabel(Character.toString(fila), SwingConstants.CENTER);
            lblFila.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(lblFila);

            for (int j = 1; j <= 5; j++) {
                JButton btn = new JButton("O");
                btn.setPreferredSize(new Dimension(50, 50));
                btn.setBackground(Color.GREEN);
                btn.setForeground(Color.BLACK);
                btn.setFont(new Font("Arial", Font.BOLD, 14));
                btn.setToolTipText("Espacio " + fila + j + " - Disponible");

                // Agregar un identificador al botón para facilitar su localización
                btn.setName(nivel + "_" + fila + "_" + j);

                // Agregar acción al hacer clic en el botón
                final int filaIndex = i;
                final int columnaIndex = j;
                btn.addActionListener(e -> {
                    comboNivel.setSelectedItem(nivel);
                    comboFila.setSelectedItem(Character.toString((char) ('A' + filaIndex)));
                    comboColumna.setSelectedItem(columnaIndex);
                });

                panel.add(btn);
            }
        }

        return panel;
    }

    private void actualizarFilasSegunNivel(String nivel) {
        comboFila.removeAllItems();

        if (nivel.equals("S1")) {
            for (char c = 'A'; c <= 'D'; c++) {
                comboFila.addItem(Character.toString(c));
            }
        } else if (nivel.equals("S2")) {
            for (char c = 'A'; c <= 'E'; c++) {
                comboFila.addItem(Character.toString(c));
            }
        } else if (nivel.equals("S3")) {
            for (char c = 'A'; c <= 'F'; c++) {
                comboFila.addItem(Character.toString(c));
            }
        }
    }

    private void ocuparEspacio() {
        String nivel = (String) comboNivel.getSelectedItem();
        String filaStr = (String) comboFila.getSelectedItem();
        int columna = (Integer) comboColumna.getSelectedItem();

        // Convertir fila de letra a índice (A=0, B=1, etc.)
        int fila = filaStr.charAt(0) - 'A';

        // Obtener el botón específico en el panel
        JButton btn = obtenerBotonEspacio(nivel, fila, columna);

        if (btn != null) {
            String estado = btn.getText();

            if (estado.equals("O")) {  // Solo si está libre
                // Aquí llamarías al sistema para ocupar el espacio
                // En una implementación real:
                // boolean exito = estacionamiento.ocuparEspacio(nivel, fila, columna-1);

                // Simulación para la interfaz
                boolean exito = true;

                if (exito) {
                    // Actualizar solo este botón específico
                    btn.setText("P"); // P = Ocupado
                    btn.setBackground(Color.RED);
                    btn.setToolTipText("Espacio " + filaStr + columna + " - Ocupado");

                    JOptionPane.showMessageDialog(this,
                            "Espacio " + filaStr + columna + " del nivel " + nivel + " ocupado exitosamente.",
                            "Ocupación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error: El espacio " + filaStr + columna + " del nivel " + nivel + " no está disponible.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "El espacio seleccionado no está disponible.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void liberarEspacio() {
        String nivel = (String) comboNivel.getSelectedItem();
        String filaStr = (String) comboFila.getSelectedItem();
        int columna = (Integer) comboColumna.getSelectedItem();

        // Convertir fila de letra a índice (A=0, B=1, etc.)
        int fila = filaStr.charAt(0) - 'A';

        // Obtener el botón específico en el panel
        JButton btn = obtenerBotonEspacio(nivel, fila, columna);

        if (btn != null) {
            String estado = btn.getText();

            if (estado.equals("P")) {  // Solo si está ocupado
                // Aquí llamarías al sistema para liberar el espacio
                // En una implementación real:
                // boolean exito = estacionamiento.liberarEspacio(nivel, fila, columna-1);

                // Simulación para la interfaz
                boolean exito = true;

                if (exito) {
                    // Actualizar solo este botón específico
                    btn.setText("O"); // O = Libre
                    btn.setBackground(Color.GREEN);
                    btn.setToolTipText("Espacio " + filaStr + columna + " - Disponible");

                    JOptionPane.showMessageDialog(this,
                            "Espacio " + filaStr + columna + " del nivel " + nivel + " liberado exitosamente.",
                            "Liberación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error: El espacio " + filaStr + columna + " del nivel " + nivel + " no puede ser liberado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (estado.equals("E") || estado.equals("D")) {
                JOptionPane.showMessageDialog(this,
                        "Este espacio está reservado y no puede ser liberado normalmente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "El espacio seleccionado ya está libre.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private JButton obtenerBotonEspacio(String nivel, int fila, int columna) {
        JPanel panelNivel = null;

        if ("S1".equals(nivel)) {
            panelNivel = panelNivelS1;
        } else if ("S2".equals(nivel)) {
            panelNivel = panelNivelS2;
        } else if ("S3".equals(nivel)) {
            panelNivel = panelNivelS3;
        }

        if (panelNivel != null) {
            Component[] componentes = panelNivel.getComponents();
            // El índice del botón en el arreglo de componentes
            // (saltando la primera fila de encabezados y la columna de encabezados)
            int indice = 6 + fila * 6 + columna;

            if (indice < componentes.length && componentes[indice] instanceof JButton) {
                return (JButton) componentes[indice];
            }
        }

        return null;
    }

    private void actualizarVista() {
        // En una implementación real, aquí consultarías al sistema para 
        // obtener el estado actual de todos los espacios

        // Aquí se simularía la lectura del estado actual del estacionamiento
        if (estacionamiento != null) {
            // En una implementación real, esto vendría del sistema:
            // String estadoParqueo = estacionamiento.getEstadoParqueoComoString();
            // Y luego procesarías ese string para actualizar la interfaz

            // Actualizar los tres niveles
            actualizarPanelNivel(panelNivelS1, "S1");
            actualizarPanelNivel(panelNivelS2, "S2");
            actualizarPanelNivel(panelNivelS3, "S3");
        }
    }

    private void actualizarPanelNivel(JPanel panel, String nivel) {
        // En una implementación real, aquí consultarías al sistema
        // para obtener el estado actual de cada espacio

        // Ejemplo de cómo obtendríamos los datos reales:
        // char[][] espacios = estacionamiento.getNivelSegunNombre(nivel).getEspacios();
        // Para la simulación, usaremos datos aleatorios pero con los estados adecuados
        int filas = 4; // Por defecto S1
        if (nivel.equals("S2")) {
            filas = 5;
        }
        if (nivel.equals("S3")) {
            filas = 6;
        }

        // Inicializamos un array para almacenar los estados
        char[][] espacios = new char[filas][5];

        // Simulamos la actualización de espacios para la demostración
        // En una implementación real, estos datos vendrían del sistema
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 5; j++) {
                double rand = Math.random();

                if (rand < 0.5) {
                    espacios[i][j] = 'O'; // Espacio libre
                } else if (rand < 0.7) {
                    espacios[i][j] = 'P'; // Espacio ocupado
                } else if (rand < 0.85) {
                    espacios[i][j] = 'E'; // Espacio para discapacitados
                } else {
                    espacios[i][j] = 'D'; // Espacio para directores
                }
            }
        }

        // Ahora actualizamos los botones del panel según los estados
        Component[] componentes = panel.getComponents();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 5; j++) {
                // El índice del botón en el arreglo de componentes
                int indice = 6 + i * 6 + j + 1;

                if (indice < componentes.length && componentes[indice] instanceof JButton) {
                    JButton btn = (JButton) componentes[indice];
                    char estado = espacios[i][j];

                    btn.setText(String.valueOf(estado));

                    switch (estado) {
                        case 'O': // Libre
                            btn.setBackground(Color.GREEN);
                            btn.setToolTipText("Espacio Libre");
                            break;
                        case 'P': // Ocupado
                            btn.setBackground(Color.RED);
                            btn.setToolTipText("Espacio Ocupado");
                            break;
                        case 'E': // Discapacitados
                            btn.setBackground(Color.BLUE);
                            btn.setToolTipText("Espacio para Discapacitados");
                            break;
                        case 'D': // Directores
                            btn.setBackground(Color.ORANGE);
                            btn.setToolTipText("Espacio para Directores");
                            break;
                    }
                }
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
/*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
 */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameEstacionamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameEstacionamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameEstacionamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameEstacionamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
/*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameEstacionamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
*/
