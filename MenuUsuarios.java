import java.util.List;
public class MenuUsuarios{
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("USUARIOS Y EMPLEADOS");
            System.out.println("  1. Ver todos los empleados");
            System.out.println("  2. Agregar empleado");
            System.out.println("  3. Desactivar usuario");
            System.out.println("  0. Volver");
            Consola.linea();
            int op =Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    verEmpleados(); 
                    break;
                case 2: 
                    agregarEmpleado(); 
                    break;
                case 3: 
                    desactivarUsuario(); 
                    break;
                case 0: 
                    activo=false; 
                    break;
                default: System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void verEmpleados(){
        Consola.titulo("EMPLEADOS");
        List<Usuario> lista = UsuarioDAO.listarTodos();
        if(lista.isEmpty()){
            System.out.println("  Sin usuarios registrados.");
        }else{
            for(Usuario u : lista){ 
                System.out.println("  "+u); 
            }
        }
        Consola.pausar();
    }
    private static void agregarEmpleado(){
        Consola.titulo("AGREGAR EMPLEADO");
        System.out.println("  Ingrese los datos del nuevo empleado:");
        String nombre=Consola.leerTexto("Nombre");
        String apellido=Consola.leerTexto("Apellido");
        String telefono=Consola.leerTexto("Telefono");
        String usuario=Consola.leerTexto("Nombre de usuario");
        if(UsuarioDAO.buscarPorNombreUsuario(usuario)!=null){
            System.out.println("  Ese nombre de usuario ya existe.");
            Consola.pausar();
            return;
        }
        String contra = Consola.leerTexto("Contrasena");
        String rol;
        while(true){
            System.out.println();
            System.out.println("  Roles disponibles:");
            System.out.println("    1. ADMIN    - Acceso total al sistema");
            System.out.println("    2. CAJERO   - Pedidos, ventas y reportes");
            System.out.println("    3. COCINERO - Inventario y productos");
            int opRol = Consola.leerEntero("Seleccione rol (1-3)");
            if(opRol==1){ 
                rol=Usuario.ADMIN;   
                break;
            }
            if(opRol==2){ 
                rol=Usuario.CAJERO;   
                break; 
            }
            if(opRol==3){ 
                rol=Usuario.COCINERO; 
                break;
            }
            System.out.println("  Opcion no valida. Ingrese 1, 2 o 3.");
        }
        String id =UsuarioDAO.siguienteId();
        Usuario u =new Usuario(id,nombre,apellido,telefono,usuario,contra,rol);
        UsuarioDAO.guardar(u);
        System.out.println("  Empleado registrado correctamente: " +id + " | " +nombre + " | " +rol);
        Consola.pausar();
    }
    private static void desactivarUsuario(){
        String id=Consola.leerTexto("ID del usuario");
        Usuario u=UsuarioDAO.buscar(id);
        if(u==null){ 
            System.out.println("  Usuario no encontrado."); 
            Consola.pausar(); 
            return;
        }
        if(Consola.confirmar("Desactivar a " + u.getNombre() + "?")){
            u.setActivo(false);
            UsuarioDAO.guardar(u);
            System.out.println("  Usuario desactivado.");
        }
        Consola.pausar();
    }
}