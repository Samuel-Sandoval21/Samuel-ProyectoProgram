package proyectoprogram;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaGeneral {

    private String tipoRecurso;
    private int recursoId;
    private String nombreReservante;
    private int cantidadPersonas;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    public ReservaGeneral(String tipoRecurso, int recursoId, String nombreReservante,
            int cantidadPersonas, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.tipoRecurso = tipoRecurso;
        this.recursoId = recursoId;
        this.nombreReservante = nombreReservante;
        this.cantidadPersonas = cantidadPersonas;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public int getRecursoId() {
        return recursoId;
    }

    public String getNombreReservante() {
        return nombreReservante;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return tipoRecurso + " #" + recursoId
                + " - Reservado por: " + nombreReservante
                + " - Personas: " + cantidadPersonas
                + " - Desde: " + horaInicio.format(formatter)
                + " - Hasta: " + horaFin.format(formatter);
    }
}
