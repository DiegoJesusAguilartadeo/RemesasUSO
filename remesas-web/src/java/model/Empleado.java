package model;

public class Empleado {

    private Integer idEmpleado;
    private String nombre;
    private String correo;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado, String nombre, String correo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
