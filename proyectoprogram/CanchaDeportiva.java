/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogram;

import java.time.LocalDateTime;

/**
 *
 * @author samu0
 */
public class CanchaDeportiva {

    private String deporte;
    private int capacidad;
    private boolean disponible;
    private String descripcion;
    private int id;

    public CanchaDeportiva(int id, String deporte, int capacidad, String descripcion) {
        this.id = id;
        this.deporte = deporte;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.disponible = true;
    }

    public boolean reservar() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }

    public void liberar() {
        disponible = true;
    }

    public String getDeporte() {
        return deporte;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cancha de " + deporte + " (ID: " + id + ") - "
                + "Capacidad: " + capacidad + " personas - "
                + (disponible ? "Disponible" : "Ocupada");
    }
}
