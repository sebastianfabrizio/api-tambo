package com.tambo.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;
    private String nom_proveedor;
    private String dni;
    private String empresa;

}
