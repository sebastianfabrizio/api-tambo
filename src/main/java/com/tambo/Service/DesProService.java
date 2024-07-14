package com.tambo.Service;


import com.tambo.Model.Des_pro;
import com.tambo.Repository.DesProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesProService {

    @Autowired
    private DesProRepository productoRepository;

    public List<Des_pro> getAllProductos() {
        return productoRepository.findAll();
    }

    public Des_pro saveProducto(Des_pro producto) {
        return productoRepository.save(producto);
    }
    public Des_pro updateProducto(Integer id, Des_pro producto) {
        Des_pro existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Des_pro not found with id " + id));
        existingProducto.setNom_producto(producto.getNom_producto());
        existingProducto.setMarca(producto.getMarca());
        existingProducto.setCategoria(producto.getCategoria());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setFecha((producto.getFecha()));
        existingProducto.setStock(producto.getStock());
        return productoRepository.save(existingProducto);
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }


}
