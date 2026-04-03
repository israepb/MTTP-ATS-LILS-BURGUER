public class Main{
    public static void main(String[] args){
        System.out.println("  Iniciando sistema Lils Burger...");
        if(!ArchivoUtil.existe("usuarios.txt")){
            System.out.println("  Primera ejecucion: cargando datos iniciales...");
            DatosDemostracion.cargar();
        }else{
            System.out.println("  Datos cargados desde archivos.");
        }
        MenuLogin.mostrar();
    }
}