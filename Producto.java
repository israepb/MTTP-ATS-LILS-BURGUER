public class Producto{
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private int tiempoPreparacion;
    private boolean disponible;
    private boolean esCombo;   
    private double descuento;  
    private String receta;
    public Producto(String idProducto, String nombre, String descripcion, double precio, String categoria, int tiempoPreparacion){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.tiempoPreparacion = tiempoPreparacion;
        this.disponible = true;
        this.esCombo = false;
        this.descuento = 0.0;
        this.receta = "";
    }
    public double getPrecioFinal(){
        return precio*(1.0-descuento/100.0);
    }
    public String getIdProducto(){return idProducto;}
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}
    public double getPrecio(){return precio;}
    public String getCategoria(){return categoria;}
    public int getTiempoPreparacion(){return tiempoPreparacion;}
    public boolean estaDisponible(){return disponible;}
    public boolean esCombo(){return esCombo;}
    public double getDescuento(){return descuento;}
    public String getReceta(){return receta;}
    public void setNombre(String n){nombre = n; }
    public void setDescripcion(String d){descripcion = d; }
    public void setPrecio(double p){precio = p; }
    public void setDisponible(boolean d){disponible = d; }
    public void setEsCombo(boolean b){esCombo = b; }
    public void setDescuento(double d){descuento = d; }
    public void setReceta(String r){receta = r; }
    @Override
    public String toString(){
        String etiqueta = esCombo ? String.format(" [COMBO -%.0f%%] Bs.%6.2f", descuento, getPrecioFinal()): "";
        return String.format("  [%-6s] %-25s Bs.%6.2f%s | %s | %d min", idProducto, nombre, precio, etiqueta, 
               disponible ? "Disponible" : "No disp.", tiempoPreparacion);
    }
}