package org.example.domain;
import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Cita implements Serializable {
    private static final long serialVersionaUID = 1L;
    private int idCita;
    private Date fecha;
    private Date hora;
    private String motivo;
    private Paciente paciente;

    public Cita(){
    }

    public Cita(int idCita, Date fecha, Date hora, String motivo, Paciente paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return "Cita{" +
                "Cita Id: " + idCita +
                ", fecha: " + dateFormat.format(fecha) +
                ", hora: " + timeFormat.format(hora) +
                ", motivo: '" + motivo + '\'' +
                ", paciente: " + paciente +
                '}';
    }
}
