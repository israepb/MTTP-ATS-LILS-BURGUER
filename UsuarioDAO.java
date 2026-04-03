import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO{
    private static final String A = "usuarios.txt";
    public static List<Usuario> listarTodos(){
        List<Usuario> lista = new ArrayList<Usuario>();
        for(String l : ArchivoUtil.leerLineas(A)){
            Usuario u=desdeLinea(l);
            if(u!=null){
                lista.add(u);
            }
        }
        return lista;
    }
    public static Usuario buscar(String id){
        for(String l : ArchivoUtil.leerLineas(A)){
            String[] p=l.split("\\|");
            if(p[0].equals(id)){
                return desdeLinea(l);
            }
        }
        return null;
    }
    public static Usuario buscarPorNombreUsuario(String nombreUsuario){
        for(Usuario u : listarTodos()){
            if(u.getNombreUsuario().equals(nombreUsuario)){
                return u;
            }
        }
        return null;
    }
    public static Usuario validarLogin(String nombreUsuario, String contrasena){
        for(Usuario u : listarTodos()){
            if(u.getNombreUsuario().equals(nombreUsuario) && u.validarContrasena(contrasena) && u.isActivo()){
                return u;
            }
        }
        return null;
    }
    public static void guardar(Usuario u){
        List<String> lineas=ArchivoUtil.leerLineas(A);
        boolean enc=false;
        for(int i=0; i<lineas.size(); i++){
            if(lineas.get(i).split("\\|")[0].equals(u.getIdUsuario())){
                lineas.set(i, aLinea(u)); enc = true; break;
            }
        }
        if(!enc){
            lineas.add(aLinea(u));
        }
        ArchivoUtil.escribirLineas(A, lineas);
    }
    public static String siguienteId(){
        int max = 0;
        for(String l : ArchivoUtil.leerLineas(A)){
            String[] p=l.split("\\|");
            if(p.length > 0 && p[0].startsWith("U")){
                try{
                    int n=Integer.parseInt(p[0].substring(1));
                    if(n>max){
                        max = n;
                    }
                }catch(NumberFormatException e){}
            }
        }
        return "U" + String.format("%03d", max + 1);
    }
    public static int contarPorRol(String rol){
        int c=0;
        for(String l : ArchivoUtil.leerLineas(A)){
            String[] p=l.split("\\|");
            if(p.length>=7 && p[6].equals(rol)){
                c++;
            }
        }
        return c;
    }
    private static Usuario desdeLinea(String l){
        String[] p=l.split("\\|");
        if(p.length<8){
            return null;
        }
        Usuario u=new Usuario(p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
        u.setActivo(Boolean.parseBoolean(p[7]));
        return u;
    }
    private static String aLinea(Usuario u){
        return u.getIdUsuario() + "|" + u.getNombre() + "|" + u.getApellido() + "|" + u.getTelefono() + "|" + u.getNombreUsuario() + "|" + 
               u.getContrasena() + "|" + u.getRol() + "|" + u.isActivo();
    }
}