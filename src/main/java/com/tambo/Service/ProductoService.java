package com.tambo.Service;

import com.tambo.Model.Producto;
import com.tambo.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    public Producto updateProducto(Long id, Producto producto) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto not found with id " + id));
        existingProducto.setProducto(producto.getProducto());
        existingProducto.setMarca(producto.getMarca());
        existingProducto.setCategoria(producto.getCategoria());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setStock(producto.getStock());
        return productoRepository.save(existingProducto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> getProductosByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }


}