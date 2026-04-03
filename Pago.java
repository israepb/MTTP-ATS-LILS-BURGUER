public class Pago{
    private String idPago;
    private double monto;
    private String idPedido;
    private String fecha;
    public Pago(String idPago, double monto, String idPedido){
        this.idPago = idPago;
        this.monto = monto;
        this.idPedido = idPedido;
        this.fecha = java.time.LocalDate.now().toString();
    }
    public void imprimirComprobante(){
        System.out.println("  --------------------------------");
        System.out.println("        COMPROBANTE DE PAGO");
        System.out.println("  --------------------------------");
        System.out.println("  ID     : " + idPago);
        System.out.println("  Pedido : " + idPedido);
        System.out.printf( "  Monto  : Bs. %.2f%n", monto);
        System.out.println("  Metodo : Efectivo");
        System.out.println("  Fecha  : " + fecha);
        System.out.println("  --------------------------------");
    }
    public String getIdPago(){return idPago;}
    public double getMonto(){return monto;}
    public String getIdPedido(){return idPedido;}
    public String getFecha(){return fecha;}
    public void setFecha(String f){fecha = f;}
    @Override
    public String toString(){
        return String.format("[%-6s] Pedido: %-8s | Bs.%7.2f | %s", idPago, idPedido, monto, fecha);
    }
}