package com.tambo.Service;

import com.tambo.Model.Proveedor;
import com.tambo.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAllProveedor() {
        return proveedorRepository.findAll();
    }

    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
    public Proveedor updateProveedor(Long id, Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor not found with id " + id));
        existingProveedor.setNom_proveedor(proveedor.getNom_proveedor());
        existingProveedor.setDni(proveedor.getDni());
        existingProveedor.setEmpresa(proveedor.getEmpresa());
        return proveedorRepository.save(existingProveedor);
    }

    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
