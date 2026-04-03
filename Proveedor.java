public class Proveedor{
    private String idProveedor;
    private String nombre;
    private String telefono;
    private String direccion;
    private String tipoProducto;
    public Proveedor(String idProveedor, String nombre, String telefono, String direccion, String tipoProducto){
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoProducto = tipoProducto;
    }
    public String getIdProveedor(){return idProveedor;}
    public String getNombre(){return nombre;}
    public String getTelefono(){return telefono;}
    public String getDireccion(){return direccion;}
    public String getTipoProducto(){return tipoProducto;}
    public void setNombre(String n){nombre=n;}
    public void setTelefono(String t){telefono=t;}
    public void setDireccion(String d){direccion=d;}
    public void setTipoProducto(String t){tipoProducto=t;}
    @Override
    public String toString(){
        return String.format("[%-6s] %-22s | Tel: %-12s | %s", idProveedor, nombre, telefono, tipoProducto);
    }
}