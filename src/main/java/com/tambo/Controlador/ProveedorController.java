package com.tambo.Controlador;


import com.tambo.Model.Proveedor;
import com.tambo.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/proveedores")
    public List<Proveedor> getAllClientes() {
        return proveedorService.getAllProveedor();
    }

    @PostMapping("/proveedores")
    public Proveedor createProveedor(@RequestBody Proveedor cliente) {
        return proveedorService.saveProveedor(cliente);
    }

    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> updateCliente(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor updatedCliente = proveedorService.updateProveedor(id, proveedor);
        return ResponseEntity.ok(updatedCliente);
    }

}
