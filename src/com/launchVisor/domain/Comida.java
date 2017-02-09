package com.launchVisor.domain;

public class Comida {

    private Long id;
    private EstadoComida estado = EstadoComida.DISPONIBLE;
    private Double precio;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoComida getEstado() {
        return estado;
    }

    public void setEstado(EstadoComida estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
