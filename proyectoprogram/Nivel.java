package proyectoprogram;

public class Nivel {

    private char[][] espacios;
    private int filas;
    private int columnas;

    public Nivel(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.espacios = new char[filas][columnas];

        // Inicializar todos los espacios como libres
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                espacios[i][j] = 'O'; // O = espacio libre
            }
        }

        // Nivel S1 (4x5) - Configuración según la imagen
        if (filas == 4 && columnas == 5) {
            // Fila A
            espacios[0][1] = 'P';
            espacios[0][4] = 'P';

            // Fila C
            espacios[2][1] = 'P';

            // Fila D (incluye espacios para discapacitados y directores)
            espacios[3][0] = 'E';
            espacios[3][1] = 'E';
            espacios[3][2] = 'E';
            espacios[3][3] = 'D';
        } // Nivel S2 (5x5) - Configuración según la imagen
        else if (filas == 5 && columnas == 5) {
            // Fila A
            espacios[0][1] = 'P';
            espacios[0][2] = 'P';

            // Fila B
            espacios[1][1] = 'D';

            // Fila C
            espacios[2][1] = 'P';

            // Fila D
            espacios[3][4] = 'P';

            // Fila E (espacios para discapacitados)
            espacios[4][0] = 'E';
            espacios[4][1] = 'E';
            espacios[4][2] = 'E';
            espacios[4][3] = 'P';
            espacios[4][4] = 'P';
        } // Nivel S3 (6x5) - Configuración según la imagen
        else if (filas == 6 && columnas == 5) {
            // Fila A
            espacios[0][1] = 'P';
            espacios[0][2] = 'P';
            espacios[0][4] = 'P';

            // Fila C
            espacios[2][1] = 'P';

            // Fila D
            espacios[3][4] = 'P';

            // Fila E
            espacios[4][0] = 'P';
            espacios[4][2] = 'P';
            espacios[4][3] = 'P';
            espacios[4][4] = 'P';

            // Fila F (espacios para discapacitados y directores)
            espacios[5][0] = 'E';
            espacios[5][1] = 'E';
            espacios[5][2] = 'E';
            espacios[5][3] = 'D';
            espacios[5][4] = 'D';
        }
    }

    public boolean ocuparEspacio(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            if (espacios[fila][columna] == 'O') {
                espacios[fila][columna] = 'P';
                return true;
            }
        }
        return false;
    }

    public boolean liberarEspacio(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            if (espacios[fila][columna] == 'P') {
                espacios[fila][columna] = 'O';
                return true;
            }
        }
        return false;
    }

    public void imprimirNivel() {
        // Imprimir encabezado de columnas
        System.out.print("| ");
        for (int j = 0; j < columnas; j++) {
            System.out.print((j + 1) + " | ");
        }
        System.out.println();

        // Imprimir filas
        for (int i = 0; i < filas; i++) {
            System.out.print((char) ('A' + i) + " | ");
            for (int j = 0; j < columnas; j++) {
                System.out.print(espacios[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public char[][] getEspacios() {
        return espacios;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
