package model;

import java.sql.Date;

public class Remesa {

    private int idRemesa;
    private int idRemitente;
    private int idDestinatario;
    private double monto;
    private Date fechaEnvio;
    private String referencia;

    // Nuevos campos añadidos
    private String pin;
    private String estado;
    private Date fechaDisponible;
    private String metodoCobro;
    private double montoTotal;
    private double fee;
    private String numeroOrden;
    private Date fechaCobro;

    // Campos relacionados con empleados (según tus columnas)
    private Integer idEmpleadoCreador;
    private Integer idEmpleadoCobrador;
    private Integer idEmpleadoRegistro; // quien registró la remesa en el sistema (operador)
    private Integer idEmpleadoCobro;    // quien efectivamente hizo el cobro

    // 🔹 Nuevos campos para los reportes (nombres en vez de IDs)
    private String nombreRemitente;
    private String nombreDestinatario;

    public Remesa() {}

    public Remesa(int idRemitente, int idDestinatario, double monto, Date fechaEnvio, String referencia) {
        this.idRemitente = idRemitente;
        this.idDestinatario = idDestinatario;
        this.monto = monto;
        this.fechaEnvio = fechaEnvio;
        this.referencia = referencia;
    }

    // getters / setters
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

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaDisponible() { return fechaDisponible; }
    public void setFechaDisponible(Date fechaDisponible) { this.fechaDisponible = fechaDisponible; }

    public String getMetodoCobro() { return metodoCobro; }
    public void setMetodoCobro(String metodoCobro) { this.metodoCobro = metodoCobro; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public String getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }

    public Date getFechaCobro() { return fechaCobro; }
    public void setFechaCobro(Date fechaCobro) { this.fechaCobro = fechaCobro; }

    public Integer getIdEmpleadoCreador() { return idEmpleadoCreador; }
    public void setIdEmpleadoCreador(Integer idEmpleadoCreador) { this.idEmpleadoCreador = idEmpleadoCreador; }

    public Integer getIdEmpleadoCobrador() { return idEmpleadoCobrador; }
    public void setIdEmpleadoCobrador(Integer idEmpleadoCobrador) { this.idEmpleadoCobrador = idEmpleadoCobrador; }

    public Integer getIdEmpleadoRegistro() { return idEmpleadoRegistro; }
    public void setIdEmpleadoRegistro(Integer idEmpleadoRegistro) { this.idEmpleadoRegistro = idEmpleadoRegistro; }

    public Integer getIdEmpleadoCobro() { return idEmpleadoCobro; }
    public void setIdEmpleadoCobro(Integer idEmpleadoCobro) { this.idEmpleadoCobro = idEmpleadoCobro; }

    // 🔹 Nuevos getters/setters para nombres
    public String getNombreRemitente() { return nombreRemitente; }
    public void setNombreRemitente(String nombreRemitente) { this.nombreRemitente = nombreRemitente; }

    public String getNombreDestinatario() { return nombreDestinatario; }
    public void setNombreDestinatario(String nombreDestinatario) { this.nombreDestinatario = nombreDestinatario; }
}
