package org.equipo5.proyectofinal;

/**
 * Nodo para la pila del historial
 */
public class NodoPila {
    private SolicitudEnvio solicitud;
    private NodoPila siguiente;
    
    public NodoPila(SolicitudEnvio solicitud) {
        this.solicitud = solicitud;
        this.siguiente = null;
    }
    
    // Getters y Setters
    public SolicitudEnvio getSolicitud() { return solicitud; }
    public NodoPila getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPila siguiente) { this.siguiente = siguiente; }
}
