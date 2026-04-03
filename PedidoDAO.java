import java.util.ArrayList;
import java.util.List;
public class PedidoDAO{
    private static final String AP = "pedidos.txt"; //Almacen Pedido (lp)
    private static final String AD = "detalles.txt"; //Almacen Detalles (ld)
    public static List<Pedido> listarTodos(){
        List<Pedido> lista = new ArrayList<Pedido>();
        for(String l : ArchivoUtil.leerLineas(AP)){
            Pedido p = cabecera(l);
            if(p!=null){
                lista.add(p);
            }
        }
        for(String l : ArchivoUtil.leerLineas(AD)){
            String[] d = l.split("\\|");
            if(d.length<5){
                continue;
            }
            for(Pedido p : lista){
                if(p.getIdPedido().equals(d[1])){
                    p.agregarProducto(d[2], Integer.parseInt(d[3]), Double.parseDouble(d[4]));
                    break;
                }
            }
        }
        return lista;
    }
    public static Pedido buscar(String id){
        for(Pedido p : listarTodos()){
            if(p.getIdPedido().equals(id)){
                return p;
            }
        }
        return null;
    }
    public static void guardar(Pedido pedido){
        List<String> lp = ArchivoUtil.leerLineas(AP);
        boolean enc = false;
        for (int i=0; i<lp.size(); i++){
            if (lp.get(i).split("\\|")[0].equals(pedido.getIdPedido())) {
                lp.set(i, aLinea(pedido)); enc = true; break;
            }
        }
        if(!enc){
            lp.add(aLinea(pedido));
        }
        ArchivoUtil.escribirLineas(AP, lp);
        List<String> ld = ArchivoUtil.leerLineas(AD);
        ld.removeIf(l -> l.split("\\|")[1].equals(pedido.getIdPedido()));
        for(DetallePedido d : pedido.getDetalles()){
            ld.add(d.getIdDetalle() + "|" + pedido.getIdPedido() + "|" + d.getIdProducto() + "|" + d.getCantidad() + "|" + d.getPrecioUnitario());
        }
        ArchivoUtil.escribirLineas(AD, ld);
    }
    public static String siguienteId(){
        int max = 0;
        for(String l : ArchivoUtil.leerLineas(AP)){
            String[] p = l.split("\\|");
            if(p.length > 0 && p[0].startsWith("PED-")){
                try{
                    int n = Integer.parseInt(p[0].substring(4));
                    if(n > max) max = n;
                }catch(NumberFormatException e){}
            }
        }
        return "PED-" +(max + 1);
    }
    private static Pedido cabecera(String l){
        String[] p=l.split("\\|");
        if(p.length<5){
            return null;
        }
        Pedido ped=new Pedido(p[0], p[1], p[2]);
        ped.setEstado(p[3]);
        ped.setFecha(p[4]);
        return ped;
    }
    private static String aLinea(Pedido p){
        return p.getIdPedido() + "|" + p.getIdCliente() + "|" + p.getTipo() + "|" + p.getEstado() + "|" + p.getFecha();
    }
}