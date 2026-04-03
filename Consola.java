import java.util.Scanner;
public class Consola{
    private static Scanner scanner = new Scanner(System.in);
    private Consola(){}
    public static String leerTexto(String etiqueta){
        System.out.print("  "+etiqueta+": ");
        return scanner.nextLine().trim();
    }
    public static int leerEntero(String etiqueta){
        while(true){
            System.out.print("  "+etiqueta+": ");
            try{
                return Integer.parseInt(scanner.nextLine().trim());
            }catch(NumberFormatException e) {
                System.out.println("  Ingrese un numero entero valido.");
            }
        }
    }
    public static double leerDecimal(String etiqueta){
        while(true){
            System.out.print("  "+etiqueta+": ");
            try{
                return Double.parseDouble(scanner.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("  Ingrese un numero valido (ej: 35.50).");
            }
        }
    }
    public static boolean confirmar(String pregunta){
        System.out.print("  "+pregunta+" (s/n): ");
        return scanner.nextLine().trim().toLowerCase().equals("s");
    }
    public static void pausar(){
        System.out.print("\n  Presione ENTER para continuar...");
        scanner.nextLine();
    }
    public static void titulo(String texto){
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("   " + texto);
        System.out.println("  ================================================");
    }
    public static void linea(){
        System.out.println("  ------------------------------------------------");
    }
}