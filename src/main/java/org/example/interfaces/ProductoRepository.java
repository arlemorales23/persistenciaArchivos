package org.example.interfaces;

import org.example.domain.Producto;

import java.util.List;

public interface ProductoRepository {
    List<Producto> findAll();
    Producto findById(int id);
    void save(Producto producto);
    void update(Producto producto);
    void delete(int id);
}