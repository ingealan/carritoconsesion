package services;

import modelos.Producto;

import java.util.Arrays;

import java.util.List;



public class ProductosServiceImplement implements ProductosServices {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1,"Laptop","Computacion",575.50),
                new Producto(2,"Cocina","Hogar",500),
                new Producto(3,"mouse","tecnologia",10));


    }
}
