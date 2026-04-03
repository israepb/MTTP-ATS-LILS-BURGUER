import java.util.List;
public class MenuReportes{
    public static void mostrar(){
        boolean activo = true;
        while(activo){
            Consola.titulo("REPORTES");
            System.out.println("  1. Total de ventas");
            System.out.println("  2. Ver todos los pagos");
            System.out.println("  3. Ver facturas emitidas");
            System.out.println("  4. Resumen de pedidos por estado");
            System.out.println("  0. Volver");
            Consola.linea();
            int op = Consola.leerEntero("Opcion");
            if(op==1){totalVentas();}
            else if(op==2){verPagos();}
            else if(op==3){verFacturas();}
            else if(op==4){resumenPedidos();}
            else if(op==0){activo = false;}
            else{System.out.println("  Opcion no valida.");}
        }
    }
    private static void totalVentas(){
        Consola.titulo("TOTAL DE VENTAS");
        List<Pago> pagos = PagoDAO.listarPagos();
        System.out.println("  Pagos registrados : "+pagos.size());
        System.out.printf("  Total acumulado   : Bs. %.2f%n",PagoDAO.totalVentas());
        Consola.pausar();
    }
    private static void verPagos(){
        Consola.titulo("PAGOS REGISTRADOS");
        List<Pago> lista =PagoDAO.listarPagos();
        if(lista.isEmpty()){
            System.out.println("  Sin pagos registrados.");
        }else{
            for(Pago p : lista){
                System.out.println("  "+p);
            }
        }
        Consola.pausar();
    }
    private static void verFacturas(){
        Consola.titulo("FACTURAS EMITIDAS");
        List<Factura> lista=PagoDAO.listarFacturas();
        if(lista.isEmpty()){
            System.out.println("  Sin facturas emitidas.");
        }else{
            for(Factura f : lista){
                System.out.println("  "+f);
            }
        }
        Consola.pausar();
    }
    private static void resumenPedidos(){
        Consola.titulo("RESUMEN DE PEDIDOS");
        List<Pedido> lista=PedidoDAO.listarTodos();
        int pend=0, prep=0,listo=0,entregado=0,cancelado=0;
        for(Pedido p : lista){
            String e = p.getEstado();
            if(e.equals(Pedido.PENDIENTE)){pend++;}
            else if(e.equals(Pedido.EN_PREPARACION)){prep++;}
            else if(e.equals(Pedido.LISTO)){listo++;}
            else if(e.equals(Pedido.ENTREGADO)){entregado++;}
            else if(e.equals(Pedido.CANCELADO)){cancelado++;}
        }
        System.out.println("  Pendientes     : " + pend);
        System.out.println("  En preparacion : " + prep);
        System.out.println("  Listos         : " + listo);
        System.out.println("  Entregados     : " + entregado);
        System.out.println("  Cancelados     : " + cancelado);
        System.out.println("  -------------------------");
        System.out.println("  TOTAL          : " + lista.size());
        Consola.pausar();
    }
}