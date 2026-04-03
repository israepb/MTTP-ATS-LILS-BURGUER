import java.util.ArrayList;
import java.util.List;
public class ProveedorDAO{
    private static final String A = "proveedores.txt";
    public static List<Proveedor> listarTodos(){
        List<Proveedor> lista = new ArrayList<Proveedor>();
        for(String l : ArchivoUtil.leerLineas(A)){
            String[] p = l.split("\\|");
            if(p.length<5){
                continue;
            }
            lista.add(new Proveedor(p[0], p[1], p[2], p[3], p[4]));
        }
        return lista;
    }
    public static Proveedor buscar(String id){
        for(String l : ArchivoUtil.leerLineas(A)){
            String[]p=l.split("\\|");
            if(p[0].equals(id)){
                return new Proveedor(p[0], p[1], p[2], p[3], p[4]);
            }
        }
        return null;
    }
    public static void guardar(Proveedor prov){
        List<String> lineas = ArchivoUtil.leerLineas(A);
        boolean enc = false;
        for(int i=0; i<lineas.size(); i++){
            if(lineas.get(i).split("\\|")[0].equals(prov.getIdProveedor())){
                lineas.set(i, aLinea(prov)); 
                enc = true; 
                break;
            }
        }
        if(!enc){
            lineas.add(aLinea(prov));
        }
        ArchivoUtil.escribirLineas(A, lineas);
    }
    public static void eliminar(String id){
        List<String> lineas=ArchivoUtil.leerLineas(A);
        lineas.removeIf(l -> l.split("\\|")[0].equals(id));
        ArchivoUtil.escribirLineas(A, lineas);
    }
    public static String siguienteId(){
        return "PRV-" + (ArchivoUtil.leerLineas(A).size() + 1);
    }
    private static String aLinea(Proveedor p){
        return p.getIdProveedor() + "|" + p.getNombre() + "|" + p.getTelefono() + "|" + p.getDireccion() + "|" + p.getTipoProducto();
    }
}