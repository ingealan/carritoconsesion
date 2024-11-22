package modelos;

public class Producto {
    //defino las variables de productos
    private int idProducto;
    private String nombre;
    private String categoria;
    private double precio;
    //Implementamos un constructor vacio

    public Producto() {

    }

    //Implementamos un constructor con todos los metodos
    public Producto(int idProducto, String nombre, String categoria, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
    //Implementamos los metodos get and set


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
