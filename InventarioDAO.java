import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class InventarioDAO{
    private static final String AI = "ingredientes.txt"; //AI=AlmacenIngredientes (li)
    private static final String AV = "inventario.txt";   //AV=Almacen inVentario (lv)
    public static List<Ingrediente> listarTodos(){
        List<Ingrediente> lista = new ArrayList<Ingrediente>();
        Map<String, Double> minimos = leerMinimos();
        for(String l : ArchivoUtil.leerLineas(AI)){
            String[] p = l.split("\\|");
            if(p.length<4){
                continue;
            }
            double min =minimos.containsKey(p[0]) ? minimos.get(p[0]) : 0.0;
            lista.add(new Ingrediente(p[0], p[1], p[2], Double.parseDouble(p[3]), min));
        }
        return lista;
    }
    public static Ingrediente buscar(String id){
        for(Ingrediente ing : listarTodos()){
            if(ing.getId().equals(id)){
                return ing;
            }
        }
        return null;
    }
    public static void guardar(Ingrediente ing){
        List<String> li = ArchivoUtil.leerLineas(AI);
        boolean enc = false;
        for(int i=0; i<li.size(); i++){
            if(li.get(i).split("\\|")[0].equals(ing.getId())){
                li.set(i, ing.getId() + "|" + ing.getNombre() + "|" + ing.getUnidad() + "|" + ing.getStock());
                enc = true;
                break;
            }
        }
        if(!enc){
            li.add(ing.getId() + "|" + ing.getNombre() + "|" + ing.getUnidad() + "|" + ing.getStock());
        }
        ArchivoUtil.escribirLineas(AI, li);
        List<String> lv = ArchivoUtil.leerLineas(AV);
        boolean encV = false;
        for (int i=0; i<lv.size(); i++){
            if(lv.get(i).split("\\|")[0].equals(ing.getId())){
                lv.set(i, ing.getId() + "|" + ing.getMinimo());
                encV=true;
                break;
            }
        }
        if(!encV){
            lv.add(ing.getId() + "|" + ing.getMinimo());
        }
        ArchivoUtil.escribirLineas(AV, lv);
    }
    public static String siguienteId(){
        return "I" + String.format("%03d", ArchivoUtil.leerLineas(AI).size() + 1);
    }
    private static Map<String, Double> leerMinimos(){
        Map<String, Double> map = new HashMap<String, Double>();
        for(String l : ArchivoUtil.leerLineas(AV)){
            String[] p = l.split("\\|");
            if(p.length>=2){
                map.put(p[0], Double.parseDouble(p[1]));
            }
        }
        return map;
    }
}