/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Administrador
 */
public class PrestamoBibliotecario {

    private String fechaPrestamo;
    private String fechaPrevista;
    private String fechaDevolucion;
    private String estadoLibro;
    private float multa = 0.0f;
    private int cantidadLibros = 0;

    public PrestamoBibliotecario() {
        this("--/--/--", "--/--/--", "--/--/--", "NN");
    }

    public PrestamoBibliotecario(String fechaPrestamo, String fechaPrevista, 
            String fechaDevolucion, String estadoLibro) {
        this.fechaDevolucion = fechaDevolucion;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevista = fechaPrevista;
        this.estadoLibro = estadoLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(String fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(int cantidad) {
        this.cantidadLibros = cantidad;
    }

    @Override
    public String toString() {
        return "Fecha de Prestamo: " + getFechaPrestamo()
                + "Fecha prevista: " + getFechaPrevista()
                + "Fecha de devolucion: " + getFechaDevolucion()
                + "Estado del Libro: " + getEstadoLibro()
                + "Multa a pagar: " + getMulta()
                + "Cantidad de Libros: " + getCantidadLibros();
    }
}
