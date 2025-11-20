package org.equipo5.proyectofinal;

/**
 * Clase que representa una solicitud de envío
 */
public class SolicitudEnvio {
    private static int contador = 1;
    private final int id;
    private final String cliente;
    private final String destino;
    private final String tipoPaquete;
    private final double peso;
    private String estado;
    
    public SolicitudEnvio(String cliente, String destino, String tipoPaquete, double peso) {
        this.id = contador++;
        this.cliente = cliente;
        this.destino = destino;
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.estado = "Pendiente";
    }
    
    // Getters
    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getDestino() { return destino; }
    public String getTipoPaquete() { return tipoPaquete; }
    public double getPeso() { return peso; }
    public String getEstado() { return estado; }
    
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return "Solicitud{#" + id + ", Cliente: " + cliente + ", Destino: " + destino + 
               ", Paquete: " + tipoPaquete + ", Peso: " + peso + "lbs, Estado: " + estado + "}";
    }
}
