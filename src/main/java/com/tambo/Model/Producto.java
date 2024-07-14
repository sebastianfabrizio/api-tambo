package com.tambo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private String marca;
    private String categoria;
    private double precio;
    private String descripcion;
    private int stock;

}