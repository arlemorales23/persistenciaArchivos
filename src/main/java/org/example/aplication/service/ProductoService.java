package org.example.aplication.service;

import org.example.domain.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(int id);
    void save(Producto producto);
    void update(Producto producto);
    void delete(int id);
}