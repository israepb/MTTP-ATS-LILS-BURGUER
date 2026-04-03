public class DatosDemostracion{
    public static void cargar(){
        cargarProductos();
        cargarCombos();
        cargarRecetas();
        cargarEmpleados();
        cargarIngredientes();
        cargarProveedores();
        System.out.println("  Datos iniciales cargados.");
    }
    private static void cargarProductos(){
        p("P001","Clasica",           "Carne, papas y ensalada",               35.0,"Hamburguesas",12);
        p("P002","Royal",             "Carne, huevo y papas",                  40.0,"Hamburguesas",14);
        p("P003","Filete Burger",     "Pechuga de pollo y papas",              40.0,"Hamburguesas",14);
        p("P004","Chile Burger",      "Carne, palta, tomate y papas",          40.0,"Hamburguesas",15);
        p("P005","Chori Huevo",       "Chorizo parrillero y huevo",            45.0,"Hamburguesas",13);
        p("P006","Burger a lo Pobre", "Carne, huevo, platano y papas",         48.0,"Hamburguesas",16);
        p("P007","Lomitoki Burger",   "Lomito salteado y papas",               35.0,"Hamburguesas",15);
        p("P008","Bichota Burger",    "Doble carne, aros de cebolla y papas",  48.0,"Hamburguesas",18);
        p("P009","Crispy Burger",     "Filete de pollo broaster y papas",      35.0,"Hamburguesas",14);
        p("P010","Hawaiana Burger",   "Pechuga, queso, pina y papas",          48.0,"Hamburguesas",16);
        p("P011","Hot Burger",        "Carne, chancho, tocino, huevo y papas", 40.0,"Hamburguesas",17);
        p("P012","Lili Burger",       "Carne, huevo, chorizo, pollo y papas",  45.0,"Hamburguesas",18);
        p("P013","Pizza Muzzarella",  "Salsa tomate, queso reggianito",        40.0,"Pizzas",20);
        p("P014","Pizza Jamon",       "Salsa tomate, queso, jamon",            45.0,"Pizzas",22);
        p("P015","Pizza Palmito",     "Salsa tomate, queso, palmito",          45.0,"Pizzas",22);
        p("P016","Pizza Katupiry",    "Salsa tomate, queso crema, cheddar",    45.0,"Pizzas",22);
        p("P017","Pizza Pepperoni",   "Salsa tomate, queso, pepperoni",        45.0,"Pizzas",22);
        p("P018","Pizza Napolitana",  "Salsa tomate, queso, tomate, jamon",    45.0,"Pizzas",22);
        p("P019","Coca-Cola 500ml",   "Gaseosa 500ml",                          8.0,"Bebidas", 1);
        p("P020","Coca-Cola 2L",      "Gaseosa 2 litros",                      18.0,"Bebidas", 1);
        p("P021","Fanta 500ml",       "Gaseosa 500ml",                          8.0,"Bebidas", 1);
        p("P022","Sprite 500ml",      "Gaseosa 500ml",                          8.0,"Bebidas", 1);
        p("P023","Jugo Tumbo 500ml",  "Jugo Del Valle",                         9.0,"Bebidas", 1);
        p("P024","Jugo Manzana 1L",   "Jugo Del Valle",                        15.0,"Bebidas", 1);
        p("P025","Huevo extra",       "Huevo frito",                            4.0,"Extras",  2);
        p("P026","Platano frito",     "Platano frito",                          4.0,"Extras",  2);
        p("P027","Queso extra",       "Queso adicional",                        4.0,"Extras",  1);
        p("P028","Filete pollo ext",  "Filete de pollo",                       12.5,"Extras",  7);
        p("P029","Carne extra",       "Carne adicional",                       15.0,"Extras",  7);
    }
    private static void cargarCombos(){
        combo("C001","Combo Clasica + Bebida", "Hamburguesa Clasica + Coca-Cola 500ml", 43.0, 10.0, "P001,P019", 14);
        combo("C002","Combo Pizza + Bebida", "Pizza Muzzarella + Sprite 500ml", 48.0, 15.0, "P013,P022", 22);
    }

    private static void cargarRecetas(){
        receta("P001", "I001:0.15;I002:1;I009:0.20;I005:0.10;I006:0.05");  
        receta("P002", "I001:0.15;I002:1;I010:1;I009:0.20");              
        receta("P003", "I003:0.15;I002:1;I009:0.20");                      
        receta("P004", "I001:0.15;I002:1;I009:0.20;I005:0.10");           
        receta("P013", "I007:0.25;I008:0.10;I004:0.15");                   
        receta("P014", "I007:0.25;I008:0.10;I004:0.15");                  
    }
    private static void cargarEmpleados(){
        UsuarioDAO.guardar(new Usuario("U001","Natalia",  "Mendoza","70011111","natalia",  "admin123",  Usuario.ADMIN));
        UsuarioDAO.guardar(new Usuario("U002","Alejandro","Vargas", "70022222","alejandro","admin456",  Usuario.ADMIN));
        UsuarioDAO.guardar(new Usuario("U003","Luis",     "Morales","70033333","luis",     "cajero123", Usuario.CAJERO));
        UsuarioDAO.guardar(new Usuario("U004","Carlos",   "Ramirez","70044444","carlos",   "cocina123", Usuario.COCINERO));
    }
    private static void cargarIngredientes(){
        i("I001","Carne de res",    "kg",    5.0, 2.0);
        i("I002","Pan hamburguesa", "unid", 40.0,10.0);
        i("I003","Pechuga pollo",   "kg",    3.0, 1.5);
        i("I004","Queso",           "kg",    2.0, 0.5);
        i("I005","Tomate",          "kg",    2.0, 0.5);
        i("I006","Lechuga",         "kg",    1.5, 0.5);
        i("I007","Harina pizza",    "kg",    4.0, 1.0);
        i("I008","Salsa de tomate", "lt",    3.0, 1.0);
        i("I009","Papas",           "kg",    8.0, 2.0);
        i("I010","Huevo",           "unid", 30.0,10.0);
    }
    private static void cargarProveedores(){
        ProveedorDAO.guardar(new Proveedor("PRV-1","Coca-Cola Bolivia",   "70099001","Av. Blanco Galindo","Bebidas"));
        ProveedorDAO.guardar(new Proveedor("PRV-2","Distribuidora Andina","70099002","Calle Comercio 123","Insumos"));
        ProveedorDAO.guardar(new Proveedor("PRV-3","Panaderia Central",   "70099003","Mercado Central",   "Panaderia"));
        ProveedorDAO.guardar(new Proveedor("PRV-4","Proeza",              "70099004","Av. America",       "Carnes"));
    }
    private static void p(String id, String n, String d, double pr, String c, int t){
        ProductoDAO.guardar(new Producto(id, n, d, pr, c, t));
    }
    private static void combo(String id, String n, String d, double precioBase, double descuento, String ids, int tiempo){
        Producto prod = new Producto(id, n, d, precioBase, "Combos", tiempo);
        prod.setEsCombo(true);
        prod.setDescuento(descuento);
        prod.setReceta(ids);
        ProductoDAO.guardar(prod);
    }
    private static void receta(String idProducto, String recetaStr){
        Producto prod = ProductoDAO.buscar(idProducto);
        if(prod!=null){
            prod.setReceta(recetaStr);
            ProductoDAO.guardar(prod);
        }
    }
    private static void i(String id, String n, String u, double s, double m){
        InventarioDAO.guardar(new Ingrediente(id, n, u, s, m));
    }
}