package model;

import java.sql.Date;

public class Remesa {
    private int idRemesa;
    private int idRemitente;
    private int idDestinatario;
    private double monto;
    private Date fechaEnvio;
    private String referencia;

    public Remesa() {}

    public Remesa(int idRemitente, int idDestinatario, double monto, Date fechaEnvio, String referencia) {
        this.idRemitente = idRemitente;
        this.idDestinatario = idDestinatario;
        this.monto = monto;
        this.fechaEnvio = fechaEnvio;
        this.referencia = referencia;
    }

    public int getIdRemesa() { return idRemesa; }
    public void setIdRemesa(int idRemesa) { this.idRemesa = idRemesa; }
    public int getIdRemitente() { return idRemitente; }
    public void setIdRemitente(int idRemitente) { this.idRemitente = idRemitente; }
    public int getIdDestinatario() { return idDestinatario; }
    public void setIdDestinatario(int idDestinatario) { this.idDestinatario = idDestinatario; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}
