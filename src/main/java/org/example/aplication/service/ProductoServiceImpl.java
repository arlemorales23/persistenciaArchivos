package org.example.aplication.service;

import org.example.domain.Producto;
import org.example.interfaces.ProductoRepository;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public void save(Producto producto) {
        validarProducto(producto);
        productoRepository.save(producto);
    }

    @Override
    public void update(Producto producto) {
        validarProducto(producto);
        productoRepository.update(producto);
    }

    @Override
    public void delete(int id) {
        productoRepository.delete(id);
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero");
        }
    }
}
