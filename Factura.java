public class Factura{
    private String idFactura;
    private String nit;
    private String razonSocial;
    private double total;
    private String idPedido;
    private String fecha;
    public Factura(String idFactura, String nit, String razonSocial, double total, String idPedido){
        this.idFactura = idFactura;
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.total = total;
        this.idPedido = idPedido;
        this.fecha = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    public void imprimir(){
        System.out.println("  ================================");
        System.out.println("         FACTURA FISCAL");
        System.out.println("        LILS BURGER S.R.L.");
        System.out.println("  ================================");
        System.out.println("  N° Factura   : " + idFactura);
        System.out.println("  Fecha        : " + fecha);
        System.out.println("  NIT          : " + nit);
        System.out.println("  Razon Social : " + razonSocial);
        System.out.println("  Pedido       : " + idPedido);
        System.out.printf( "  TOTAL        : Bs. %.2f%n", total);
        System.out.println("  ================================");
    }
    public String getIdFactura(){return idFactura;}
    public String getNit(){return nit;}
    public String getRazonSocial(){return razonSocial;}
    public double getTotal(){return total;}
    public String getIdPedido(){return idPedido;}
    public String getFecha(){return fecha;}
    @Override
    public String toString(){
        return String.format("[%-6s] NIT: %-12s | %-20s | Bs.%7.2f | %s", idFactura, nit, razonSocial, total, fecha);
    }
}
