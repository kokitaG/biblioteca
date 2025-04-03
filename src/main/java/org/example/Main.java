import org.example.libroModel;
import org.example.usuarioModel;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static usuarioModel usuarioActual = null;

    public static void main(String[] args) {
        // Inicializar datos
        usuarioModel.inicializarDatosQuemados();
        libroModel.inicializarDatos();

        // Menú principal
        menuPrincipal();

        scanner.close();
    }

    private static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Seleccionar usuario");
            System.out.println("2. Salir del sistema");
            System.out.print("Seleccione una opción (1-2): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch(opcion) {
                case 1:
                    seleccionarUsuario();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 2);
    }

    private static void seleccionarUsuario() {
        System.out.println("\n=== SELECCIÓN DE USUARIO ===");
        System.out.println("1. Administrador");
        System.out.println("2. Estudiante");
        System.out.print("Seleccione su tipo de usuario (1-2): ");
        int opcionUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch(opcionUsuario) {
            case 1:
                usuarioActual = usuarioModel.getAdministrador();
                System.out.println("\nBienvenido Administrador " + usuarioActual.getNombre());
                menuAdministrador();
                break;
            case 2:
                usuarioActual = usuarioModel.getEstudiante();
                System.out.println("\nBienvenido Estudiante " + usuarioActual.getNombre());
                menuEstudiante();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver inventario");
            System.out.println("3. Cambiar de usuario");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción (1-4): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch(opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    seleccionarUsuario();
                    return; // Salir de este menú
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 4);
    }

    private static void menuEstudiante() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ESTUDIANTE ===");
            System.out.println("1. Ver inventario");
            System.out.println("2. Cambiar de usuario");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción (1-3): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch(opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    seleccionarUsuario();
                    return; // Salir de este menú
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 3);
    }

    private static void agregarLibro() {
        System.out.println("\n=== AGREGAR NUEVO LIBRO ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("ID (número): ");
        int id = scanner.nextInt();

        System.out.print("Disponible (true/false): ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine(); // Limpiar buffer

        try {
            libroModel.agregarLibro(usuarioActual, new libroModel(titulo, autor, categoria, id, disponible));
            System.out.println("¡Libro agregado exitosamente!");
        } catch (SecurityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarInventario() {
        System.out.println("\n=== INVENTARIO DE LIBROS ===");
        List<libroModel> libros = libroModel.obtenerLibros(usuarioActual);

        if(libros.isEmpty()) {
            System.out.println("No hay libros en el inventario");
            return;
        }

        for(libroModel libro : libros) {
            System.out.printf(
                    "Título: %s\nAutor: %s\nCategoría: %s\nID: %d\nEstado: %s\n----------------\n",
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.getId_libro(),
                    libro.isDisponible() ? "Disponible" : "Prestado"
            );
        }
        System.out.println("Total de libros: " + libros.size());
    }
}