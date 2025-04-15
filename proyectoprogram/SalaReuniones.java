package proyectoprogram;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * public class SalaReuniones { private int capacidad; private boolean
 * disponible;  *
 * public SalaReuniones(int capacidad) { this.capacidad = capacidad;
 * this.disponible = true; }
 *
 * public boolean reservar() { if (disponible) { disponible = false; return
 * true; } return false; }
 *
 * public void liberar() { disponible = true; } }
 *
 */
public class SalaReuniones {

    private int capacidad;
    private boolean disponible;

    public SalaReuniones(int capacidad) {
        this.capacidad = capacidad;
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

    public boolean Disponible() {
        return disponible;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
