public class MenuLogin{
    public static void mostrar(){
        boolean corriendo = true;
        while(corriendo){
            System.out.println();
            System.out.println("  ================================================");
            System.out.println("       LILS BURGER - SISTEMA DE GESTION");
            System.out.println("       Av. Pando y Av. Portales | 17:00-23:30");
            System.out.println("  ================================================");
            System.out.println();
            System.out.println("  1. Iniciar sesion");
            System.out.println("  2. Registrarse");
            System.out.println("  3. Ingresar como Invitado");
            System.out.println("  0. Salir");
            Consola.linea();
            int op = Consola.leerEntero("Opcion");
            switch(op){
                case 1:
                    Usuario u=pedirLogin();
                    if(u!=null){
                        MenuPrincipal.iniciarConSesion(u);
                    }
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    ingresarComoInvitado();
                    break;
                case 0:
                    System.out.println("\n  Hasta pronto.");
                    corriendo = false;
                    break;
                default:
                    System.out.println("  Opcion no valida.");
                    break;
            }
        }
    }
    private static void ingresarComoInvitado(){
        System.out.println();
        System.out.println("  Entrando como Invitado...");
        System.out.println("  Podras ver el menu y realizar pedidos.");
        Consola.pausar();
        Usuario invitado = new Usuario(
            "INVITADO", "Invitado", "", "", "invitado", "", Usuario.CLIENT
        );
        MenuPrincipal.iniciarConSesion(invitado);
    }
    static Usuario pedirLogin(){
        Consola.titulo("INICIAR SESION");
        String usuario = Consola.leerTexto("Usuario");
        String contrasena = Consola.leerTexto("Contrasena");
        Usuario u = UsuarioDAO.validarLogin(usuario, contrasena);
        if(u==null){
            System.out.println("  Usuario o contrasena incorrectos.");
            Consola.pausar();
            return null;
        }
        System.out.println("  Bienvenido, " + u.getNombre() + " | Rol: " + u.getRol());
        Consola.pausar();
        return u;
    }
    private static void registrarCliente(){
        Consola.titulo("REGISTRO DE CLIENTE");
        System.out.println("  Datos para crear una cuenta:");
        String nombre = Consola.leerTexto("Nombre");
        String apellido = Consola.leerTexto("Apellido");
        String telefono = Consola.leerTexto("Telefono");
        String usuario = Consola.leerTexto("Nombre de usuario");
        if(UsuarioDAO.buscarPorNombreUsuario(usuario) != null){
            System.out.println("  Ese nombre de usuario ya existe");
            Consola.pausar();
            return;
        }
        String contra = Consola.leerTexto("Contrasena");
        String id = UsuarioDAO.siguienteId();
        Usuario nuevo = new Usuario(id, nombre, apellido, telefono, usuario, contra, Usuario.CLIENT);
        UsuarioDAO.guardar(nuevo);
        System.out.println("  Cuenta creada exitosamente! Bienvenido, " + nombre + ".");
        Consola.pausar();
        MenuPrincipal.iniciarConSesion(nuevo);
    }
}
