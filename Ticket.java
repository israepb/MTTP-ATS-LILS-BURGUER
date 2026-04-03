public class Ticket{
    private String idTicket;
    private String idPedido;
    private double total;
    private String fecha;
    public Ticket(String idTicket, String idPedido, double total){
        this.idTicket = idTicket;
        this.idPedido = idPedido;
        this.total = total;
        this.fecha = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    public void imprimir(){
        System.out.println("  ================================");
        System.out.println("           LILS BURGER");
        System.out.println("   Av. Pando y Av. Portales");
        System.out.println("   Horario: 17:00 - 23:30");
        System.out.println("  ================================");
        System.out.println("  Ticket : " + idTicket);
        System.out.println("  Pedido : " + idPedido);
        System.out.println("  Fecha  : " + fecha);
        System.out.printf( "  TOTAL  : Bs. %.2f%n", total);
        System.out.println("  ================================");
        System.out.println("  Gracias por su preferencia!");
        System.out.println("  ================================");
    }
    public String getIdTicket(){return idTicket;}
    public String getIdPedido(){return idPedido;}
    public double getTotal(){return total;}
    public String getFecha(){return fecha;}
    @Override
    public String toString(){
        return String.format("[%-6s] Pedido: %-8s | Bs.%7.2f | %s", idTicket, idPedido, total, fecha);
    }
}