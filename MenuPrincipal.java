public class MenuPrincipal{
    static Usuario sesion = null;
    static boolean tieneRol(String... roles){ //... son vargs es paraa aceptar varios str a la vez ejm("ADMIN", "CAJERO") xlo cual no necesitaos un array
        if(sesion == null){
            return false;
        }
        for(String r : roles){
            if(sesion.getRol().equals(r)){
                return true;
            }
        }
        return false;
    }
    public static void iniciarConSesion(Usuario usuario){
        sesion=usuario;
        String rol=sesion.getRol();
        switch(rol){
            case Usuario.ADMIN:    
                mostrarMenuAdmin();    
                break;
            case Usuario.CAJERO:   
                mostrarMenuCajero();   
                break;
            case Usuario.COCINERO: 
                mostrarMenuCocinero(); 
                break;
            case Usuario.CLIENT:   
                mostrarMenuCliente();  
                break;
            default:
                System.out.println("  Rol desconocido: " + rol);
                Consola.pausar();
                sesion=null;
        }
    }
    private static void mostrarMenuAdmin(){
        boolean ejecutando =true;
        while(ejecutando){
            encabezado();
            System.out.println("  --- PANEL ADMINISTRADOR ---");
            System.out.println("  1. Productos y Menu");
            System.out.println("  2. Pedidos - Punto de Venta");
            System.out.println("  3. Inventario");
            System.out.println("  4. Usuarios y Empleados");
            System.out.println("  5. Proveedores");
            System.out.println("  6. Reportes");
            System.out.println("  0. Cerrar sesion");
            Consola.linea();
            int op=Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    MenuProductos.mostrar();   
                    break;
                case 2: 
                    MenuPedidos.mostrar();     
                    break;
                case 3: 
                    MenuInventario.mostrar();  
                    break;
                case 4: 
                    MenuUsuarios.mostrar();    
                    break;
                case 5: 
                    MenuProveedores.mostrar(); 
                    break;
                case 6: 
                    MenuReportes.mostrar();    
                    break;
                case 0: ejecutando=cerrarSesion(); 
                    break;
                default: 
                    System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void mostrarMenuCajero(){
        boolean ejecutando=true;
        while(ejecutando){
            encabezado();
            System.out.println("  --- PANEL CAJERO ---");
            System.out.println("  1. Ver Productos y Menu");
            System.out.println("  2. Pedidos - Punto de Venta");
            System.out.println("  3. Reportes");
            System.out.println("  0. Cerrar sesion");
            Consola.linea();
            int op=Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    MenuProductos.mostrar(); 
                    break;
                case 2: 
                    MenuPedidos.mostrar();   
                    break;
                case 3: 
                    MenuReportes.mostrar();  
                    break;
                case 0: 
                    ejecutando = cerrarSesion(); 
                    break;
                default: 
                    System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void mostrarMenuCocinero(){
        boolean ejecutando=true;
        while(ejecutando){
            encabezado();
            System.out.println("  --- PANEL COCINERO ---");
            System.out.println("  1. Ver Productos y Menu");
            System.out.println("  2. Pedidos - Cocina");
            System.out.println("  3. Inventario");
            System.out.println("  0. Cerrar sesion");
            Consola.linea();
            int op=Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    MenuProductos.mostrar();  
                    break;
                case 2: 
                    MenuPedidos.mostrar();
                    break;
                case 3: 
                    MenuInventario.mostrar(); 
                    break;
                case 0: 
                    ejecutando = cerrarSesion(); 
                    break;
                default: 
                    System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void mostrarMenuCliente(){
        boolean ejecutando=true;
        while(ejecutando){
            encabezado();
            System.out.println("  --- BIENVENIDO ---");
            System.out.println("  1. Ver nuestro Menu");
            System.out.println("  2. Realizar un Pedido");
            System.out.println("  0. Cerrar sesion");
            Consola.linea();
            int op =Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    MenuProductos.mostrar(); 
                    break;
                case 2: 
                    MenuPedidos.mostrar();   
                    break;
                case 0: 
                    ejecutando = cerrarSesion(); 
                    break;
                default: 
                    System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void encabezado(){
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("       LILS BURGER - SISTEMA DE GESTION");
        System.out.println("  ================================================");
        System.out.println("  >> " + sesion.getNombre() + " " + sesion.getApellido() + " | Rol: " + sesion.getRol());
        System.out.println();
    }
    private static boolean cerrarSesion(){
        if (Consola.confirmar("Cerrar sesion de " + sesion.getNombre() + "?")) {
            System.out.println("  Sesion cerrada.");
            Consola.pausar();
            sesion=null;
            return false; 
        }
        return true; 
    }
    static boolean verificar(String descripcion, String... roles) {
        if(tieneRol(roles)){
            return true;
        }
        System.out.println("  Acceso denegado: requiere rol " + descripcion + ".");
        Consola.pausar();
        return false;
    }
}