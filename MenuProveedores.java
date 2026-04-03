import java.util.List;
public class MenuProveedores{
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("PROVEEDORES");
            System.out.println("  1. Ver proveedores");
            System.out.println("  2. Agregar proveedor");
            System.out.println("  3. Modificar proveedor");
            System.out.println("  4. Eliminar proveedor");
            System.out.println("  0. Volver");
            Consola.linea();
            int op = Consola.leerEntero("Opcion");
            if(op==1){verProveedores();}
            else if(op==2){agregarProveedor();}
            else if(op==3){modificarProveedor();}
            else if(op==4){eliminarProveedor();}
            else if(op==0){activo=false;}
            else{System.out.println("  Opcion no valida.");}
        }
    }
    private static void verProveedores(){
        Consola.titulo("LISTA DE PROVEEDORES");
        List<Proveedor> lista=ProveedorDAO.listarTodos();
        if(lista.isEmpty()){
            System.out.println("  Sin proveedores registrados.");
        }else{
            for(Proveedor p : lista){
                System.out.println("  " + p);
            }
        }
        Consola.pausar();
    }
    private static void agregarProveedor(){
        Consola.titulo("AGREGAR PROVEEDOR");
        String nombre=Consola.leerTexto("Nombre");
        String telefono=Consola.leerTexto("Telefono");
        String dir=Consola.leerTexto("Direccion");
        String tipo=Consola.leerTexto("Tipo de producto");
        Proveedor p=new Proveedor(ProveedorDAO.siguienteId(), nombre, telefono, dir, tipo);
        ProveedorDAO.guardar(p);
        System.out.println("  Proveedor registrado: " + p.getIdProveedor());
        Consola.pausar();
    }
    private static void modificarProveedor(){
        String id=Consola.leerTexto("ID del proveedor");
        Proveedor prov=ProveedorDAO.buscar(id);
        if(prov==null){ 
            System.out.println("  Proveedor no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        prov.setNombre(Consola.leerTexto("Nuevo nombre"));
        prov.setTelefono(Consola.leerTexto("Nuevo telefono"));
        prov.setDireccion(Consola.leerTexto("Nueva direccion"));
        prov.setTipoProducto(Consola.leerTexto("Nuevo tipo de producto"));
        ProveedorDAO.guardar(prov);
        System.out.println("  Proveedor actualizado.");
        Consola.pausar();
    }
    private static void eliminarProveedor(){
        String id=Consola.leerTexto("ID del proveedor");
        Proveedor prov=ProveedorDAO.buscar(id);
        if(prov==null){ 
            System.out.println("  Proveedor no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        if(Consola.confirmar("Eliminar a " + prov.getNombre() + "?")){
            ProveedorDAO.eliminar(id);
            System.out.println("  Proveedor eliminado.");
        }
        Consola.pausar();
    }
}