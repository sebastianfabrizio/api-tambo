package com.tambo.Model;




import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "des_producto")
public class Des_pro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;
    private String nom_producto;
    private String marca;
    private String categoria;
    private double precio;
    private String descripcion;
    private String fecha;
    private int stock;
}
