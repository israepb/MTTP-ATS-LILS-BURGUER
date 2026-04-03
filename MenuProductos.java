import java.util.List;
public class MenuProductos{
    private static final String[] CATEGORIAS = {"Hamburguesas", "Pizzas", "Bebidas", "Extras", "Combos"};
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("PRODUCTOS Y MENU");
            boolean esAdmin=MenuPrincipal.tieneRol(Usuario.ADMIN);
            System.out.println("  1. Ver menu completo");
            System.out.println("  2. Buscar producto por ID");
            System.out.println("  6. Ver receta de producto");
            if(esAdmin){
                System.out.println("  3. Agregar producto");
                System.out.println("  4. Cambiar precio");
                System.out.println("  5. Activar / Desactivar producto");
                System.out.println("  7. Crear combo / promocion");
                System.out.println("  8. Editar receta de producto");
            }
            System.out.println("  0. Volver");
            Consola.linea();
            int op =Consola.leerEntero("Opcion");
            switch(op){
                case 1: 
                    verMenu(); 
                    break;
                case 2: 
                    buscarProducto(); 
                    break;
                case 3:
                    if(esAdmin){ 
                        agregarProducto(); 
                    }else{ 
                        System.out.println("  Requiere rol ADMIN."); 
                    }
                    break;
                case 4:
                    if(esAdmin){ 
                        cambiarPrecio();
                    }else{ 
                        System.out.println("  Requiere rol ADMIN."); 
                    }
                    break;
                case 5:
                    if(esAdmin){ 
                        cambiarDisponibilidad();
                    }else{ 
                        System.out.println("  Requiere rol ADMIN.");
                    }
                    break;
                case 6: 
                    verReceta(); 
                    break;
                case 7:
                    if(esAdmin){ 
                        crearCombo(); 
                    }else{ 
                        System.out.println("  Requiere rol ADMIN."); 
                    }
                    break;
                case 8:
                    if(esAdmin){ 
                        editarReceta(); 
                    }else{ 
                        System.out.println("  Requiere rol ADMIN."); 
                    }
                    break;
                case 0: 
                    activo=false; 
                    break;
                default: 
                    System.out.println("  Opcion no valida."); 
                    break;
            }
        }
    }
    private static void verMenu(){
        Consola.titulo("MENU LILS BURGER");
        for(String cat : CATEGORIAS){
            List<Producto> lista=ProductoDAO.listarPorCategoria(cat);
            if(!lista.isEmpty()){
                System.out.println("\n  >> " + cat.toUpperCase());
                for(Producto p : lista){ 
                    System.out.println(p); 
                }
            }
        }
        Consola.pausar();
    }
    private static void buscarProducto(){
        Producto p =ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
        if(p == null){
            System.out.println("  Producto no encontrado.");
        }else{
            System.out.println("  ID          : " + p.getIdProducto());
            System.out.println("  Nombre      : " + p.getNombre());
            System.out.println("  Descripcion : " + p.getDescripcion());
            System.out.printf( "  Precio base : Bs. %.2f%n", p.getPrecio());
            if(p.esCombo()){
                System.out.printf("  Descuento   : %.0f%%%n", p.getDescuento());
                System.out.printf("  Precio final: Bs. %.2f%n", p.getPrecioFinal());
            }
            System.out.println("  Categoria   : " + p.getCategoria());
            System.out.println("  Disponible  : " + (p.estaDisponible() ? "Si" : "No"));
            System.out.println("  Prep.       : " + p.getTiempoPreparacion() + " min");
        }
        Consola.pausar();
    }
    private static void agregarProducto(){
        Consola.titulo("AGREGAR PRODUCTO");
        String nombre = Consola.leerTexto("Nombre");
        String desc = Consola.leerTexto("Descripcion");
        double precio = Consola.leerDecimal("Precio (Bs.)");
        System.out.println("  Categorias: Hamburguesas | Pizzas | Bebidas | Extras | Combos");
        String cat = Consola.leerTexto("Categoria");
        int tiempo = Consola.leerEntero("Tiempo de preparacion (min)");
        Producto nuevo = new Producto(ProductoDAO.siguienteId(), nombre, desc, precio, cat, tiempo);
        ProductoDAO.guardar(nuevo);
        System.out.println("  Producto registrado: " + nuevo.getIdProducto());
        Consola.pausar();
    }
    private static void cambiarPrecio(){
        Producto p = ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
        if(p==null){ 
            System.out.println("  Producto no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        double nuevo=Consola.leerDecimal("Nuevo precio (Bs.)");
        p.setPrecio(nuevo);
        ProductoDAO.guardar(p);
        System.out.println("  Precio actualizado a Bs. " + nuevo);
        Consola.pausar();
    }
    private static void cambiarDisponibilidad(){
        Producto p=ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
        if(p==null){ 
            System.out.println("  Producto no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        p.setDisponible(!p.estaDisponible());
        ProductoDAO.guardar(p);
        System.out.println("  " + p.getNombre() + ": " + (p.estaDisponible() ? "Disponible" : "No disponible"));
        Consola.pausar();
    }
    private static void verReceta(){
        Producto p =ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
        if(p==null){ 
            System.out.println("  Producto no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        Consola.titulo("RECETA: " + p.getNombre().toUpperCase());
        String receta = p.getReceta();
        if(receta==null || receta.trim().isEmpty()){
            System.out.println("  Este producto no tiene receta registrada.");
            Consola.pausar(); 
            return;
        }
        if(p.esCombo()){
            System.out.println("  Este COMBO incluye:");
            for(String pid : receta.split(",")){
                Producto inc = ProductoDAO.buscar(pid.trim());
                if(inc!=null){
                    System.out.printf("    -> [%-6s] %-25s Bs.%.2f%n", inc.getIdProducto(), inc.getNombre(), inc.getPrecio());
                }else{
                    System.out.println("    -> " + pid.trim() + " (producto no encontrado)");
                }
            }
            System.out.printf("  Precio final con %.0f%% de descuento: Bs. %.2f%n", p.getDescuento(), p.getPrecioFinal());
        }else{
            System.out.println("  Ingredientes necesarios:");
            System.out.println();
            for(String item : receta.split(";")){
                String[] partes = item.split(":");
                if(partes.length<2){ 
                    continue; 
                }
                Ingrediente ing = InventarioDAO.buscar(partes[0].trim());
                double cant= Double.parseDouble(partes[1].trim());
                if(ing!=null){
                    String alerta = ing.stockBajo() ? "  !! STOCK BAJO" : "";
                    System.out.printf("    %-6s %-20s x %5.2f %-5s  [Stock: %.2f]%s%n", ing.getId(), ing.getNombre(), cant, ing.getUnidad(), ing.getStock(), alerta);
                }else{
                    System.out.println("    " + partes[0].trim() + " x " + cant + "  (ingrediente no encontrado)");
                }
            }
        }
        Consola.pausar();
    }
    private static void crearCombo(){
        Consola.titulo("CREAR COMBO / PROMOCION");
        System.out.println("  Productos disponibles:");
        for(Producto p : ProductoDAO.listarTodos()){
            if(p.estaDisponible() && !p.esCombo()){ 
                System.out.println(p); 
            }
        }
        System.out.println();
        System.out.println("  Ingrese los IDs separados por coma (ej: P001,P019):");
        String ids =Consola.leerTexto("IDs de productos").replaceAll("\\s+", "");
        double precioBase=0;
        for(String pid : ids.split(",")){
            Producto p=ProductoDAO.buscar(pid.trim());
            if(p!=null){ 
                precioBase+= p.getPrecio(); 
            }
        }
        System.out.printf("  Precio base (suma): Bs. %.2f%n", precioBase);
        double descuento = Consola.leerDecimal("Descuento % (ej: 10 para 10%%)");
        System.out.printf("  Precio final del combo: Bs. %.2f%n", precioBase * (1 - descuento / 100.0));
        String nombre = Consola.leerTexto("Nombre del combo");
        String desc = Consola.leerTexto("Descripcion");
        int tiempo = Consola.leerEntero("Tiempo de preparacion (min)");
        Producto combo = new Producto(ProductoDAO.siguienteId(), nombre, desc, precioBase, "Combos", tiempo);
        combo.setEsCombo(true);
        combo.setDescuento(descuento);
        combo.setReceta(ids);
        ProductoDAO.guardar(combo);
        System.out.println("  Combo registrado: " + combo.getIdProducto());
        Consola.pausar();
    }
    private static void editarReceta(){
        Producto p = ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
        if(p==null){ 
            System.out.println("  Producto no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        Consola.titulo("EDITAR RECETA: " + p.getNombre());
        String recetaActual = p.getReceta();
        System.out.println("  Receta actual: " + (recetaActual == null || recetaActual.isEmpty() ? "(sin receta)" : recetaActual));
        System.out.println();
        System.out.println("  Ingredientes disponibles:");
        for(Ingrediente ing : InventarioDAO.listarTodos()){
            System.out.printf("    %-6s %-20s (%s)%n", ing.getId(), ing.getNombre(), ing.getUnidad());
        }
        System.out.println();
        System.out.println("  Formato: ID:cantidad separados por ; ");
        System.out.println("  Ejemplo: I001:0.15;I002:1;I009:0.20");
        String nueva = Consola.leerTexto("Nueva receta");
        p.setReceta(nueva);
        ProductoDAO.guardar(p);
        System.out.println("  Receta actualizada correctamente.");
        Consola.pausar();
    }
}