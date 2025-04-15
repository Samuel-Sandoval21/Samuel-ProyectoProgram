package proyectoprogram;

import java.time.LocalDateTime;

public class Estacionamiento {

    private int ocupacionAuditorio = 0;
    private final int CAPACIDAD_AUDITORIO = 50;
    private CubiculoPrivado[] cubiculosPrivados;
    private String[] empleados;
    private int cantidadEmpleados;

    private Nivel nivelS1;
    private Nivel nivelS2;
    private Nivel nivelS3;
    private SalaReuniones[] salasReuniones;
    private EspaciosRecreacion[] espaciosRecreacion;
    private Auditorio auditorio;
    private CanchaDeportiva[] canchasDeportivas;
    private ReservaGeneral[] todasLasReservas;
    private int cantidadReservas;
    private final int MAX_RESERVAS = 100;

    public Estacionamiento() {
        empleados = new String[10];
        cantidadEmpleados = 0;

        empleados[cantidadEmpleados++] = "Empleado 01 - Samuel Sandoval";
        empleados[cantidadEmpleados++] = "Empleado 02 - Kimberly Picado";
        empleados[cantidadEmpleados++] = "Empleado 03 - Jose Pablo";

        nivelS1 = new Nivel(4, 5);
        nivelS2 = new Nivel(5, 5);
        nivelS3 = new Nivel(6, 5);

        // Inicialización de salas de reuniones
        salasReuniones = new SalaReuniones[]{
            new SalaReuniones(5),
            new SalaReuniones(10),
            new SalaReuniones(15),
            new SalaReuniones(20)
        };

        this.cubiculosPrivados = new CubiculoPrivado[5];
        for (int i = 0; i < cubiculosPrivados.length; i++) {
            cubiculosPrivados[i] = new CubiculoPrivado();
        }

        // Inicialización de espacios recreativos
        espaciosRecreacion = new EspaciosRecreacion[]{
            new EspaciosRecreacion("Ping Pong", 2),
            new EspaciosRecreacion("Billar", 2),
            new EspaciosRecreacion("Futbol", 12),
            new EspaciosRecreacion("Futbol", 12),
            new EspaciosRecreacion("Baloncesto", 10),
            new EspaciosRecreacion("Tenis", 2),
            new EspaciosRecreacion("Tenis", 2),};

        // Inicializar canchas deportivas
        canchasDeportivas = new CanchaDeportiva[]{
            new CanchaDeportiva(0, "Fútbol", 12, "Cancha de fútbol 12"),
            new CanchaDeportiva(1, "Fútbol", 12, "Cancha de fútbol 12"),
            new CanchaDeportiva(2, "Baloncesto", 10, "Cancha de baloncesto"),
            new CanchaDeportiva(3, "Tenis", 2, "Cancha de tenis 1"),
            new CanchaDeportiva(4, "Tenis", 2, "Cancha de tenis 2")
        };

        // Inicializar arreglo de todas las reservas
        todasLasReservas = new ReservaGeneral[MAX_RESERVAS];
        cantidadReservas = 0;

        auditorio = new Auditorio();
    }

    // Método para reservar cubículo
    public boolean reservarCubiculo(int indice, int turno, String nombreReservante) {
        if (indice >= 0 && indice < cubiculosPrivados.length && turno >= 9 && turno <= 17) {
            boolean exito = cubiculosPrivados[indice].reservarTurno(turno);
            if (exito) {
                // Registrar la reserva en el arreglo general
                LocalDateTime inicio = LocalDateTime.now().withHour(turno).withMinute(0);
                LocalDateTime fin = inicio.plusHours(1);
                registrarReserva("Cubículo", indice, nombreReservante, 1, inicio, fin);
            }
            return exito;
        }
        return false;
    }

    // Versión compatible con el código existente
    public boolean reservarCubiculo(int indice, int turno) {
        return reservarCubiculo(indice, turno, "Usuario no especificado");
    }

