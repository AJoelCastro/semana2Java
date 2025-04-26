/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import entidades.Libro;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */

public class ListaLibros {
    private static RandomAccessFile archivo;

    public static void inicializarArchivo() {
        try {
            if (archivo == null) {
                archivo = new RandomAccessFile("Libro.dat", "rw");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + e.getMessage());
        }
    }

    public static RandomAccessFile getArchivo() {
        return archivo;
    }

    public static void escribir(Libro libro) throws IOException {
        inicializarArchivo();
        archivo.seek(archivo.length()); 

        archivo.writeUTF(libro.getId());
        archivo.writeUTF(libro.getCodigo());
        archivo.writeUTF(libro.getTitulo());
        archivo.writeUTF(libro.getAutor());
        archivo.writeUTF(libro.getCategoria());
        archivo.writeInt(libro.getAnioPublicacion());
        archivo.writeInt(libro.getCopiasDisponibles());
        archivo.writeUTF(libro.getEditorial());
        archivo.writeUTF(libro.getIsbn());
    }


    public static void listarTodos(javax.swing.table.DefaultTableModel modelo) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0); 

        while (archivo.getFilePointer() < archivo.length()) {
            String id = archivo.readUTF();
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();
            String isbn = archivo.readUTF();

            modelo.addRow(new Object[]{id, codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial, isbn});
        }
    }


    public static void buscarPorAutor(javax.swing.table.DefaultTableModel modelo, String autorBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String id = archivo.readUTF();
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();
            String isbn = archivo.readUTF();

            if (autor.equalsIgnoreCase(autorBuscado)) {
                modelo.addRow(new Object[]{id, codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial, isbn});
            }
        }
    }

    public static void buscarPorTitulo(javax.swing.table.DefaultTableModel modelo, String tituloBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String id = archivo.readUTF();
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();
            String isbn = archivo.readUTF();

            if (titulo.equalsIgnoreCase(tituloBuscado)) {
                modelo.addRow(new Object[]{id, codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial, isbn});
            }
        }
    }

    public static void buscarPorCodigo(javax.swing.table.DefaultTableModel modelo, String codigoBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String id = archivo.readUTF();
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();
            String isbn = archivo.readUTF();

            if (codigo.equalsIgnoreCase(codigoBuscado)) {
                modelo.addRow(new Object[]{id, codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial, isbn});
            }
        }
    }

    public static void buscarPorCategoria(javax.swing.table.DefaultTableModel modelo, String categoriaBuscada) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String id = archivo.readUTF();
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();
            String isbn = archivo.readUTF();

            if (categoria.equalsIgnoreCase(categoriaBuscada)) {
                modelo.addRow(new Object[]{id, codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial, isbn});
            }
        }
    }
    public static void eliminarLibro(String idBuscado) throws IOException {
    inicializarArchivo();

    RandomAccessFile temp = new RandomAccessFile("Temp.dat", "rw");
    archivo.seek(0);

    while (archivo.getFilePointer() < archivo.length()) {
        String id = archivo.readUTF();
        String codigo = archivo.readUTF();
        String titulo = archivo.readUTF();
        String autor = archivo.readUTF();
        String categoria = archivo.readUTF();
        int anioPublicacion = archivo.readInt();
        int copiasDisponibles = archivo.readInt();
        String editorial = archivo.readUTF();
        String isbn = archivo.readUTF();

        if (!id.equalsIgnoreCase(idBuscado)) {
            temp.writeUTF(id);
            temp.writeUTF(codigo);
            temp.writeUTF(titulo);
            temp.writeUTF(autor);
            temp.writeUTF(categoria);
            temp.writeInt(anioPublicacion);
            temp.writeInt(copiasDisponibles);
            temp.writeUTF(editorial);
            temp.writeUTF(isbn);
        }
    }

    archivo.close();
    temp.close();

    java.io.File original = new java.io.File("Libro.dat");
    java.io.File temporal = new java.io.File("Temp.dat");

    if (original.delete()) {
        temporal.renameTo(original);
    }

    archivo = new RandomAccessFile("Libro.dat", "rw"); 
    }
    public static void editarLibro(String idBuscado, Libro nuevoLibro) throws IOException {
    inicializarArchivo();

    RandomAccessFile temp = new RandomAccessFile("Temp.dat", "rw");
    archivo.seek(0);

    while (archivo.getFilePointer() < archivo.length()) {
        String id = archivo.readUTF();
        String codigo = archivo.readUTF();
        String titulo = archivo.readUTF();
        String autor = archivo.readUTF();
        String categoria = archivo.readUTF();
        int anioPublicacion = archivo.readInt();
        int copiasDisponibles = archivo.readInt();
        String editorial = archivo.readUTF();
        String isbn = archivo.readUTF();

        if (id.equalsIgnoreCase(idBuscado)) {
            
            temp.writeUTF(nuevoLibro.getId());
            temp.writeUTF(nuevoLibro.getCodigo());
            temp.writeUTF(nuevoLibro.getTitulo());
            temp.writeUTF(nuevoLibro.getAutor());
            temp.writeUTF(nuevoLibro.getCategoria());
            temp.writeInt(nuevoLibro.getAnioPublicacion());
            temp.writeInt(nuevoLibro.getCopiasDisponibles());
            temp.writeUTF(nuevoLibro.getEditorial());
            temp.writeUTF(nuevoLibro.getIsbn());
        } else {
            
            temp.writeUTF(id);
            temp.writeUTF(codigo);
            temp.writeUTF(titulo);
            temp.writeUTF(autor);
            temp.writeUTF(categoria);
            temp.writeInt(anioPublicacion);
            temp.writeInt(copiasDisponibles);
            temp.writeUTF(editorial);
            temp.writeUTF(isbn);
        }
    }

    archivo.close();
    temp.close();

    java.io.File original = new java.io.File("Libro.dat");
    java.io.File temporal = new java.io.File("Temp.dat");

    if (original.delete()) {
        temporal.renameTo(original);
    }

    archivo = new RandomAccessFile("Libro.dat", "rw"); 
    }

}
