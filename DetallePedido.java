public class DetallePedido{
    private String idDetalle;
    private String idProducto;
    private int cantidad;
    private double precioUnitario;
    public DetallePedido(String idDetalle, String idProducto, int cantidad, double precioUnitario){
        this.idDetalle = idDetalle;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    public double getSubTotal(){return cantidad*precioUnitario;}
    public String getIdDetalle(){return idDetalle;}
    public String getIdProducto(){return idProducto;}
    public int getCantidad(){return cantidad;}
    public double getPrecioUnitario(){return precioUnitario;}
    public void setCantidad(int c){cantidad=c;}
    @Override
    public String toString(){
        return String.format("    %-8s | Cant: %2d | Bs.%6.2f | Subtotal: Bs.%7.2f", idProducto, cantidad, precioUnitario, getSubTotal());
    }
}