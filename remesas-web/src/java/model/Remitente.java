package model;

public class Remitente {
    private int idRemitente;
    private String nombre;
    private String apellido;
    private String pais;
    private String telefono;

    public Remitente() {}

    public Remitente(String nombre, String apellido, String pais, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.telefono = telefono;
    }

    public int getIdRemitente() { return idRemitente; }
    public void setIdRemitente(int idRemitente) { this.idRemitente = idRemitente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
