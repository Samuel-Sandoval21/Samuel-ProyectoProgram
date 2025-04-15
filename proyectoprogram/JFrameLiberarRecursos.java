package proyectoprogram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class JFrameLiberarRecursos extends JFrame {

    private Estacionamiento estacionamiento;
    private JComboBox<String> comboTipoRecurso;
    private JPanel panelRecursoEspecifico;
    private JButton btnLiberar;
    private JButton btnVolver;

    // Componentes específicos para cada tipo de recurso
    private JComboBox<String> comboCubiculo;
    private JComboBox<Integer> comboTurno;
    private JComboBox<String> comboSala;
    private JTextField txtAsistentesAuditorio;
    private JComboBox<String> comboEspacioRecreativo;
    private JComboBox<String> comboCancha;

    public JFrameLiberarRecursos(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;

        // Configuración básica del frame
        setTitle("Liberar Recursos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(139, 69, 19));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Liberar Recursos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelCentral.setBackground(new Color(253, 245, 230));

        // Panel de selección de tipo de recurso
        JPanel panelTipoRecurso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTipoRecurso.setOpaque(false);

        JLabel lblTipoRecurso = new JLabel("Seleccione el tipo de recurso a liberar:");
        lblTipoRecurso.setFont(new Font("Arial", Font.BOLD, 16));
        panelTipoRecurso.add(lblTipoRecurso);

        String[] tiposRecurso = {"Estacionamiento", "Sala de Reuniones", "Cubículo Privado",
            "Auditorio", "Espacio Recreativo", "Cancha Deportiva"};
        comboTipoRecurso = new JComboBox<>(tiposRecurso);
        comboTipoRecurso.setFont(new Font("Arial", Font.PLAIN, 16));
        comboTipoRecurso.setPreferredSize(new Dimension(250, 30));
        panelTipoRecurso.add(comboTipoRecurso);

        panelCentral.add(panelTipoRecurso);
        panelCentral.add(Box.createVerticalStrut(20));

        // Panel para opciones específicas del recurso
        panelRecursoEspecifico = new JPanel();
        panelRecursoEspecifico.setLayout(new BoxLayout(panelRecursoEspecifico, BoxLayout.Y_AXIS));
        panelRecursoEspecifico.setOpaque(false);

        // Inicialmente mostramos las opciones para estacionamiento
        mostrarOpcionesEstacionamiento();

        panelCentral.add(panelRecursoEspecifico);
        panelCentral.add(Box.createVerticalStrut(20));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);

        btnLiberar = new JButton("Liberar Recurso");
        btnLiberar.setFont(new Font("Arial", Font.BOLD, 16));
        btnLiberar.setBackground(new Color(139, 69, 19));
        btnLiberar.setForeground(Color.WHITE);
        btnLiberar.setFocusPainted(false);
        btnLiberar.setPreferredSize(new Dimension(200, 40));
        panelBotones.add(btnLiberar);

        panelCentral.add(panelBotones);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(128, 128, 128));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        panelInferior.add(btnVolver);

        // Añadir action listeners
        comboTipoRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanelRecurso();
            }
        });

        btnLiberar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liberarRecurso();
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
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Centrar en pantalla
        setLocationRelativeTo(null);
    }

    private void actualizarPanelRecurso() {
        String tipoRecurso = (String) comboTipoRecurso.getSelectedItem();

        // Limpiar panel actual
        panelRecursoEspecifico.removeAll();

        // Mostrar panel según el tipo de recurso seleccionado
        switch (tipoRecurso) {
            case "Estacionamiento":
                mostrarOpcionesEstacionamiento();
                break;
            case "Sala de Reuniones":
                mostrarOpcionesSalaReuniones();
                break;
            case "Cubículo Privado":
                mostrarOpcionesCubiculoPrivado();
                break;
            case "Auditorio":
                mostrarOpcionesAuditorio();
                break;
            case "Espacio Recreativo":
                mostrarOpcionesEspacioRecreativo();
                break;
            case "Cancha Deportiva":
                mostrarOpcionesCancha();
                break;
        }

        // Actualizar la interfaz
        panelRecursoEspecifico.revalidate();
        panelRecursoEspecifico.repaint();
    }

    private void mostrarOpcionesEstacionamiento() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel lblEspacios = new JLabel("Número de espacios a liberar:");
        lblEspacios.setFont(new Font("Arial", Font.BOLD, 14));

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner spinnerEspacios = new JSpinner(model);
        spinnerEspacios.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblEspacios);
        panel.add(spinnerEspacios);

        panelRecursoEspecifico.add(panel);
    }

    private void mostrarOpcionesSalaReuniones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel lblSala = new JLabel("Seleccione sala de reuniones:");
        lblSala.setFont(new Font("Arial", Font.BOLD, 14));

        // En una implementación real, estos datos vendrían del sistema
        String[] salas = {"Sala 0 (5 personas)", "Sala 1 (10 personas)",
            "Sala 2 (15 personas)", "Sala 3 (20 personas)"};
        comboSala = new JComboBox<>(salas);
        comboSala.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblSala);
        panel.add(comboSala);

        panelRecursoEspecifico.add(panel);
    }

    private void mostrarOpcionesCubiculoPrivado() {
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setOpaque(false);

        JLabel lblCubiculo = new JLabel("Seleccione cubículo:");
        lblCubiculo.setFont(new Font("Arial", Font.BOLD, 14));

        String[] cubiculos = {"Cubículo 0", "Cubículo 1", "Cubículo 2", "Cubículo 3", "Cubículo 4"};
        comboCubiculo = new JComboBox<>(cubiculos);
        comboCubiculo.setFont(new Font("Arial", Font.PLAIN, 14));

        panel1.add(lblCubiculo);
        panel1.add(comboCubiculo);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.setOpaque(false);

        JLabel lblTurno = new JLabel("Seleccione turno:");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 14));

        Integer[] turnos = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        comboTurno = new JComboBox<>(turnos);
        comboTurno.setFont(new Font("Arial", Font.PLAIN, 14));

        panel2.add(lblTurno);
        panel2.add(comboTurno);

        panelRecursoEspecifico.add(panel1);
        panelRecursoEspecifico.add(panel2);
    }

    private void mostrarOpcionesAuditorio() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel lblAsistentes = new JLabel("Número de asistentes a liberar:");
        lblAsistentes.setFont(new Font("Arial", Font.BOLD, 14));

        txtAsistentesAuditorio = new JTextField(10);
        txtAsistentesAuditorio.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblAsistentes);
        panel.add(txtAsistentesAuditorio);

        panelRecursoEspecifico.add(panel);
    }

    private void mostrarOpcionesEspacioRecreativo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel lblEspacio = new JLabel("Seleccione espacio recreativo:");
        lblEspacio.setFont(new Font("Arial", Font.BOLD, 14));

        // En una implementación real, estos datos vendrían del sistema
        String[] espacios = {"Espacio 0 (Ping Pong)", "Espacio 1 (Billar)",
            "Espacio 2 (Futbol)", "Espacio 3 (Futbol)",
            "Espacio 4 (Baloncesto)", "Espacio 5 (Tenis)", "Espacio 6 (Tenis)"};
        comboEspacioRecreativo = new JComboBox<>(espacios);
        comboEspacioRecreativo.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblEspacio);
        panel.add(comboEspacioRecreativo);

        panelRecursoEspecifico.add(panel);
    }

    private void mostrarOpcionesCancha() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel lblCancha = new JLabel("Seleccione cancha deportiva:");
        lblCancha.setFont(new Font("Arial", Font.BOLD, 14));

        // En una implementación real, estos datos vendrían del sistema
        String[] canchas = {
            "Cancha 0 (Fútbol)",
            "Cancha 1 (Fútbol)",
            "Cancha 2 (Baloncesto)",
            "Cancha 3 (Tenis)",
            "Cancha 4 (Tenis)"
        };
        comboCancha = new JComboBox<>(canchas);
        comboCancha.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblCancha);
        panel.add(comboCancha);

        panelRecursoEspecifico.add(panel);
    }

    private void liberarRecurso() {
        String tipoRecurso = (String) comboTipoRecurso.getSelectedItem();
        boolean exito = false;

        try {
            switch (tipoRecurso) {
                case "Estacionamiento":
                    // Aquí obtendrías el valor del spinner
                    JSpinner spinnerEspacios = (JSpinner) ((JPanel) panelRecursoEspecifico.getComponent(0)).getComponent(1);
                    int espacios = (Integer) spinnerEspacios.getValue();

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarEstacionamiento(espacios);
                    exito = true;
                    break;

                case "Sala de Reuniones":
                    String seleccionSala = (String) comboSala.getSelectedItem();
                    int salaId = Character.getNumericValue(seleccionSala.charAt(5)); // Extraer el ID de "Sala X"

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarSala(salaId);
                    exito = true;
                    break;

                case "Cubículo Privado":
                    String seleccionCubiculo = (String) comboCubiculo.getSelectedItem();
                    int cubiculoId = Character.getNumericValue(seleccionCubiculo.charAt(seleccionCubiculo.length() - 1));
                    int turno = (Integer) comboTurno.getSelectedItem();

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarCubiculo(cubiculoId, turno);
                    exito = true;
                    break;

                case "Auditorio":
                    int asistentes = Integer.parseInt(txtAsistentesAuditorio.getText().trim());

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarAuditorio(asistentes);
                    exito = true;
                    break;

                case "Espacio Recreativo":
                    String seleccionEspacio = (String) comboEspacioRecreativo.getSelectedItem();
                    int espacioId = Character.getNumericValue(seleccionEspacio.charAt(8)); // Extraer el ID de "Espacio X"

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarEspacioRecreativo(espacioId);
                    exito = true;
                    break;

                case "Cancha Deportiva":
                    String seleccionCancha = (String) comboCancha.getSelectedItem();
                    int canchaId = Character.getNumericValue(seleccionCancha.charAt(7)); // Extraer el ID de "Cancha X"

                    // Llamar al método correspondiente en el sistema
                    estacionamiento.liberarCanchaDeportiva(canchaId);
                    exito = true;
                    break;
            }

            if (exito) {
                JOptionPane.showMessageDialog(this,
                        "El recurso ha sido liberado correctamente.",
                        "Liberación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese valores numéricos válidos.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al liberar el recurso: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
 * @SuppressWarnings("unchecked")
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
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameLiberarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameLiberarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameLiberarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameLiberarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameLiberarRecursos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

*/
