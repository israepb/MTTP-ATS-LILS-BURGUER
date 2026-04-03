import java.util.ArrayList;
import java.util.List;
public class ProductoDAO{
    private static final String A = "productos.txt";
    public static List<Producto> listarTodos(){
        List<Producto> lista = new ArrayList<Producto>();
        for(String l : ArchivoUtil.leerLineas(A)){
            Producto p = desdeLinea(l);
            if(p != null){ 
                lista.add(p);
            }
        }
        return lista;
    }
    public static List<Producto> listarPorCategoria(String categoria){
        List<Producto> lista = new ArrayList<Producto>();
        for(Producto p : listarTodos()){
            if(p.getCategoria().equals(categoria) && p.estaDisponible()){
                lista.add(p);
            }
        }
        return lista;
    }
    public static Producto buscar(String id){
        for(String l : ArchivoUtil.leerLineas(A)){
            String[] p = l.split("\\|", 10);
            if(p.length>0 && p[0].equals(id)){ 
                return desdeLinea(l); 
            }
        }
        return null;
    }
    public static void guardar(Producto prod){
        List<String> lineas = ArchivoUtil.leerLineas(A);
        boolean enc = false;
        for(int i=0; i<lineas.size(); i++){
            if(lineas.get(i).split("\\|", 2)[0].equals(prod.getIdProducto())){
                lineas.set(i, aLinea(prod)); 
                enc = true; 
                break;
            }
        }
        if(!enc){ 
            lineas.add(aLinea(prod)); 
        }
        ArchivoUtil.escribirLineas(A, lineas);
    }
    public static void eliminar(String id){
        List<String> lineas = ArchivoUtil.leerLineas(A);
        lineas.removeIf(l -> l.split("\\|", 2)[0].equals(id));
        ArchivoUtil.escribirLineas(A, lineas);
    }
    public static String siguienteId(){
        return "P" + String.format("%03d", ArchivoUtil.leerLineas(A).size() + 1);
    }
    private static Producto desdeLinea(String l){
        String[] p = l.split("\\|", 10);
        if(p.length < 7){ 
            return null; 
        }
        Producto prod = new Producto(p[0], p[1], p[2], Double.parseDouble(p[3]), p[4], Integer.parseInt(p[5]));
        if(!Boolean.parseBoolean(p[6])){ 
            prod.setDisponible(false); 
        }
        if(p.length>=8){ 
            prod.setEsCombo(Boolean.parseBoolean(p[7]));
        }
        if(p.length>=9){
            prod.setDescuento(Double.parseDouble(p[8])); 
        }
        if(p.length>=10){ 
            prod.setReceta(p[9]); 
        }
        return prod;
    }
    private static String aLinea(Producto p){
        return p.getIdProducto() + "|" + p.getNombre() + "|" + p.getDescripcion() + "|" +  p.getPrecio() + "|" + p.getCategoria()   + "|" + 
               p.getTiempoPreparacion() + "|" + p.estaDisponible() + "|" + p.esCombo() + "|" + p.getDescuento() + "|" + p.getReceta();
    }
}
