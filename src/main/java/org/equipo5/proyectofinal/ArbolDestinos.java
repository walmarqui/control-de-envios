package org.equipo5.proyectofinal;

/**
 * Implementación manual de árbol binario para gestionar destinos
 */
public class ArbolDestinos {
    private NodoArbolDestino raiz;
    
    public ArbolDestinos() {
        this.raiz = null;
    }
    
    /**
     * Inserta un nuevo destino en el árbol ordenado por nombre
     */
    public void insertar(Destino destino) {
        raiz = insertarRecursivo(raiz, destino);
    }
    
    private NodoArbolDestino insertarRecursivo(NodoArbolDestino nodo, Destino destino) {
        if (nodo == null) {
            return new NodoArbolDestino(destino);
        }
        
        // Comparar por nombre para orden alfabético
        if (destino.getNombre().compareToIgnoreCase(nodo.getDestino().getNombre()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), destino));
        } else if (destino.getNombre().compareToIgnoreCase(nodo.getDestino().getNombre()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), destino));
        }
        
        return nodo;
    }
    
    /**
     * Busca un destino por nombre
     */
    public Destino buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }
    
    private Destino buscarRecursivo(NodoArbolDestino nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        
        if (nombre.equalsIgnoreCase(nodo.getDestino().getNombre())) {
            return nodo.getDestino();
        }
        
        if (nombre.compareToIgnoreCase(nodo.getDestino().getNombre()) < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), nombre);
        } else {
            return buscarRecursivo(nodo.getDerecho(), nombre);
        }
    }
    
    /**
     * Busca un destino por ID
     */
    public Destino buscarPorId(String id) {
        return buscarPorIdRecursivo(raiz, id);
    }
    
    private Destino buscarPorIdRecursivo(NodoArbolDestino nodo, String id) {
        if (nodo == null) {
            return null;
        }
        
        if (id.equals(nodo.getDestino().getId())) {
            return nodo.getDestino();
        }
        
        Destino encontradoIzq = buscarPorIdRecursivo(nodo.getIzquierdo(), id);
        if (encontradoIzq != null) {
            return encontradoIzq;
        }
        
        return buscarPorIdRecursivo(nodo.getDerecho(), id);
    }
    
    /**
     * Recorrido inorden
     */
    public void mostrarInorden() {
        System.out.println("=== DESTINOS DISPONIBLES (INORDEN) ===");
        mostrarInordenRecursivo(raiz);
    }
    
    private void mostrarInordenRecursivo(NodoArbolDestino nodo) {
        if (nodo != null) {
            mostrarInordenRecursivo(nodo.getIzquierdo());
            System.out.println(nodo.getDestino());
            mostrarInordenRecursivo(nodo.getDerecho());
        }
    }
    
    /**
     * Recorrido preorden
     */
    public void mostrarPreorden() {
        System.out.println("=== DESTINOS DISPONIBLES (PREORDEN) ===");
        mostrarPreordenRecursivo(raiz);
    }
    
    private void mostrarPreordenRecursivo(NodoArbolDestino nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDestino());
            mostrarPreordenRecursivo(nodo.getIzquierdo());
            mostrarPreordenRecursivo(nodo.getDerecho());
        }
    }
    
    /**
     * Recorrido postorden
     */
    public void mostrarPostorden() {
        System.out.println("=== DESTINOS DISPONIBLES (POSTORDEN) ===");
        mostrarPostordenRecursivo(raiz);
    }
    
    private void mostrarPostordenRecursivo(NodoArbolDestino nodo) {
        if (nodo != null) {
            mostrarPostordenRecursivo(nodo.getIzquierdo());
            mostrarPostordenRecursivo(nodo.getDerecho());
            System.out.println(nodo.getDestino());
        }
    }
    
    public boolean estaVacio() {
        return raiz == null;
    }
}
