public class Ingrediente{
    private String id;
    private String nombre;
    private String unidad;
    private double stock;
    private double minimo;
    public Ingrediente(String id, String nombre, String unidad, double stock, double minimo){
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.stock = stock;
        this.minimo = minimo;
    }
    public boolean stockBajo(){ 
        return stock <= minimo; 
    }
    public String getId(){return id;}
    public String getNombre(){return nombre;}
    public String getUnidad(){return unidad;}
    public double getStock(){return stock;}
    public double getMinimo(){return minimo;}
    public void setStock(double s){stock = s;}
    public void setMinimo(double m){minimo = m;}
    @Override
    public String toString(){
        String alerta = stockBajo() ? " !! STOCK BAJO" : "";
        return String.format("[%-6s] %-20s | Stock: %6.2f %-5s | Min: %.2f%s", id, nombre, stock, unidad, minimo, alerta);
    }
}