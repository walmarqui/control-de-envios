package org.equipo5.proyectofinal;

/**
 * Implementación manual de cola para gestionar solicitudes de envío
 */
public class ColaSolicitudes {
    private NodoCola frente;
    private NodoCola fin;
    private int tamaño;
    
    public ColaSolicitudes() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }
    
    /**
     * Agrega una solicitud al final de la cola
     */
    public void encolar(SolicitudEnvio solicitud) {
        NodoCola nuevoNodo = new NodoCola(solicitud);
        
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
        }
        fin = nuevoNodo;
        tamaño++;
    }
    
    /**
     * Remueve y retorna la solicitud del frente de la cola
     */
    public SolicitudEnvio desencolar() {
        if (estaVacia()) {
            return null;
        }
        
        SolicitudEnvio solicitud = frente.getSolicitud();
        frente = frente.getSiguiente();
        
        if (frente == null) {
            fin = null;
        }
        
        tamaño--;
        return solicitud;
    }
    
    /**
     * Muestra todas las solicitudes en la cola
     */
    public void mostrarSolicitudes() {
        if (estaVacia()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        
        //System.out.println("=== SOLICITUDES PENDIENTES ===");
        NodoCola actual = frente;
        int posicion = 1;
        
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getSolicitud());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public SolicitudEnvio verFrente() {
        return estaVacia() ? null : frente.getSolicitud();
    }
}
