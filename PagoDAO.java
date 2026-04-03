import java.util.ArrayList;
import java.util.List;
public class PagoDAO{
    private static final String AP = "pagos.txt"; //AP=AlmacenPagos
    private static final String AF = "facturas.txt"; //AF=AlmacenFacturas
    private static final String AT = "tickets.txt"; //AT=AlmacenTickes
    public static List<Pago> listarPagos(){
        List<Pago> lista = new ArrayList<Pago>();
        for(String l : ArchivoUtil.leerLineas(AP)){
            String[]p=l.split("\\|");
            if(p.length<4){
                continue;
            }
            Pago pago=new Pago(p[0], Double.parseDouble(p[1]), p[2]);
            pago.setFecha(p[3]);
            lista.add(pago);
        }
        return lista;
    }
    public static void guardarPago(Pago pago){
        ArchivoUtil.agregarLinea(AP, pago.getIdPago() + "|" + pago.getMonto() + "|" + pago.getIdPedido() + "|" + pago.getFecha());
    }
    public static double totalVentas(){
        double total = 0;
        for(Pago p : listarPagos()){
            total+=p.getMonto();
        }
        return total;
    }
    public static String siguienteIdPago(){
        return "PAG-" + (ArchivoUtil.leerLineas(AP).size() + 1);
    }
    public static List<Factura> listarFacturas(){
        List<Factura> lista=new ArrayList<Factura>();
        for(String l : ArchivoUtil.leerLineas(AF)){
            String[] p=l.split("\\|");
            if(p.length<5){
                continue;
            }
            lista.add(new Factura(p[0], p[1], p[2], Double.parseDouble(p[3]), p[4]));
        }
        return lista;
    }
    public static void guardarFactura(Factura f){
        ArchivoUtil.agregarLinea(AF, f.getIdFactura() + "|" + f.getNit() + "|" + f.getRazonSocial() + "|" + f.getTotal() + "|" + f.getIdPedido() + "|" 
        + f.getFecha());
    }
    public static String siguienteIdFactura(){
        return "FAC-" + (ArchivoUtil.leerLineas(AF).size() + 1);
    }
    public static List<Ticket> listarTickets(){
        List<Ticket> lista=new ArrayList<Ticket>();
        for(String l : ArchivoUtil.leerLineas(AT)){
            String[] p=l.split("\\|");
            if(p.length<3){
                continue;
            }
            lista.add(new Ticket(p[0], p[1], Double.parseDouble(p[2])));
        }
        return lista;
    }
    public static void guardarTicket(Ticket t){
        ArchivoUtil.agregarLinea(AT, t.getIdTicket() + "|" + t.getIdPedido() + "|" + t.getTotal() + "|" + t.getFecha());
    }
    public static String siguienteIdTicket(){
        return "TKT-" + (ArchivoUtil.leerLineas(AT).size() + 1);
    }
}