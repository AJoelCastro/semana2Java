/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Asus
 */
public class Libro {
    private String codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anioPublicacion;
    private int CopiasDisponibles;
    private String editorial;
    
    public Libro() {
        this("NC","NT","NA","NC",0,0,"NE");
    }
    public Libro(String codigo, String titulo, String autor, String categoria, int anioPublicacion, int CopiasDisponibles, String editorial) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.CopiasDisponibles = CopiasDisponibles;
        this.editorial = editorial;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public int getCopiasDisponibles() {
        return CopiasDisponibles;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCopiasDisponibles(int CopiasDisponibles) {
        this.CopiasDisponibles = CopiasDisponibles;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    @Override
    public String toString() {
        return "Libro{" + " codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", anioPublicacion=" + anioPublicacion + ", CopiasDisponibles=" + CopiasDisponibles + ", editorial=" + editorial + '}';
    }
}
