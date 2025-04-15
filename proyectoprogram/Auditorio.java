/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogram;

/**
 *
 * public class Auditorio {
 *
 * static int length; private int capacidad; private boolean disponible; private
 * int horarioCharlasCapacitaciones;
 *
 * public Auditorio() { this.capacidad = 50; this.disponible = true;
 * this.horarioCharlasCapacitaciones = 10; this.horarioCharlasCapacitaciones =
 * 3; }
 *
 * public boolean reservar() { if (disponible) { disponible = false; return
 * true; } return false; }
 *
 * public void liberar() { disponible = true; }
 *
 * public boolean disponible() { return disponible; }
 *
 * public int getcapacidad() { return capacidad; } }
 *
 */
public class Auditorio {

    private int capacidad;
    private boolean reservado;

    public Auditorio() {
        this.capacidad = 50;
        this.reservado = false;
    }

    public boolean reservar() {
        if (!reservado) {
            reservado = true;
            return true;
        }
        return false;
    }

    public void liberar() {
        reservado = false;
    }
}
