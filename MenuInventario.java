import java.util.List;
public class MenuInventario{
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("INVENTARIO");
            System.out.println("  1. Ver inventario completo");
            System.out.println("  2. Registrar entrada de stock");
            System.out.println("  3. Registrar salida de stock");
            System.out.println("  4. Agregar ingrediente");
            System.out.println("  5. Ver alertas de stock bajo");
            System.out.println("  0. Volver");
            Consola.linea();
            int op = Consola.leerEntero("Opcion");
            if(op ==1){verInventario();}
            else if(op ==2){registrarEntrada();}
            else if(op ==3){registrarSalida();}
            else if(op ==4){agregarIngrediente();}
            else if(op ==5){verAlertas();}
            else if(op ==0){activo = false;}
            else{System.out.println("  Opcion no valida.");}
        }
    }
    private static void verInventario(){
        Consola.titulo("INVENTARIO COMPLETO");
        List<Ingrediente> lista = InventarioDAO.listarTodos();
        if(lista.isEmpty()){
            System.out.println("  Sin ingredientes registrados.");
        }else{
            for (Ingrediente i : lista){
                System.out.println("  " +i);
            }
        }
        Consola.pausar();
    }
    private static void registrarEntrada(){
        Ingrediente ing =InventarioDAO.buscar(Consola.leerTexto("ID del ingrediente"));
        if(ing==null){
            System.out.println("  Ingrediente no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        double cant = Consola.leerDecimal("Cantidad a ingresar");
        ing.setStock(ing.getStock()+cant);
        InventarioDAO.guardar(ing);
        System.out.printf("  Stock actualizado: %.2f %s%n",ing.getStock(),ing.getUnidad());
        Consola.pausar();
    }
    private static void registrarSalida(){
        Ingrediente ing=InventarioDAO.buscar(Consola.leerTexto("ID del ingrediente"));
        if(ing==null){ 
            System.out.println("  Ingrediente no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        double cant=Consola.leerDecimal("Cantidad a retirar");
        if(cant>ing.getStock()){ 
            System.out.println("  Stock insuficiente."); 
            Consola.pausar(); 
            return; 
        }
        ing.setStock(ing.getStock()-cant);
        InventarioDAO.guardar(ing);
        System.out.printf("  Stock actualizado: %.2f %s%n", ing.getStock(),ing.getUnidad());
        if(ing.stockBajo()){
            System.out.println("  !! ALERTA stock bajo.");
        }
        Consola.pausar();
    }
    private static void agregarIngrediente(){
        Consola.titulo("AGREGAR INGREDIENTE");
        String nombre =Consola.leerTexto("Nombre");
        String unidad =Consola.leerTexto("Unidad (kg, lt, unid...)");
        double stock =Consola.leerDecimal("Stock inicial");
        double minimo =Consola.leerDecimal("Stock minimo");
        Ingrediente ing =new Ingrediente(InventarioDAO.siguienteId(), nombre, unidad, stock, minimo);
        InventarioDAO.guardar(ing);
        System.out.println("  Ingrediente registrado: " + ing.getId());
        Consola.pausar();
    }
    private static void verAlertas(){
        Consola.titulo("ALERTAS DE STOCK BAJO");
        int alertas=0;
        for(Ingrediente ing : InventarioDAO.listarTodos()){
            if(ing.stockBajo()){ 
                System.out.println("  " + ing); alertas++; 
            }
        }
        if(alertas==0){
            System.out.println("  Todo el stock esta bien.");
        }
        Consola.pausar();
    }
}