    // Método para mostrar reservas de cubículos
    public void mostrarReservasCubiculos() {
        for (int i = 0; i < cubiculosPrivados.length; i++) {
            System.out.println("Cubiculo " + i + ":");
            cubiculosPrivados[i].mostrarReservas();
            System.out.println();
        }
    }

    public void imprimirNivel(int numeroNivel) {
        switch (numeroNivel) {
            case 1 -> {
                System.out.println("Nivel S1:");
                nivelS1.imprimirNivel();
            }
            case 2 -> {
                System.out.println("Nivel S2:");
                nivelS2.imprimirNivel();
            }
            case 3 -> {
                System.out.println("Nivel S3:");
                nivelS3.imprimirNivel();
            }
            default ->
                System.out.println("Numero de nivel invalido");
        }
    }

    public void mostrarEstacionamiento() {
        imprimirNivel(1);
        imprimirNivel(2);
        imprimirNivel(3);
    }

    // Nuevo método para obtener el estado del parqueo como un String
    public String getEstadoParqueoComoString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nivel S1:\n");
        sb.append(nivelToString(nivelS1));
        sb.append("\nNivel S2:\n");
        sb.append(nivelToString(nivelS2));
        sb.append("\nNivel S3:\n");
        sb.append(nivelToString(nivelS3));

