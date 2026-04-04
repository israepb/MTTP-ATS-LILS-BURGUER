public class Ticket{
    private String idTicket;
    private String idPedido;
    private String cliente;
    private double total;
    private String fecha;
    private String detallePedido;
    public Ticket(String idTicket, String idPedido, double total){
        this(idTicket, idPedido, "", total, java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "");
    }
    public Ticket(String idTicket, String idPedido, String cliente, double total, String detallePedido){
        this(idTicket, idPedido, cliente, total, java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), detallePedido);
    }
    public Ticket(String idTicket, String idPedido, String cliente, double total, String fecha, String detallePedido){
        this.idTicket = idTicket;
        this.idPedido = idPedido;
        this.cliente = cliente == null ? "" : cliente;
        this.total = total;
        this.fecha = fecha == null || fecha.trim().isEmpty() ? java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : fecha;
        this.detallePedido = detallePedido == null ? "" : detallePedido;
    }
    public void imprimir(){
        System.out.println("  ================================");
        System.out.println("           LILS BURGER");
        System.out.println("   Av. Pando y Av. Portales");
        System.out.println("   Horario: 17:00 - 23:30");
        System.out.println("  ================================");
        System.out.println("  Ticket : " + idTicket);
        System.out.println("  Pedido : " + idPedido);
        if(!cliente.trim().isEmpty()){
            System.out.println("  Cliente: " + cliente);
        }
        System.out.println("  Fecha  : " + fecha);
        if(!detallePedido.trim().isEmpty()){
            System.out.println("  --- Detalle ---");
            String[] items = detallePedido.split(";\\s*");
            for(String item : items){
                if(!item.trim().isEmpty()){
                    System.out.println("  " + item.trim());
                }
            }
        }
        System.out.printf("  TOTAL  : Bs. %.2f%n", total);
        System.out.println("  ================================");
        System.out.println("  Gracias por su preferencia!");
        System.out.println("  ================================");
    }
    public String getIdTicket(){return idTicket;}
    public String getIdPedido(){return idPedido;}
    public String getCliente(){return cliente;}
    public double getTotal(){return total;}
    public String getFecha(){return fecha;}
    public String getDetallePedido(){return detallePedido;}
    @Override
    public String toString(){
        String detalle = detallePedido == null || detallePedido.trim().isEmpty() ? "Sin detalle" : detallePedido.replace("\n", " ");
        return String.format("[%-6s] Pedido: %-8s | Cliente: %-18s | Bs.%7.2f | %s | %s", idTicket, idPedido, cliente, total, fecha, detalle);
    }
}
