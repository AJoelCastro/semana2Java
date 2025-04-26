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
    private String id; //Identificador único de cada libro
    private String codigo; //codigo de libro(interno en la biblioteca)
    private String titulo;
    private String autor;
    private String categoria;
    private int anioPublicacion;
    private int CopiasDisponibles;
    private String editorial;
    private String isbn; //codigo que identifica al libro a nivel mundial

    public Libro(String id, String codigo, String titulo, String autor, String categoria, int anioPublicacion, int CopiasDisponibles, String editorial, String isbn) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.CopiasDisponibles = CopiasDisponibles;
        this.editorial = editorial;
        this.isbn = isbn;
        
    }

    public String getId() {
        return id;
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

    public String getIsbn() {
        return isbn;
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


    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", anioPublicacion=" + anioPublicacion + ", CopiasDisponibles=" + CopiasDisponibles + ", editorial=" + editorial + ", isbn=" + isbn + '}';
    }
}
