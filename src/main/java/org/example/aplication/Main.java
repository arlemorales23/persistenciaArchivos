package org.example.aplication;

import org.example.aplication.service.ProductoService;
import org.example.aplication.service.ProductoServiceImpl;
import org.example.domain.Producto;
import org.example.infraestructure.repository.ProductoRepositoryImpl;
import org.example.interfaces.ProductoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoService productoService;

    static {
        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        productoService = new ProductoServiceImpl(productoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar productos");
            System.out.println("2. Crear producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarProductos();
                    break;
                case 2:
                    crearProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Listado de productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static void crearProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        try {
            productoService.save(producto);
            System.out.println("Producto creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el producto con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del producto (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            producto.setNombre(nombre);
        }

        System.out.print("Ingrese el nuevo precio del producto (0 para no cambiar): ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        if (precio > 0) {
            producto.setPrecio(precio);
        }

        try {
            productoService.update(producto);
            System.out.println("Producto actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el producto con ID " + id);
            return;
        }

        productoService.delete(id);
        System.out.println("Producto eliminado exitosamente.");
    }
}
