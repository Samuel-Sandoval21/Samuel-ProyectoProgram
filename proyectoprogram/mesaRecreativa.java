package proyectoprogram;

import java.time.LocalDateTime;

public class mesaRecreativa {

    private String tipo;
    private LocalDateTime[] reservas;
    private int cantidadReservas;
    private final int MAX_RESERVAS = 10; // máximo de reservas permitidas
    private final int DURACION_RESERVA = 2; // en horas

    public mesaRecreativa(String tipo) {
        this.tipo = tipo;
        this.reservas = new LocalDateTime[MAX_RESERVAS];
        this.cantidadReservas = 0;
    }

    public boolean reservar(LocalDateTime horaInicio) {
        // Verificar si hay espacio para más reservas
        if (cantidadReservas >= MAX_RESERVAS) {
            return false; // No hay más espacio para reservas
        }

        // Verificar si la mesa ya está reservada para esa hora
        for (int i = 0; i < cantidadReservas; i++) {
            LocalDateTime reservaExistente = reservas[i];

            // Si la hora de inicio solicitada está dentro de una reserva existente
            if ((horaInicio.isEqual(reservaExistente) || horaInicio.isAfter(reservaExistente))
                    && horaInicio.isBefore(reservaExistente.plusHours(DURACION_RESERVA))) {
                return false; // Ya hay una reserva para esa hora
            }

            // Si el final de la reserva solicitada se extiende a una reserva existente
            LocalDateTime finReservaSolicitada = horaInicio.plusHours(DURACION_RESERVA);
            if (finReservaSolicitada.isAfter(reservaExistente)
                    && finReservaSolicitada.isBefore(reservaExistente.plusHours(DURACION_RESERVA))) {
                return false;
            }
        }

        // Si llegamos aquí, la hora está disponible
        reservas[cantidadReservas] = horaInicio;
        cantidadReservas++;
        return true;
    }

    public boolean cancelarReserva(LocalDateTime horaInicio) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (reservas[i].equals(horaInicio)) {
                // Mover todas las reservas una posición hacia atrás
                for (int j = i; j < cantidadReservas - 1; j++) {
                    reservas[j] = reservas[j + 1];
                }
                cantidadReservas--;
                return true;
            }
        }
        return false;
    }

    public LocalDateTime[] getReservas() {
        LocalDateTime[] copiaReservas = new LocalDateTime[cantidadReservas];
        for (int i = 0; i < cantidadReservas; i++) {
            copiaReservas[i] = reservas[i];
        }
        return copiaReservas;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa de ").append(tipo).append("\n");

        if (cantidadReservas == 0) {
            sb.append("No hay reservas actualmente.");
        } else {
            sb.append("Reservas actuales:\n");
            for (int i = 0; i < cantidadReservas; i++) {
                LocalDateTime reserva = reservas[i];
                sb.append("- ").append(reserva.toLocalDate())
                        .append(" de ").append(reserva.toLocalTime())
                        .append(" a ").append(reserva.plusHours(DURACION_RESERVA).toLocalTime())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}
