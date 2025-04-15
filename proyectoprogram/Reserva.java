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
public class Reserva {

    private int cubiculoId;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private int empleadoId;
    private String empleadoNombre;

    //Constructor
    public Reserva(int cubiculoID, LocalDateTime horaInicio, LocalDateTime horaFinal, int empleadoId, String empleadoNombre) {
        this.cubiculoId = cubiculoId;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.empleadoId = empleadoId;
        this.empleadoNombre = empleadoNombre;
    }

    //Getters y setters
    public int getCubiculoId() {
        return cubiculoId;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

}
