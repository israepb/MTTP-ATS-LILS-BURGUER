import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ArchivoUtil{
    public static final String DIR = "";
    static{
        new File(DIR).mkdirs();
    }
    public static List<String> leerLineas(String archivo){
        List<String> lineas = new ArrayList<String>();
        File f = new File(DIR + archivo);
        if(!f.exists()){
            return lineas;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String linea;
            while((linea=br.readLine())!=null){
                linea = linea.trim();
                if(!linea.isEmpty()){
                    lineas.add(linea);
                }
            }
        }catch(IOException e){
            System.out.println("  Error leyendo " + archivo + ": " + e.getMessage());
        }
        return lineas;
    }
    public static void escribirLineas(String archivo, List<String> lineas){
        try(PrintWriter pw = new PrintWriter(new FileWriter(DIR + archivo))){
            for(String l : lineas){
                pw.println(l);
            }
        }catch(IOException e){
            System.out.println("  Error escribiendo " + archivo + ": " + e.getMessage());
        }
    }
    public static void agregarLinea(String archivo, String linea){
        try(PrintWriter pw = new PrintWriter(new FileWriter(DIR + archivo, true))){
            pw.println(linea);
        }catch(IOException e){
            System.out.println("  Error en " + archivo + ": " + e.getMessage());
        }
    }
    public static boolean existe(String archivo){
        File f=new File(DIR + archivo);
        return f.exists() && f.length()>0;
    }
}