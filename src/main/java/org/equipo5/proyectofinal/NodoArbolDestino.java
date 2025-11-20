package org.equipo5.proyectofinal;

/**
 * Nodo para el árbol binario de destinos
 */
public class NodoArbolDestino {
    private final Destino destino;
    private NodoArbolDestino izquierdo;
    private NodoArbolDestino derecho;
    
    public NodoArbolDestino(Destino destino) {
        this.destino = destino;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    // Getters y Setters
    public Destino getDestino() { return destino; }
    public NodoArbolDestino getIzquierdo() { return izquierdo; }
    public NodoArbolDestino getDerecho() { return derecho; }
    
    public void setIzquierdo(NodoArbolDestino izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(NodoArbolDestino derecho) { this.derecho = derecho; }
}
