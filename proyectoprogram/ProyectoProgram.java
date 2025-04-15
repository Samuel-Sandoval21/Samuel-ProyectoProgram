package proyectoprogram;

import javax.swing.JFrame;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class ProyectoProgram {

    public static void main(String[] args) {
        // Crear la instancia de estacionamiento (sistema de gestión)
        Estacionamiento estacionamiento = new Estacionamiento();

        // Mostrar JFramePrincipal
        JFramePrincipal principal = new JFramePrincipal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);

        // Esperar a que el usuario cierre el JFramePrincipal
        while (principal.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar JFrameMenu con la instancia de estacionamiento
        JFrameMenu menu = new JFrameMenu(estacionamiento);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }
}
