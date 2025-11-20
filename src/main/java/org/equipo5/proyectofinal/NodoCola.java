package org.equipo5.proyectofinal;

/**
 * Nodo para la cola de solicitudes
 */
public class NodoCola {
    private SolicitudEnvio solicitud;
    private NodoCola siguiente;
    
    public NodoCola(SolicitudEnvio solicitud) {
        this.solicitud = solicitud;
        this.siguiente = null;
    }
    
    // Getters y Setters
    public SolicitudEnvio getSolicitud() { return solicitud; }
    public NodoCola getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCola siguiente) { this.siguiente = siguiente; }
}
