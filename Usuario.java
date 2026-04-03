public class Usuario{
    public static final String ADMIN = "ADMIN";
    public static final String CAJERO = "CAJERO";
    public static final String COCINERO = "COCINERO";
    public static final String CLIENT = "CLIENTE";
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private boolean activo;
    public Usuario(String idUsuario, String nombre, String apellido, String telefono, String nombreUsuario, String contrasena, String rol){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = true;
    }
    public boolean validarContrasena(String c){return contrasena.equals(c);}
    public String getIdUsuario(){return idUsuario;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getTelefono(){return telefono;}
    public String getNombreUsuario(){return nombreUsuario;}
    public String getContrasena(){return contrasena;}
    public String getRol(){return rol;}
    public boolean isActivo(){return activo;}
    public void setNombre(String n){nombre=n;}
    public void setApellido(String a){apellido=a;}
    public void setTelefono(String t){telefono=t;}
    public void setActivo(boolean a){activo=a;}
    @Override
    public String toString(){
        return "[" + idUsuario + "] " + nombre + " " + apellido + " | " + rol + " | " + (activo ? "Activo" : "Inactivo");
    }
}