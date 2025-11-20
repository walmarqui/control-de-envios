/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.equipo5.proyectofinal;

/**
 *
 * @author  User
 * Walter Alberto Martinez Quintanilla 20182030894
 * Henry Daniel Briceno Reyes 20191030545
 * Jeyson Joseph Mendez Alvarado 20232000748
 */

import java.util.Scanner;

/**
 * Sistema principal de gestión de envíos y solicitudes de transporte
 */

public class Proyecto {
    private static ArbolDestinos arbolDestinos = new ArbolDestinos();
    private static ColaSolicitudes colaSolicitudes = new ColaSolicitudes();
    private static PilaHistorial pilaHistorial = new PilaHistorial();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        inicializarDatosPrueba();
        mostrarMenuPrincipal();
    }
    
    private static void inicializarDatosPrueba() {
        // Destinos de prueba
        arbolDestinos.insertar(new Destino("D001", "Tegucigalpa", 100, "Centro"));
        arbolDestinos.insertar(new Destino("D002", "SPS", 150, "Norte"));
        arbolDestinos.insertar(new Destino("D003", "Ceiba", 120, "Atlantica"));
        arbolDestinos.insertar(new Destino("D004", "Santa Rosa", 100, "Occidente"));
        arbolDestinos.insertar(new Destino("D005", "Choluteca", 100, "Sur"));
        
        // Solicitudes de prueba
        colaSolicitudes.encolar(new SolicitudEnvio("Juan Pérez", "Tegucigalpa", "Documentos", 0.5));
        colaSolicitudes.encolar(new SolicitudEnvio("María García", "Santa Rosa", "Electrodomesticos", 75.0));
       
    }
    
    private static void mostrarMenuPrincipal() {
        int opcion;
        
        do {
            System.out.println("\n" + "=".repeat(75));
            System.out.println("====== SISTEMA DE GESTION DE ENVIOS Y SOLICITUDES DE TRANSPORTE ======");
            System.out.println("=".repeat(75));
            System.out.println("1. Registrar nuevo destino o vehículo");
            System.out.println("2. Buscar destino o vehículo");
            System.out.println("3. Mostrar destinos disponibles");
            System.out.println("4. Registrar nueva solicitud de envío");
            System.out.println("5. Procesar siguiente solicitud");
            System.out.println("6. Ver historial de envíos procesados");
            System.out.println("7. Revertir último envío procesado");
            System.out.println("8. Mostrar solicitudes pendientes");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }
            
            switch (opcion) {
                case 1:
                    registrarDestino();
                    break;
                case 2:
                    buscarDestino();
                    break;
                case 3:
                    mostrarDestinos();
                    break;
                case 4:
                    registrarSolicitudConValidacion();
                    break;
                case 5:
                    procesarSolicitud();
                    break;
                case 6:
                    verHistorial();
                    break;
                case 7:
                    revertirEnvio();
                    break;
                case 8:
                    mostrarSolicitudesPendientes();
                    break;
                case 9:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        } while (opcion != 9);
        
        scanner.close();
    }
    
    // MÉTODO MEJORADO CON VALIDACIÓN DE DESTINO
    private static void registrarSolicitudConValidacion() {
        System.out.println("\n--- REGISTRAR NUEVA SOLICITUD ---");
        
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();
        
        // Validación mejorada del destino
        String destino = validarYSeleccionarDestino();
        if (destino == null) {
            System.out.println("No se pudo registrar la solicitud. Destino inválido.");
            return;
        }
        
        System.out.print("Tipo de paquete: ");
        String tipoPaquete = scanner.nextLine();
        
        double peso = validarPeso();
        if (peso < 0) {
            System.out.println("No se pudo registrar la solicitud. Peso inválido.");
            return;
        }
        
        SolicitudEnvio nuevaSolicitud = new SolicitudEnvio(cliente, destino, tipoPaquete, peso);
        colaSolicitudes.encolar(nuevaSolicitud);
        
        System.out.println("Solicitud registrada exitosamente: " + nuevaSolicitud);
        System.out.println("Solicitudes pendientes: " + colaSolicitudes.getTamaño());

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Valida y permite seleccionar un destino válido
     * @return El nombre del destino válido, o null si no se seleccionó
     */
    private static String validarYSeleccionarDestino() {
        String destino;
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        
        do {
            System.out.print("Destino: ");
            destino = scanner.nextLine().trim();
            
            // Verificar si el destino existe
            if (arbolDestinos.buscar(destino) != null) {
                return destino; // Destino válido
            }
            
            intentos++;
            System.out.println("El destino '" + destino + "' no existe.");
            
            if (intentos < MAX_INTENTOS) {
                System.out.println("\nDestinos disponibles:");
                arbolDestinos.mostrarInorden();
                System.out.println("\nPor favor, ingrese un destino válido de la lista.");
                System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentos));
            } else {
                System.out.println("Límite de intentos alcanzado.");
                System.out.println("¿Desea ver los destinos disponibles nuevamente? (s/n)");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    arbolDestinos.mostrarInorden();
                }
                return null;
            }
            
        } while (intentos < MAX_INTENTOS);
        
        return null;
    }
    
    /**
     * Valida que el peso sea un número positivo
     * @return El peso validado, o -1 si es inválido
     */
    private static double validarPeso() {
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        
        do {
            try {
                System.out.print("Peso (lbs): ");
                double peso = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                
                if (peso <= 0) {
                    System.out.println("El peso debe ser mayor a 0.");
                    intentos++;
                } else if (peso > 200) {
                    System.out.println("Advertencia: Peso muy alto (" + peso + "lbs).");
                    System.out.print("¿Confirmar este peso? (s/n): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        return peso;
                    } else {
                        intentos++;
                    }
                } else {
                    return peso; // Peso válido
                }
                
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido para el peso.");
                scanner.nextLine(); // Limpiar buffer
                intentos++;
            }
            
            if (intentos < MAX_INTENTOS) {
                System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentos));
            }
            
        } while (intentos < MAX_INTENTOS);
        
        System.out.println("Límite de intentos alcanzado para el peso.");
        return -1;
    }
    
    // Los demás métodos permanecen igual...
    private static void registrarDestino() {
        System.out.println("\n--- REGISTRAR NUEVO DESTINO ---");
        
        System.out.print("ID del destino: ");
        String id = scanner.nextLine();
        
        System.out.print("Nombre del destino: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Capacidad máxima (lbs): ");
        int capacidad;
        try {
            capacidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error: La capacidad debe ser un número entero.");
            scanner.nextLine(); // Limpiar buffer
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.print("Zona: ");
        String zona = scanner.nextLine();
        
        Destino nuevoDestino = new Destino(id, nombre, capacidad, zona);
        arbolDestinos.insertar(nuevoDestino);
        
        System.out.println("Destino registrado exitosamente: " + nuevoDestino);
    }
    
    private static void buscarDestino() {
        System.out.println("\n--- BUSCAR DESTINO ---");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por ID");
        System.out.print("Seleccione opción de búsqueda: ");
        
        int opcion;
        try {
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error: Debe ingresar un número (1 o 2).");
            scanner.nextLine(); // Limpiar buffer
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        Destino destino = null;
        
        if (opcion == 1) {
            System.out.print("Ingrese el nombre del destino: ");
            String nombre = scanner.nextLine();
            destino = arbolDestinos.buscar(nombre);
        } else if (opcion == 2) {
            System.out.print("Ingrese el ID del destino: ");
            String id = scanner.nextLine();
            destino = arbolDestinos.buscarPorId(id);
        } else {
            System.out.println("Opción no válida.");
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        if (destino != null) {
            System.out.println("Destino encontrado: " + destino);
        } else {
            System.out.println("Destino no encontrado.");
        }
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void mostrarDestinos() {
        if (arbolDestinos.estaVacio()) {
            System.out.println("No hay destinos registrados.");
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\n--- MOSTRAR DESTINOS ---");
        System.out.println("1. Mostrar en orden (alfabético)");
        System.out.println("2. Mostrar en preorden");
        System.out.println("3. Mostrar en postorden");
        System.out.print("Seleccione el tipo de recorrido: ");
        
        int opcion;
        try {
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        switch (opcion) {
            case 1:
                arbolDestinos.mostrarInorden();
                break;
            case 2:
                arbolDestinos.mostrarPreorden();
                break;
            case 3:
                arbolDestinos.mostrarPostorden();
                break;
            default:
                System.out.println("Opción no válida.");
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void procesarSolicitud() {
        System.out.println("\n--- PROCESAR SOLICITUD ---");
        
        if (colaSolicitudes.estaVacia()) {
            System.out.println("No hay solicitudes pendientes para procesar.");
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        SolicitudEnvio solicitud = colaSolicitudes.desencolar();
        solicitud.setEstado("Procesado");
        pilaHistorial.apilar(solicitud);
        
        System.out.println("Solicitud procesada exitosamente:");
        System.out.println(solicitud);
        System.out.println("Solicitudes pendientes restantes: " + colaSolicitudes.getTamaño());

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void verHistorial() {
        System.out.println("\n--- HISTORIAL DE ENVIOS ---");
        pilaHistorial.mostrarHistorial();

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void revertirEnvio() {
        System.out.println("\n--- REVERTIR ULTIMO ENVIO ---");
        
        if (pilaHistorial.estaVacia()) {
            System.out.println("No hay envíos para revertir.");

            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        SolicitudEnvio solicitud = pilaHistorial.desapilar();
        solicitud.setEstado("Pendiente");
        colaSolicitudes.encolar(solicitud);
        
        System.out.println("Ultimo envío revertido exitosamente:");
        System.out.println(solicitud);
        System.out.println("La solicitud ha sido reingresada a la cola de pendientes.");

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void mostrarSolicitudesPendientes() {
        System.out.println("\n--- SOLICITUDES PENDIENTES ---");
        colaSolicitudes.mostrarSolicitudes();

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}