package com.tambo.Controlador;

import com.tambo.Model.Des_pro;
import com.tambo.Model.Producto;
import com.tambo.Service.DesProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class DesProController {

    @Autowired
    private DesProService productoService;

    @GetMapping("/des")
    public List<Des_pro> getAllProductos() {
        return productoService.getAllProductos();
    }

    @PostMapping("/des")
    public Des_pro createProducto(@RequestBody Des_pro producto) {
        return productoService.saveProducto(producto);
    }

    @DeleteMapping("/des/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/des/{id}")
    public ResponseEntity<Des_pro> updateProducto(@PathVariable Integer id, @RequestBody Des_pro producto) {
        Des_pro updatedProducto = productoService.updateProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }




}



