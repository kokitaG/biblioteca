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
                System.out.println("\n=== SELECCIÓN DE USUARIO ===");
                System.out.println("1. Administrador");
                System.out.println("2. Estudiante");
                System.out.print("Seleccione su tipo de usuario (1-2): ");
                int tipoUsuario = scanner.nextInt();
                scanner.nextLine();

                if (tipoUsuario == 1) {
                    usuarioActual = usuarioModel.getAdministrador();
                    System.out.println("\nBienvenido Administrador " + usuarioActual.getNombre());
                    // Menú administrador existente...
                } else if (tipoUsuario == 2) {
                    usuarioActual = usuarioModel.getEstudiante();
                    System.out.println("\nBienvenido Estudiante " + usuarioActual.getNombre());

                    // Menú del estudiante integrado directamente
                    int opcionEstudiante;
                    do {
                        System.out.println("\n=== MENÚ ESTUDIANTE ===");
                        System.out.println("1. Ver inventario");
                        System.out.println("2. Solicitar préstamo");
                        System.out.println("3. Devolver libro");
                        System.out.println("4. Cambiar de usuario");
                        System.out.println("5. Volver al menú principal");
                        System.out.print("Seleccione una opción (1-5): ");
                        opcionEstudiante = scanner.nextInt();
                        scanner.nextLine();

                        if (opcionEstudiante == 1) {
                            // Mostrar inventario
                            System.out.println("\n=== INVENTARIO DE LIBROS ===");
                            for (libroModel libro : libroModel.getInventario()) {
                                System.out.printf("ID: %d - %s (%s) - %s\n",
                                        libro.getId_libro(),
                                        libro.getTitulo(),
                                        libro.getAutor(),
                                        libro.isDisponible() ? "Disponible" : "Prestado");
                            }
                        } else if (opcionEstudiante == 2) {
                            // Solicitar préstamo
                            System.out.println("\n=== SOLICITAR PRÉSTAMO ===");
                            System.out.print("Ingrese ID del libro: ");
                            int idLibro = scanner.nextInt();
                            scanner.nextLine();

                            libroModel libro = libroModel.buscarPorId(idLibro);
                            if (libro != null && libro.isDisponible()) {
                                Date fechaPrestamo = new Date();
                                Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7L * 24 * 60 * 60 * 1000));

                                prestamoModel prestamo = new prestamoModel(
                                        libro.getTitulo(), libro.getAutor(), libro.getCategoria(), libro.getId_libro(), false,
                                        usuarioActual.getNombre(), usuarioActual.getApellido(), "Estudiante", usuarioActual.getId_usuario(),
                                        fechaPrestamo, fechaDevolucion, true
                                );

                                prestamoModel.getPrestamos().add(prestamo);
                                libro.setDisponible(false);
                                System.out.println("Préstamo registrado exitosamente. Fecha devolución: " + fechaDevolucion);
                            } else {
                                System.out.println("El libro no está disponible para préstamo");
                            }
                        } else if (opcionEstudiante == 3) {
                            // Devolver libro
                            System.out.println("\n=== DEVOLVER LIBRO ===");
                            System.out.print("Ingrese ID del libro: ");
                            int idLibro = scanner.nextInt();
                            scanner.nextLine();

                            boolean prestamoEncontrado = false;
                            for (prestamoModel prestamo : prestamoModel.getPrestamos()) {
                                if (prestamo.getId_libro() == idLibro && prestamo.isActivo() &&
                                        prestamo.getId_usuario() == usuarioActual.getId_usuario()) {

                                    libroModel libro = libroModel.buscarPorId(idLibro);
                                    if (libro != null) {
                                        libro.setDisponible(true);
                                    }
                                    prestamo.setActivo(false);
                                    prestamoEncontrado = true;

                                    // Registrar devolución
                                    devolucionModel devolucion = new devolucionModel(
                                            prestamo.getTitulo(), prestamo.getAutor(), prestamo.getCategoria(),
                                            prestamo.getId_libro(), true, prestamo.getNombre(), prestamo.getApellido(),
                                            prestamo.getTipo(), prestamo.getId_usuario(), prestamo.getFechaPrestamo(),
                                            prestamo.getFechaDevolucion(), false, new Date()
                                    );
                                    devolucionModel.getDevoluciones().add(devolucion);

                                    System.out.println("Devolución registrada exitosamente");
                                    break;
                                }
                            }
                            if (!prestamoEncontrado) {
                                System.out.println("No tienes un préstamo activo con este libro");
                            }
                        }
                    } while (opcionEstudiante != 4 && opcionEstudiante != 5);
                }
            }
        } while (opcionPrincipal != 2);
        scanner.close();
    }
}