        return sb.toString();
    }

    // Método auxiliar para convertir un Nivel a String
    private String nivelToString(Nivel nivel) {
        StringBuilder sb = new StringBuilder();
        char[][] espacios = nivel.getEspacios();

        // Imprimir encabezados de columnas
        sb.append("| 1 | 2 | 3 | 4 | 5 |\n");

        // Imprimir filas
        for (int i = 0; i < espacios.length; i++) {
            sb.append((char) ('A' + i)).append(" | ");
            for (int j = 0; j < espacios[i].length; j++) {
                sb.append(espacios[i][j]).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean reservarSala(int indice, String nombreReservante, int cantidadPersonas) {
        if (indice >= 0 && indice < salasReuniones.length) {
            boolean exito = salasReuniones[indice].reservar();
            if (exito) {
                LocalDateTime ahora = LocalDateTime.now();
                registrarReserva("Sala de Reuniones", indice, nombreReservante,
                        cantidadPersonas, ahora, ahora.plusHours(1));
            }
            return exito;
        }
        return false;
    }

    // Versión compatible con el código existente
    public boolean reservarSala(int indice) {
        return reservarSala(indice, "Usuario no especificado", 1);
    }

    public boolean reservarEspacioRecreacion(int indice, String nombreReservante, int cantidadPersonas) {
        if (indice >= 0 && indice < espaciosRecreacion.length) {
            boolean exito = espaciosRecreacion[indice].reservar();
            if (exito) {
                LocalDateTime ahora = LocalDateTime.now();
                registrarReserva("Espacio Recreativo", indice, nombreReservante,
                        cantidadPersonas, ahora, ahora.plusHours(1));
            }
            return exito;
        }
        return false;
    }

    // Versión compatible con el código existente
    public boolean reservarEspacioRecreacion(int indice) {
        return reservarEspacioRecreacion(indice, "Usuario no especificado", 1);
    }

    public boolean reservarAuditorio(int asistentes, String nombreReservante) {
        if (ocupacionAuditorio + asistentes > CAPACIDAD_AUDITORIO) {
            return false; // No se puede exceder la capacidad de personas en auditorio
        }
        ocupacionAuditorio += asistentes;

        LocalDateTime ahora = LocalDateTime.now();
        registrarReserva("Auditorio", 0, nombreReservante, asistentes, ahora, ahora.plusHours(2));

        return true; // Reserva exitosa
    }

    // Versión compatible con el código existente
    public boolean reservarAuditorio(int asistentes) {
        return reservarAuditorio(asistentes, "Usuario no especificado");
    }

    public void liberarAuditorio() {
        ocupacionAuditorio = 0; // Reinicia la ocupación (ej. para una nueva sesión)
        // Eliminar todas las reservas del auditorio
        eliminarReservas("Auditorio");
    }

    // Implementación de métodos faltantes
    public void liberarEstacionamiento(int espacios) {
        // Implementación simple para simulación
        System.out.println(espacios + " espacios liberados en el estacionamiento.");
    }

    public void liberarSala(int salaId) {
        if (salaId >= 0 && salaId < salasReuniones.length) {
            salasReuniones[salaId].liberar();
            // Eliminar la reserva del arreglo general
            eliminarReservasPorTipoYId("Sala de Reuniones", salaId);
            System.out.println("Sala " + salaId + " liberada.");
        } else {
            System.out.println("ID de sala inválido.");
        }
    }

    public void liberarCubiculo(int cubiculo, int turno) {
        if (cubiculo >= 0 && cubiculo < cubiculosPrivados.length && turno >= 9 && turno <= 17) {
            cubiculosPrivados[cubiculo].liberarTurno(turno);
            // Eliminar la reserva específica
            LocalDateTime horaReserva = LocalDateTime.now().withHour(turno).withMinute(0);
            eliminarReservaCubiculo(cubiculo, horaReserva);

            System.out.println("Cubículo " + cubiculo + " turno " + turno + " liberado.");
        } else {
            System.out.println("Datos inválidos para liberar cubículo.");
        }
    }

    public void liberarEspacioRecreativo(int espacioId) {
        if (espacioId >= 0 && espacioId < espaciosRecreacion.length) {
            espaciosRecreacion[espacioId].liberar();
            // Eliminar la reserva del arreglo general
            eliminarReservasPorTipoYId("Espacio Recreativo", espacioId);
            System.out.println("Espacio recreativo " + espacioId + " liberado.");
        } else {
            System.out.println("ID de espacio recreativo inválido.");
        }
    }

    public void liberarAuditorio(int asistentes) {
        if (ocupacionAuditorio - asistentes < 0) {
            ocupacionAuditorio = 0; // No puede ser negativo
        } else {
            ocupacionAuditorio -= asistentes;
        }
        // No eliminamos reservas aquí, ya que podría haber múltiples grupos
        System.out.println("Auditorio actualizado. Ocupación actual: " + ocupacionAuditorio);
    }

    // Método para mostrar espacios de parqueo en consola
    public void mostrarEspaciosParqueo() {
        System.out.println("Estado actual del parqueo:");
        nivelS1.imprimirNivel();
        nivelS2.imprimirNivel();
        nivelS3.imprimirNivel();
    }

    // Método para reservar cancha deportiva
    public boolean reservarCanchaDeportiva(int id, String nombreReservante, int cantidadPersonas) {
        if (id >= 0 && id < canchasDeportivas.length) {
            CanchaDeportiva cancha = canchasDeportivas[id];

            // Verificar que la cantidad de personas no exceda la capacidad
            if (cantidadPersonas > cancha.getCapacidad()) {
                return false;
            }

            if (cancha.reservar()) {
                // Registrar la reserva en el arreglo general
                LocalDateTime ahora = LocalDateTime.now();
                registrarReserva("Cancha " + cancha.getDeporte(),
                        id,
                        nombreReservante,
                        cantidadPersonas,
                        ahora,
                        ahora.plusHours(2)); // Las reservas de canchas duran 2 horas por defecto
                return true;
            }
        }
        return false;
    }

    // Método para liberar cancha deportiva
    public void liberarCanchaDeportiva(int id) {
        if (id >= 0 && id < canchasDeportivas.length) {
            canchasDeportivas[id].liberar();

            // Eliminar la reserva del arreglo general
            for (int i = 0; i < cantidadReservas; i++) {
                if (todasLasReservas[i].getTipoRecurso().startsWith("Cancha")
                        && todasLasReservas[i].getRecursoId() == id) {
                    eliminarReserva(i);
                    i--; // Retroceder una posición ya que el arreglo se ha desplazado
                }
            }

            System.out.println("Cancha deportiva " + id + " liberada.");
        } else {
            System.out.println("ID de cancha deportiva inválido.");
        }
    }

    // Método para listar todas las canchas deportivas
    public void listarCanchasDeportivas() {
        System.out.println("CANCHAS DEPORTIVAS DISPONIBLES:");
        for (CanchaDeportiva cancha : canchasDeportivas) {
            System.out.println(cancha);
        }
    }

    // Método para obtener las canchas deportivas como un String
    public String getCanchasDeportivasComoString() {
        StringBuilder sb = new StringBuilder("CANCHAS DEPORTIVAS DISPONIBLES:\n");
        for (CanchaDeportiva cancha : canchasDeportivas) {
            sb.append(cancha).append("\n");
        }
        return sb.toString();
    }

    // Método para registrar una reserva genérica (usado internamente)
    private void registrarReserva(String tipoRecurso, int recursoId, String nombreReservante,
            int cantidadPersonas, LocalDateTime inicio, LocalDateTime fin) {
        if (cantidadReservas < MAX_RESERVAS) {
            ReservaGeneral reserva = new ReservaGeneral(
                    tipoRecurso, recursoId, nombreReservante, cantidadPersonas, inicio, fin
            );
            todasLasReservas[cantidadReservas] = reserva;
            cantidadReservas++;
        } else {
            System.out.println("Error: No se pueden realizar más reservas. Límite alcanzado.");
        }
    }

    // Método para eliminar una reserva en una posición específica
    private void eliminarReserva(int posicion) {
        if (posicion >= 0 && posicion < cantidadReservas) {
            // Desplazar todas las reservas una posición hacia arriba
            for (int i = posicion; i < cantidadReservas - 1; i++) {
                todasLasReservas[i] = todasLasReservas[i + 1];
            }
            todasLasReservas[cantidadReservas - 1] = null;
            cantidadReservas--;
        }
    }

    // Método para eliminar todas las reservas de un tipo específico
    private void eliminarReservas(String tipoRecurso) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (todasLasReservas[i].getTipoRecurso().equals(tipoRecurso)) {
                eliminarReserva(i);
                i--; // Retroceder una posición ya que el arreglo se ha desplazado
            }
        }
    }

    // Método para eliminar reservas por tipo y ID
    private void eliminarReservasPorTipoYId(String tipoRecurso, int recursoId) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (todasLasReservas[i].getTipoRecurso().equals(tipoRecurso)
                    && todasLasReservas[i].getRecursoId() == recursoId) {
                eliminarReserva(i);
                i--; // Retroceder una posición ya que el arreglo se ha desplazado
            }
        }
    }

    // Método para eliminar una reserva específica de cubículo
    private void eliminarReservaCubiculo(int cubiculo, LocalDateTime horaReserva) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (todasLasReservas[i].getTipoRecurso().equals("Cubículo")
                    && todasLasReservas[i].getRecursoId() == cubiculo
                    && todasLasReservas[i].getHoraInicio().getHour() == horaReserva.getHour()) {
                eliminarReserva(i);
                return;
            }
        }
    }

    // Método principal para listar todas las reservas
    public void listarTodasLasReservas() {
        if (cantidadReservas == 0) {
            System.out.println("No hay reservas registradas en el sistema.");
            return;
        }

        System.out.println("\n===== LISTADO DE TODAS LAS RESERVAS =====");
        for (int i = 0; i < cantidadReservas; i++) {
            System.out.println((i + 1) + ". " + todasLasReservas[i]);
        }
        System.out.println("==========================================\n");
    }

    // Método para obtener todas las reservas como un String
    public String getTodasLasReservasComoString() {
        if (cantidadReservas == 0) {
            return "No hay reservas registradas en el sistema.";
        }

        StringBuilder sb = new StringBuilder("===== LISTADO DE TODAS LAS RESERVAS =====\n");
        for (int i = 0; i < cantidadReservas; i++) {
            sb.append((i + 1)).append(". ").append(todasLasReservas[i]).append("\n");
        }
        sb.append("==========================================");

        return sb.toString();
    }
}
