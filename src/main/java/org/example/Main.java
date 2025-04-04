import org.example.devolucionModel;
import org.example.libroModel;
import org.example.prestamoModel;
import org.example.usuarioModel;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static usuarioModel usuarioActual = null;

    public static void main(String[] args) {
        // Inicializar datos
        usuarioModel.inicializarDatosQuemados();
        libroModel.inicializarDatos();

        // Menú principal
        int opcionPrincipal;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Seleccionar usuario");
            System.out.println("2. Salir del sistema");
            System.out.print("Seleccione una opción (1-2): ");
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            if (opcionPrincipal == 1) {
                seleccionarUsuario();
            }
        } while (opcionPrincipal != 2);
        scanner.close();
        System.out.println("Saliendo del sistema...");
    }

    private static void seleccionarUsuario() {
        System.out.println("\n=== SELECCIÓN DE USUARIO ===");
        System.out.println("1. Administrador");
        System.out.println("2. Estudiante");
        System.out.print("Seleccione su tipo de usuario (1-2): ");
        int tipoUsuario = scanner.nextInt();
        scanner.nextLine();

        if (tipoUsuario == 1) {
            usuarioActual = usuarioModel.getAdministrador();
            System.out.println("\nBienvenido Administrador " + usuarioActual.getNombre());
            menuAdministrador();
        } else if (tipoUsuario == 2) {
            usuarioActual = usuarioModel.getEstudiante();
            System.out.println("\nBienvenido Estudiante " + usuarioActual.getNombre());
            menuEstudiante();
        } else {
            System.out.println("Opción no válida");
        }
    }

    // ==================== MÉTODOS DE ADMINISTRADOR ====================
    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver inventario completo");
            System.out.println("3. Ver préstamos activos");
            System.out.println("4. Ver historial de devoluciones");
            System.out.println("5. Cambiar de usuario");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción (1-6): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> mostrarInventario(true);
                case 3 -> mostrarPrestamosActivos();
                case 4 -> mostrarDevoluciones();
                case 5 -> seleccionarUsuario();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5 && opcion != 6);
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
        System.out.print("Disponible (1=Sí, 0=No): ");
        boolean disponible = scanner.nextInt() == 1;
        scanner.nextLine();

        libroModel nuevoLibro = new libroModel(titulo, autor, categoria, id, disponible);
        libroModel.getInventario().add(nuevoLibro);
        System.out.println("✅ Libro agregado exitosamente!");
    }

    private static void mostrarPrestamosActivos() {
        System.out.println("\n=== PRÉSTAMOS ACTIVOS ===");
        if (prestamoModel.getPrestamos().isEmpty()) {
            System.out.println("No hay préstamos activos");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-15s %-15s\n",
                "ID Lib", "Título", "Usuario", "Fecha Préstamo", "Fecha Dev");
        for (prestamoModel prestamo : prestamoModel.getPrestamos()) {
            if (prestamo.isActivo()) {
                System.out.printf("%-5d %-20s %-15s %-15s %-15s\n",
                        prestamo.getId_libro(),
                        prestamo.getTitulo().substring(0, Math.min(prestamo.getTitulo().length(), 18)),
                        prestamo.getNombre(),
                        prestamo.getFechaPrestamo(),
                        prestamo.getFechaDevolucion());
            }
        }
    }

    private static void mostrarDevoluciones() {
        System.out.println("\n=== HISTORIAL DE DEVOLUCIONES ===");
        if (devolucionModel.getDevoluciones().isEmpty()) {
            System.out.println("No hay devoluciones registradas");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-15s\n",
                "ID Lib", "Título", "Usuario", "Fecha Devolución");
        for (devolucionModel devolucion : devolucionModel.getDevoluciones()) {
            System.out.printf("%-5d %-20s %-15s %-15s\n",
                    devolucion.getId_libro(),
                    devolucion.getTitulo().substring(0, Math.min(devolucion.getTitulo().length(), 18)),
                    devolucion.getNombre(),
                    devolucion.getFechaDevuelto());
        }
    }

    // ==================== MÉTODOS DE ESTUDIANTE ====================
    private static void menuEstudiante() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ESTUDIANTE ===");
            System.out.println("1. Ver libros disponibles");
            System.out.println("2. Solicitar préstamo");
            System.out.println("3. Devolver libro");
            System.out.println("4. Mis préstamos activos");
            System.out.println("5. Cambiar de usuario");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción (1-6): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> mostrarInventario(false);
                case 2 -> solicitarPrestamo();
                case 3 -> devolverLibro();
                case 4 -> mostrarPrestamosUsuario();
                case 5 -> seleccionarUsuario();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5 && opcion != 6);
    }

    private static void mostrarInventario(boolean completo) {
        System.out.println("\n=== INVENTARIO DE LIBROS ===");
        System.out.printf("%-5s %-25s %-20s %-15s %s\n",
                "ID", "Título", "Autor", "Categoría", "Disponible");

        for (libroModel libro : libroModel.getInventario()) {
            if (completo || libro.isDisponible()) {
                System.out.printf("%-5d %-25s %-20s %-15s %s\n",
                        libro.getId_libro(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getCategoria(),
                        libro.isDisponible() ? "✅" : "❌");
            }
        }
    }

    private static void solicitarPrestamo() {
        System.out.println("\n=== SOLICITAR PRÉSTAMO ===");
        mostrarInventario(false);

        System.out.print("\nIngrese el ID del libro a prestar: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();

        libroModel libro = libroModel.buscarPorId(idLibro);
        if (libro == null || !libro.isDisponible()) {
            System.out.println("❌ Error: El libro no está disponible para préstamo");
            return;
        }

        Date fechaPrestamo = new Date();
        Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7L * 24 * 60 * 60 * 1000)); // +7 días

        prestamoModel nuevoPrestamo = new prestamoModel(
                libro.getTitulo(), libro.getAutor(), libro.getCategoria(), libro.getId_libro(), false,
                usuarioActual.getNombre(), usuarioActual.getApellido(), "Estudiante", usuarioActual.getId_usuario(),
                fechaPrestamo, fechaDevolucion, true
        );

        prestamoModel.getPrestamos().add(nuevoPrestamo);
        libro.setDisponible(false);
        System.out.println("✅ Préstamo registrado exitosamente. Fecha de devolución: " + fechaDevolucion);
    }

    private static void devolverLibro() {
        System.out.println("\n=== DEVOLVER LIBRO ===");
        mostrarPrestamosUsuario();

        if (prestamoModel.getPrestamos().stream()
                .noneMatch(p -> p.isActivo() && p.getId_usuario() == usuarioActual.getId_usuario())) {
            return;
        }

        System.out.print("Ingrese el ID del libro a devolver: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();

        prestamoModel prestamo = prestamoModel.getPrestamos().stream()
                .filter(p -> p.isActivo() && p.getId_libro() == idLibro && p.getId_usuario() == usuarioActual.getId_usuario())
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            System.out.println("❌ Error: No tienes un préstamo activo con este libro");
            return;
        }

        libroModel libro = libroModel.buscarPorId(idLibro);
        if (libro != null) {
            libro.setDisponible(true);
        }

        prestamo.setActivo(false);
        devolucionModel devolucion = new devolucionModel(
                prestamo.getTitulo(), prestamo.getAutor(), prestamo.getCategoria(),
                prestamo.getId_libro(), true, prestamo.getNombre(), prestamo.getApellido(),
                prestamo.getTipo(), prestamo.getId_usuario(), prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(), false, new Date()
        );
        devolucionModel.getDevoluciones().add(devolucion);

        System.out.println("✅ Devolución registrada exitosamente");
    }

    private static void mostrarPrestamosUsuario() {
        System.out.println("\n=== MIS PRÉSTAMOS ACTIVOS ===");
        boolean tienePrestamos = false;

        for (prestamoModel prestamo : prestamoModel.getPrestamos()) {
            if (prestamo.isActivo() && prestamo.getId_usuario() == usuarioActual.getId_usuario()) {
                System.out.printf("ID Libro: %d - %s (Devuelve antes: %s)\n",
                        prestamo.getId_libro(),
                        prestamo.getTitulo(),
                        prestamo.getFechaDevolucion());
                tienePrestamos = true;
            }
        }

        if (!tienePrestamos) {
            System.out.println("No tienes préstamos activos actualmente");
        }
    }
}