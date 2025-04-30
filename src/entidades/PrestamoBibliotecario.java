/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Administrador
 */
public class PrestamoBibliotecario {

    private int idPrestamo;
    private String fechaPrestamo;
    private String fechaPrevista;
    private String fechaDevolucion;
    private String estadoLibro;
    private Libro libro;
    private Usuario usuario;
    private float multa = 0.0f;
    private int cantidadLibros = 0;

    public PrestamoBibliotecario() {
        this(0, "--/--/--", new Libro(), new Usuario());
    }

    public PrestamoBibliotecario(int idPrestamo, String fechaPrevista, Libro libro, Usuario usuario) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = obtenerFechaActual();
        this.fechaPrevista = fechaPrevista;
        this.usuario = usuario;
        this.libro = libro;
    }

    // Método para obtener la fecha actual en formato "dd/MM/yyyy" usando GregorianCalendar
    private static String obtenerFechaActual() {
        GregorianCalendar calendario = new GregorianCalendar(); // Crear un objeto GregorianCalendar
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // Definir el formato de fecha
        return formato.format(calendario.getTime()); // Obtener la fecha actual formateada
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getTamaño() {
        return getFechaPrestamo().length() * 2 + getFechaDevolucion().length() * 2 + getFechaPrevista().length() * 2 + getUsuario().getNombre().length()*2 + getUsuario().getCorreo().length()*2 + 
                + getLibro().getTitulo().length()*2 + getLibro().getAutor().length()*2 + getUsuario().getTelefono().length()*2 + 4 + 4;
    }

    @Override
    public String toString() {
        return "Nombre del libro: " + libro.getTitulo()
                + "Codigo del libro: " + libro.getCodigo()
                + "Fecha de Prestamo: " + getFechaPrestamo()
                + "Fecha prevista: " + getFechaPrevista()
                + "Fecha de devolucion: " + getFechaDevolucion()
                + "Estado del Libro: " + getEstadoLibro()
                + "Multa a pagar: " + getMulta()
                + "Cantidad de Libros: " + getCantidadLibros();
    }
}
