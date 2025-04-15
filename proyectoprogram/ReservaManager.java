package proyectoprogram;

import java.time.LocalDateTime;

/**
 *
 * @author samu0
 */
public class ReservaManager {

    private Reserva[] reservas;
    private int numReservas;

    public ReservaManager() {
        reservas = new Reserva[10]; // Tamaño inicial del arreglo
        numReservas = 0;
    }

// Método para reservar un cubículo
    public boolean reservarCubiculo(int cubiculoId, LocalDateTime horaInicio, LocalDateTime horaFinal, int empleadoId, String empleadoNombre) {
        for (int i = 0; i < numReservas; i++) {
            Reserva reserva = reservas[i];
            if (reserva.getCubiculoId() == cubiculoId && ((horaInicio.isBefore(reserva.getHoraFinal()) && horaInicio.isAfter(reserva.getHoraInicio()))
                    || (horaFinal.isBefore(reserva.getHoraFinal()) && horaFinal.isAfter(reserva.getHoraInicio()))
                    || horaInicio.isEqual(reserva.getHoraInicio())
                    || horaFinal.isEqual(reserva.getHoraFinal()))) {
                return false; // El cubículo ya está reservado en el rango de tiempo
            }
        }
// Si no hay conflicto, añadir la nueva reserva
        if (numReservas == reservas.length) {
            Reserva[] nuevoArreglo = new Reserva[reservas.length * 2]; // Duplicar el tamaño del arreglo si está lleno
            System.arraycopy(reservas, 0, nuevoArreglo, 0, reservas.length);
            reservas = nuevoArreglo;
        }
        reservas[numReservas++] = new Reserva(cubiculoId, horaInicio, horaFinal, empleadoId, empleadoNombre);
        return true; // Reserva exitosa
    }

}
