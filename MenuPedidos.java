import java.util.List;
public class MenuPedidos{
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("PEDIDOS — PUNTO DE VENTA");
            boolean esAdmin = MenuPrincipal.tieneRol(Usuario.ADMIN);
            boolean esCajero = MenuPrincipal.tieneRol(Usuario.CAJERO);
            boolean esCocinero = MenuPrincipal.tieneRol(Usuario.COCINERO);
            if(!esCocinero || esAdmin){
                System.out.println("  1.  Nuevo pedido");
            }
            System.out.println("  2.  Ver pedido");
            if(!esCocinero || esAdmin){
                System.out.println("  3.  Agregar producto a pedido");
                System.out.println("  4.  Modificar cantidad");
                System.out.println("  5.  Eliminar producto de pedido");
                System.out.println("  6.  Ver total del pedido");
                System.out.println("  7.  Cancelar pedido");
            }
            if(esAdmin || esCajero){
                System.out.println("  8.  Enviar a cocina");
            }
            if(esAdmin || esCocinero){
                System.out.println("  9.  Marcar como listo");
            }
            if(esAdmin || esCajero){
                System.out.println("  10. Registrar pago");
                System.out.println("  11. Generar factura");
            }
            System.out.println("  12. Ver historial de pedidos");
            if(esAdmin || esCajero || esCocinero){
                System.out.println("  13. Ver tickets de cocina");
            }
            if(esAdmin || esCocinero){
                System.out.println("  14. Ver receta del pedido");
            }
            System.out.println("  0.  Volver");
            Consola.linea();
            int op=Consola.leerEntero("Opcion");
            switch(op){
                case 1:
                    if(!esCocinero || esAdmin){
                        nuevoPedido();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 2:
                    verPedido();
                    break;
                case 3:
                    if(!esCocinero || esAdmin){
                        agregarProducto();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 4:
                    if(!esCocinero || esAdmin){
                        modificarCantidad();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 5:
                    if(!esCocinero || esAdmin){
                        eliminarProducto();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 6:
                    if(!esCocinero || esAdmin){
                        verTotal();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 7:
                    if(!esCocinero || esAdmin){
                        cancelarPedido();
                    }else{
                        System.out.println("  Opcion no disponible para su rol.");
                    }
                    break;
                case 8:
                    if(esAdmin || esCajero){
                        enviarCocina();
                    }else{
                        System.out.println("  Requiere rol CAJERO o ADMIN.");
                    }
                    break;
                case 9:
                    if(esAdmin || esCocinero){
                        marcarListo();
                    }else{
                        System.out.println("  Requiere rol COCINERO o ADMIN.");
                    }
                    break;
                case 10:
                    if(esAdmin || esCajero){
                        registrarPago();
                    }else{
                        System.out.println("  Requiere rol CAJERO o ADMIN.");
                    }
                    break;
                case 11:
                    if(esAdmin || esCajero){
                        generarFactura();
                    }else{
                        System.out.println("  Requiere rol CAJERO o ADMIN.");
                    }
                    break;
                case 12:
                    verHistorial();
                    break;
                case 13:
                    if(esAdmin || esCajero || esCocinero){
                        verTickets();
                    }else{
                        System.out.println("  Requiere sesion de empleado.");
                    }
                    break;
                case 14:
                    if(esAdmin || esCocinero){
                        verRecetaPedido();
                    }else{
                        System.out.println("  Requiere rol COCINERO o ADMIN.");
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
    private static void nuevoPedido(){
        Consola.titulo("NUEVO PEDIDO");
        String cliente = Consola.leerTexto("Nombre o ID del cliente");
        System.out.println("  Tipo:  1. Local   2. Para llevar");
        String tipo = Consola.leerEntero("Opcion") == 2 ? Pedido.PARA_LLEVAR : Pedido.LOCAL;
        Pedido p = new Pedido(PedidoDAO.siguienteId(), cliente, tipo);
        PedidoDAO.guardar(p);
        System.out.println("  Pedido creado: " + p.getIdPedido());
        Consola.pausar();
    }
    private static void verPedido(){
        Pedido p =PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){
            System.out.println("  Pedido no encontrado.");
        }else{
            p.mostrarDetalle();
        }
        Consola.pausar();
    }
    private static void agregarProducto(){
        Pedido p =PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p ==null){
            System.out.println("  Pedido no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        if( p.getEstado().equals(Pedido.PENDIENTE)){
            List<Producto> todos = ProductoDAO.listarTodos();
            System.out.println("  --- Productos disponibles ---");
            for(Producto prod : todos){
                if(prod.estaDisponible()){ 
                    System.out.println(prod);
                }
            }
            System.out.println();
            Producto prod =ProductoDAO.buscar(Consola.leerTexto("ID del producto"));
            if(prod ==null){
                System.out.println("  Producto no encontrado."); 
                Consola.pausar(); 
                return; 
            }
            int cant =Consola.leerEntero("Cantidad");
            if (cant <=0) {
                System.out.println("  La cantidad debe ser mayor a cero.");
                Consola.pausar();
                return;
            }
            p.agregarProducto(prod.getIdProducto(),cant,prod.getPrecioFinal());
            PedidoDAO.guardar(p);
            System.out.printf("  Agregado: %dx %s  (Bs. %.2f c/u)%n", cant, prod.getNombre(), prod.getPrecioFinal());
        }else{
            System.out.println("el pedido esta "+p.getEstado()+" por lo tanto ya no puede ser modificado.");
        }
        Consola.pausar();
    }
    private static void modificarCantidad(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p ==null){
            System.out.println("  Pedido no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        if( p.getEstado().equals(Pedido.PENDIENTE)){
            p.mostrarDetalle();
            String idProd= Consola.leerTexto("ID del producto a modificar");
            boolean existeEnPedido = false;
            for(DetallePedido d : p.getDetalles()){
                if(d.getIdProducto().equals(idProd)){
                    existeEnPedido = true;
                    break;
                }
            }
            if(!existeEnPedido){
                System.out.println("  Error: El producto '" + idProd + "' no está en este pedido.");
                Consola.pausar();
                return; 
            }
            int nuevaCant= Consola.leerEntero("Nueva cantidad");
            if (nuevaCant <=0) {
                System.out.println("  La cantidad debe ser mayor a cero.");
                Consola.pausar();
                return;
            }
            for(DetallePedido d : p.getDetalles()){
                if(d.getIdProducto().equals(idProd)){
                    d.setCantidad(nuevaCant);
                    PedidoDAO.guardar(p);
                    System.out.println("  Cantidad actualizada.");
                    Consola.pausar(); 
                    return;
                }
            }
            System.out.println("  Error: El producto '" + idProd + "' no está en este pedido.");
            Consola.pausar();
        }else{
            System.out.println("el pedido esta "+p.getEstado()+" por lo tanto ya no puede ser modificado.");
        }
        Consola.pausar();
    }
    private static void eliminarProducto(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){
            System.out.println("  Pedido no encontrado.");
            Consola.pausar();
            return;
        }
        if( p.getEstado().equals(Pedido.PENDIENTE)){
            p.mostrarDetalle();
            p.eliminarProducto(Consola.leerTexto("ID del producto a eliminar"));
            PedidoDAO.guardar(p);
        }else{
            System.out.println("el pedido esta "+p.getEstado()+" por lo tanto ya no puede ser modificado.");
        }
        Consola.pausar();
    }
    private static void verTotal(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p ==null){
            System.out.println("  Pedido no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        System.out.printf("  Total del pedido %s: Bs. %.2f%n",p.getIdPedido(),p.calcularTotal());
        Consola.pausar();
    }
    private static void cancelarPedido(){
        String id=Consola.leerTexto("ID del pedido");
        Pedido p= PedidoDAO.buscar(id);
        if(p==null){
            System.out.println("  Pedido no encontrado.");
            Consola.pausar();
            return;
        }
        if(p.getEstado().equals(Pedido.ENTREGADO)){
            System.out.println("  No se puede cancelar un pedido que ya ha sido pagado y entregado.");
            Consola.pausar(); 
            return;
        }
        if(Consola.confirmar("Cancelar "+id+"?")){
            p.setEstado(Pedido.CANCELADO);
            PedidoDAO.guardar(p);
            System.out.println("  Pedido cancelado.");
        }
        Consola.pausar();
    }
    private static void enviarCocina(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p ==null){
            System.out.println("  Pedido no encontrado."); 
            Consola.pausar(); 
            return; 
        }
        if(p.getEstado().equals(Pedido.PENDIENTE)){
            p.setEstado(Pedido.EN_PREPARACION);
            PedidoDAO.guardar(p);
            System.out.println("  Pedido " +p.getIdPedido()+" enviado a cocina.");
        }else{
            System.out.println("el pedido esta "+p.getEstado()+", necesita estar PENDIENTE para marcar esta opción.");
        }
        Consola.pausar();
    }
    private static void marcarListo(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){ 
                System.out.println("  Pedido no encontrado."); 
                Consola.pausar(); 
                return;
            }
        if(p.getEstado().equals(Pedido.EN_PREPARACION)){
            p.setEstado(Pedido.LISTO);
            PedidoDAO.guardar(p);
            System.out.println("  Pedido " + p.getIdPedido() + " listo para entregar.");
        }else{
            System.out.println("el pedido esta "+p.getEstado()+", necesita estar EN_PREPARACION para marcar esta opción.");
        }
        Consola.pausar();
    }
    private static void registrarPago(){
        Pedido p= PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){ 
                System.out.println("  Pedido no encontrado."); 
                Consola.pausar(); 
                return;
            }
        if(p.getEstado().equals(Pedido.ENTREGADO)){
            System.out.println("  Este pedido ya fue pagado y entregado.");
            Consola.pausar();
            return;
        }else if(p.getEstado().equals(Pedido.CANCELADO)){
            System.out.println("  No se puede cobrar un pedido cancelado.");
            Consola.pausar();
            return;
        }else if(p.getEstado().equals(Pedido.LISTO)){
            System.out.printf("  Total a cobrar: Bs. %.2f | Metodo: Efectivo%n", p.calcularTotal());
            if(!Consola.confirmar("Confirmar pago?")){ 
                Consola.pausar(); 
                return; 
            }
            Pago pago = new Pago(PagoDAO.siguienteIdPago(),p.calcularTotal(),p.getIdPedido());
            PagoDAO.guardarPago(pago);
            pago.imprimirComprobante();
            Ticket ticket = new Ticket(PagoDAO.siguienteIdTicket(),p.getIdPedido(),p.calcularTotal());
            PagoDAO.guardarTicket(ticket);
            ticket.imprimir();
            p.setEstado(Pedido.ENTREGADO);
            PedidoDAO.guardar(p);
        }else{
            System.out.println("el pedido esta "+p.getEstado()+", necesita estar LISTO para marcar esta opción.");
        }
        Consola.pausar();
    }
    private static void generarFactura(){
        Pedido p=PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){
            System.out.println("  Pedido no encontrado.");
            Consola.pausar();
            return;
        }
        if(p.getEstado().equals(Pedido.ENTREGADO)){
            String nit=Consola.leerTexto("NIT del cliente");
            String rs=Consola.leerTexto("Razon social");
            Factura f=new Factura(PagoDAO.siguienteIdFactura(),nit,rs,p.calcularTotal(),p.getIdPedido());
            PagoDAO.guardarFactura(f);
            f.imprimir();
        }
        else{
            System.out.println("el pedido esta "+p.getEstado()+", necesita estar ENTREGADO para marcar esta opción.");
        }
        Consola.pausar();
    }
    private static void verHistorial(){
        Consola.titulo("HISTORIAL DE PEDIDOS");
        List<Pedido> lista = PedidoDAO.listarTodos();
        if(lista.isEmpty()){
            System.out.println("  Sin pedidos registrados.");
        }else{
            for(Pedido p : lista){
                System.out.println(p);
            }
        }
        Consola.pausar();
    }
    private static void verTickets(){
       Consola.titulo("TICKETS DE COCINA");
       List<Pedido> lista = PedidoDAO.listarTodos();
       int pedidosEncontrados = 0;
       if(lista.isEmpty()){
        System.out.println("  No hay historial de pedidos en el sistema.");
        } else {
            for(Pedido p : lista){
                if(p.getEstado().equals(Pedido.EN_PREPARACION)){
                    System.out.println("  [TICKET] " + p); 
                    pedidosEncontrados++;
                }
            }
            if(pedidosEncontrados == 0){
                System.out.println("  No hay pedidos en preparación actualmente.");
            }
        }
        Consola.pausar();
    }
    private static void verRecetaPedido(){
        Pedido p= PedidoDAO.buscar(Consola.leerTexto("ID del pedido"));
        if(p==null){
            System.out.println("  Pedido no encontrado.");
            Consola.pausar();
            return;
        }
        Consola.titulo("RECETA DEL PEDIDO " + p.getIdPedido());
        System.out.println("  Cliente : " + p.getIdCliente());
        System.out.println("  Estado  : " + p.getEstado());
        System.out.println();
        boolean hayReceta = false;
        for(DetallePedido det : p.getDetalles()){
            Producto prod = ProductoDAO.buscar(det.getIdProducto());
            if(prod==null){
                continue;
            }
            System.out.println("  >> "+det.getCantidad()+"x "+prod.getNombre());
            String receta = prod.getReceta();
            if(receta==null || receta.trim().isEmpty()){
                System.out.println("     (sin receta registrada)");
                continue;
            }
            hayReceta = true;
            if(prod.esCombo()){
                for(String pid : receta.split(",")){
                    Producto inc = ProductoDAO.buscar(pid.trim());
                    System.out.println("     Incluye: " +(inc != null ? inc.getNombre() : pid.trim()+" (no encontrado)"));
                }
            }else{
                for(String item : receta.split(";")){
                    String[] partes=item.split(":");
                    if(partes.length<2){
                        continue;
                    }
                    Ingrediente ing=InventarioDAO.buscar(partes[0].trim());
                    double cantNecesaria=Double.parseDouble(partes[1].trim()) * det.getCantidad();
                    if(ing!=null){
                        String alerta =ing.stockBajo() ? "  !! STOCK BAJO" : "";
                        System.out.printf("     %-20s x %5.2f %-5s  [Stock: %.2f]%s%n",ing.getNombre(),cantNecesaria,ing.getUnidad(),ing.getStock(),alerta);
                    }else{
                        System.out.println("     " + partes[0].trim() + " x " + cantNecesaria + " (ingrediente no encontrado)");
                    }
                }
            }
            System.out.println();
        }
        if(!hayReceta){
            System.out.println("  Ninguno de los productos tiene receta registrada.");
        }
        Consola.pausar();
    }
}