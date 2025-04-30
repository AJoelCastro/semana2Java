/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import datos.DALInventarioLibro;
import entidades.Libro;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author ArcosArce
 */
public class BLInventarioLibro {
    private static TableColumn columna;
    private static DefaultTableModel modelo;

    // Método para registrar un libro
    public static String registrarLibro(String codigo, String titulo, String autor, String categoria, 
                                        int anioPublicacion, int copiasDisponibles, String editorial) {
        return DALInventarioLibro.registrarLibro(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
    }

    // Método para listar todos los libros
    public static DefaultTableModel listarLibros() {
        ArrayList<Libro> listaLibros = DALInventarioLibro.obtenerInventario(); // Obtén todos los libros del DAL
        String columnas[] = {"Código", "Título", "Autor", "Categoría", "Año Publicación", "Copias Disponibles", "Editorial"};
        Object fila[] = new Object[columnas.length];
        
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);
        
        int i = 0;
        for (Libro libro : listaLibros) {
            fila[0] = libro.getCodigo();
            fila[1] = libro.getTitulo();
            fila[2] = libro.getAutor();
            fila[3] = libro.getCategoria();
            fila[4] = libro.getAnioPublicacion();
            fila[5] = libro.getCopiasDisponibles();
            fila[6] = libro.getEditorial();
            modelo.addRow(fila);
            i++;
        }
        return modelo;
    }

    // Método para realizar la búsqueda de libros según el campo
    public static ArrayList<Libro> buscarLibro(String criterio, String valor) {
        ArrayList<Libro> libros = DALInventarioLibro.obtenerInventario(); // Obtener todos los libros desde DAL
        ArrayList<Libro> encontrados = new ArrayList<>();

        // Filtrar según el criterio seleccionado
        for (Libro libro : libros) {
            switch (criterio.toLowerCase()) {
                case "codigo":
                    if (libro.getCodigo().equalsIgnoreCase(valor)) {
                        encontrados.add(libro);
                    }
                    break;
                case "titulo":
                    if (libro.getTitulo().equalsIgnoreCase(valor)) {
                        encontrados.add(libro);
                    }
                    break;
                case "autor":
                    if (libro.getAutor().equalsIgnoreCase(valor)) {
                        encontrados.add(libro);
                    }
                    break;
                case "categoria":
                    if (libro.getCategoria().equalsIgnoreCase(valor)) {
                        encontrados.add(libro);
                    }
                    break;
                default:
                    break;
            }
        }

        return encontrados;
    }
    public static Libro buscarPorCodigo(String codigo) {
        ArrayList<Libro> libros = DALInventarioLibro.obtenerInventario();
        Libro libroEncontrado = new Libro();
        
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                libroEncontrado = libro;
            }
        }

        return libroEncontrado;
    }

    // Método para obtener el número total de registros de libros
    public static int obtenerNumeroRegistros() {
        return DALInventarioLibro.obtenerNumeroRegistros();
    }

    // Configuración de las columnas de la tabla
    public static void configurarColumnasTabla(JTable tblLibro) {
        columna = tblLibro.getColumnModel().getColumn(0);
        columna.setPreferredWidth(20);
        columna = tblLibro.getColumnModel().getColumn(1);
        columna.setPreferredWidth(90);
        columna = tblLibro.getColumnModel().getColumn(2);
        columna.setPreferredWidth(90);
        columna = tblLibro.getColumnModel().getColumn(3);
        columna.setPreferredWidth(30);
        columna = tblLibro.getColumnModel().getColumn(4);
        columna.setPreferredWidth(30);
    }
}
