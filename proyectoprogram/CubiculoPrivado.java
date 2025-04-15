/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogram;

/**
 *
 * public class CubiculoPrivado { static int length; private boolean[] turnos;
 *
 * public CubiculoPrivado() { this.turnos = new boolean[9]; // Turnos de 9 a.m.
 * a 6 p.m. }
 *
 * public boolean reservarTurno(int turno) { int index = turno - 9; if (index >=
 * 0 && index < turnos.length && !turnos[index]) {
 * turnos[index] = true;
 * return true;
 * }
 * return false;
 * }
 *
 * public void liberarTurno(int turno) {
 * int index = turno - 9;
 * if (index >= 0 && index < turnos.length) { turnos[index] = false; } }
 */

/*
public class CubiculoPrivado {

    static int length;
    private boolean[] turnos;
    
    public CubiculoPrivado() {
        this.turnos = new boolean[9]; // Turnos de 9 a.m. a 6 p.m.
    }
    
    public boolean reservarTurno(int turno) {
        int index = turno - 9;
            if (index >= 0 && index < turnos.length && !turnos[index]) {
                turnos[index] = true;
            return true;
        }
        return false;
    }
    
    public void liberarTurno(int turno) {
        int index = turno - 9;
        if (index >= 0 && index < turnos.length) {
            turnos[index] = false;
        }
    }
    
    public void mostrarReservas() {
        System.out.println("Turnos reservados: " + turnos);
    }
    
}
 */
public class CubiculoPrivado {

    // Eliminar la línea static int length que causa conflictos
    private boolean[] turnos;

    public CubiculoPrivado() {
        this.turnos = new boolean[9]; // Turnos de 9 a.m. a 6 p.m.
    }

    public boolean reservarTurno(int turno) {
        int index = turno - 9;
        if (index >= 0 && index < turnos.length && !turnos[index]) {
            turnos[index] = true;
            return true;
        }
        return false;
    }

    public void liberarTurno(int turno) {
        int index = turno - 9;
        if (index >= 0 && index < turnos.length) {
            turnos[index] = false;
        }
    }

    public void mostrarReservas() {
        System.out.println("Estado de turnos:");
        for (int i = 0; i < turnos.length; i++) {
            System.out.println("Hora " + (i + 9) + ": " + (turnos[i] ? "Reservado" : "Disponible"));
        }
    }
}
