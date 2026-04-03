import java.util.ArrayList;
public class Pedido{
    public static final String LOCAL = "LOCAL";
    public static final String PARA_LLEVAR = "PARA_LLEVAR";
    public static final String PENDIENTE = "PENDIENTE";
    public static final String EN_PREPARACION = "EN_PREPARACION";
    public static final String LISTO = "LISTO";
    public static final String ENTREGADO = "ENTREGADO";
    public static final String CANCELADO = "CANCELADO";
    private String idPedido;
    private String idCliente;
    private String tipo;
    private String estado;
    private String fecha;
    private ArrayList<DetallePedido> detalles;
    public Pedido(String idPedido, String idCliente, String tipo){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.estado = PENDIENTE;
        this.fecha = java.time.LocalDate.now().toString();
        this.detalles = new ArrayList<DetallePedido>();
    }
    public void agregarProducto(String idProducto, int cantidad, double precioUnitario){
        detalles.add(new DetallePedido("DET-" + (detalles.size()+1), idProducto, cantidad, precioUnitario));
    }
    public void eliminarProducto(String idProducto){
        for(int i=0; i<detalles.size(); i++){
            if(detalles.get(i).getIdProducto().equals(idProducto)){
                detalles.remove(i);
                System.out.println("  Producto eliminado del pedido.");
                return;
            }
        }
        System.out.println("  Producto no encontrado en el pedido.");
    }
    public double calcularTotal(){
        double total = 0;
        for(DetallePedido d : detalles){
            total+=d.getSubTotal();
        }
        return total;
    }
    public void mostrarDetalle(){
        System.out.println("  Pedido  : " +idPedido);
        System.out.println("  Cliente : " +idCliente);
        System.out.println("  Tipo    : " +tipo);
        System.out.println("  Estado  : " +estado);
        System.out.println("  Fecha   : " +fecha);
        System.out.println("  --- Items ---");
        for(DetallePedido d : detalles){
            System.out.println(d);
        }
        System.out.printf("  TOTAL   : Bs. %.2f%n", calcularTotal());
    }
    public String getIdPedido(){return idPedido;}
    public String getIdCliente(){return idCliente;}
    public String getTipo(){return tipo;}
    public String getEstado(){return estado;}
    public String getFecha(){return fecha;}
    public ArrayList<DetallePedido> getDetalles(){return detalles;}
    public void setEstado(String e){estado = e;}
    public void setFecha(String f){fecha = f;}
    @Override
    public String toString(){
        return String.format("[%-8s] %-12s | %-11s | %-15s | Bs.%7.2f", idPedido, idCliente, tipo, estado, calcularTotal());
    }
}