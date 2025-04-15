package proyectoprogram;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * public class EspaciosRecreacion { private String tipo; private int capacidad;
 * private boolean disponible;
 *
 * public EspaciosRecreacion(String tipo, int capacidad) { this.tipo = tipo;
 * this.capacidad = capacidad; this.disponible = true; }
 *
 * public boolean reservar() { if(disponible) { disponible = false; return true;
 * } return false; }
 *
 * public void liberar() { disponible = true; } }
 */
public class EspaciosRecreacion {

    private String tipo;
    private int capacidad;
    private boolean disponible;

    public EspaciosRecreacion(String tipo, int capacidad) {
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean Disponible() {
        return disponible;
    }
}
