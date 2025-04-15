package proyectoprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFramePrincipal extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    private JButton btnIngresar;
    private JLabel lblLogo;

    public JFramePrincipal() {
        // Configuración básica del frame
        setTitle("Sistema de Reservas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel con imagen de fondo
        // Reemplaza "ruta/a/tu/imagen.jpg" con la ruta de tu imagen
        BackgroundPanel panelFondo = new BackgroundPanel("cars.jpg");
        panelFondo.setLayout(new BorderLayout());

        // Panel central con transparencia para que se vea la imagen de fondo
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Hacer el panel semi-transparente
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(230, 240, 250, 180)); // El último número (180) controla la transparencia (0-255)
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        panelCentral.setOpaque(false); // Importante para que se vea la imagen de fondo
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Título principal con estilo mejorado
        lblTitulo = new JLabel("SISTEMA DE RESERVAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(50, 50, 120)); // Color más oscuro para contraste
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        // Aplicar sombra al texto del título para mejor visibilidad sobre la imagen
        lblTitulo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createEmptyBorder(30, 0, 10, 0)
        ));

        // Subtítulo
        lblSubtitulo = new JLabel("Gestión de espacios y servicios");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 20));
        lblSubtitulo.setForeground(new Color(80, 80, 120)); // Color para contraste
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Botón para ingresar con estilo mejorado
        btnIngresar = new JButton("Ingresar al Sistema");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresar.setMaximumSize(new Dimension(250, 50));
        btnIngresar.setBackground(new Color(70, 130, 180));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);

        // Efecto hover para el botón
        btnIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnIngresar.setBackground(new Color(100, 160, 210));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnIngresar.setBackground(new Color(70, 130, 180));
            }
        });

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar ventana actual
            }
        });

        // Añadir componentes al panel central
        panelCentral.add(lblTitulo);
        panelCentral.add(lblSubtitulo);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(btnIngresar);

        // Panel inferior con información
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(70, 130, 180, 200)); // Semi-transparente
        JLabel lblInfo = new JLabel("© 2025 Sistema de Reservas - Todos los derechos reservados");
        lblInfo.setForeground(Color.WHITE);
        panelInferior.add(lblInfo);

        // Añadir paneles al panel de fondo
        panelFondo.add(panelCentral, BorderLayout.CENTER);
        panelFondo.add(panelInferior, BorderLayout.SOUTH);

        // Añadir panel de fondo al frame
        add(panelFondo);

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

}



/*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("INGRESAR");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.setText("Bienvenidos al Sistema de Parqueo");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("CONTINUAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(529, 529, 529)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 110, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)
                        .addGap(55, 55, 55)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jButton2)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
*/
