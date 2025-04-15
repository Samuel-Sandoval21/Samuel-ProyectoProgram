package proyectoprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFrameMenu extends JFrame {

    private JButton btnCubiculos;
    private JButton btnEstacionamiento;
    private JButton btnMesasRecreativas;
    private JButton btnCanchasDeportivas;
    private JButton btnVerReservas;
    private JButton btnLiberarRecursos;
    private JButton btnSalir;

    private Estacionamiento estacionamiento;

    public JFrameMenu(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Menú Principal - Sistema de Reservas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para título
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(70, 130, 180));
        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel central con botones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(3, 2, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panelCentral.setBackground(new Color(240, 240, 240));

        // Crear botones con estilos
        btnCubiculos = crearBoton("Cubículos Privados", new Color(60, 179, 113));
        btnEstacionamiento = crearBoton("Espacios de Estacionamiento", new Color(30, 144, 255));
        btnMesasRecreativas = crearBoton("Mesas Recreativas", new Color(255, 165, 0));
        btnCanchasDeportivas = crearBoton("Canchas Deportivas", new Color(106, 90, 205));
        btnVerReservas = crearBoton("Ver Todas las Reservas", new Color(220, 20, 60));
        btnLiberarRecursos = crearBoton("Liberar Recursos", new Color(139, 69, 19));

        // Añadir botones al panel
        panelCentral.add(btnCubiculos);
        panelCentral.add(btnEstacionamiento);
        panelCentral.add(btnMesasRecreativas);
        panelCentral.add(btnCanchasDeportivas);
        panelCentral.add(btnVerReservas);
        panelCentral.add(btnLiberarRecursos);

        // Panel inferior con botón de salir
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(240, 240, 240));
        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setPreferredSize(new Dimension(150, 40));
        btnSalir.setBackground(new Color(128, 128, 128));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        panelInferior.add(btnSalir);

        // Añadir action listeners a los botones
        addActionListeners();

        // Añadir paneles al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto al pasar el ratón
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    private void addActionListeners() {
        btnCubiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameCubiculos cubiculos = new JFrameCubiculos(estacionamiento);
                cubiculos.setVisible(true);
            }
        });

        btnEstacionamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameEstacionamiento estacionamientoFrame = new JFrameEstacionamiento(estacionamiento);
                estacionamientoFrame.setVisible(true);
            }
        });

        btnMesasRecreativas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameMesasRecreativas mesas = new JFrameMesasRecreativas(estacionamiento);
                mesas.setVisible(true);
            }
        });

        btnCanchasDeportivas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameCanchasDeportivas canchas = new JFrameCanchasDeportivas(estacionamiento);
                canchas.setVisible(true);
            }
        });

        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameReservas reservas = new JFrameReservas(estacionamiento);
                reservas.setVisible(true);
            }
        });

        btnLiberarRecursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameLiberarRecursos liberar = new JFrameLiberarRecursos(estacionamiento);
                liberar.setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar esta ventana
                System.exit(0); // Terminar la aplicación
            }
        });
    }
}

/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setText("Menu de Reservas");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("RESERVAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButton1)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

*/
