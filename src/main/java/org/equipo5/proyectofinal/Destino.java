package org.equipo5.proyectofinal;

/**
 * Clase que representa un destino o vehículo en el sistema de transporte
 */
public class Destino {
    private final String id;
    private String nombre;
    private int capacidad;
    private String zona;
    
    public Destino(String id, String nombre, int capacidad, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.zona = zona;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public String getZona() { return zona; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setZona(String zona) { this.zona = zona; }
    
    @Override
    public String toString() {
        return "Destino{ID: " + id + ", Nombre: " + nombre + 
               ", Capacidad: " + capacidad + "lbs, Zona: " + zona + "}";
    }
}
