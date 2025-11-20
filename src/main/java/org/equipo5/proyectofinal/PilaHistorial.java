package org.equipo5.proyectofinal;

/**
 * Implementación manual de pila para el historial de envíos
 */
public class PilaHistorial {
    private NodoPila tope;
    private int tamaño;
    
    public PilaHistorial() {
        this.tope = null;
        this.tamaño = 0;
    }
    
    /**
     * Agrega una solicitud procesada a la pila
     */
    public void apilar(SolicitudEnvio solicitud) {
        NodoPila nuevoNodo = new NodoPila(solicitud);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        tamaño++;
    }
    
    /**
     * Remueve y retorna la solicitud del tope de la pila
     */
    public SolicitudEnvio desapilar() {
        if (estaVacia()) {
            return null;
        }
        
        SolicitudEnvio solicitud = tope.getSolicitud();
        tope = tope.getSiguiente();
        tamaño--;
        return solicitud;
    }
    
    /**
     * Muestra el historial de envíos procesados
     */
    public void mostrarHistorial() {
        if (estaVacia()) {
            System.out.println("No hay envíos en el historial.");
            return;
        }
        
        //System.out.println("=== HISTORIAL DE ENVIOS PROCESADOS ===");
        NodoPila actual = tope;
        int posicion = 1;
        
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getSolicitud());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
    
    public boolean estaVacia() {
        return tope == null;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public SolicitudEnvio verTope() {
        return estaVacia() ? null : tope.getSolicitud();
    }
}
