/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import entidades.Libro;
import java.io.File;
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

        archivo.writeUTF(libro.getCodigo());
        archivo.writeUTF(libro.getTitulo());
        archivo.writeUTF(libro.getAutor());
        archivo.writeUTF(libro.getCategoria());
        archivo.writeInt(libro.getAnioPublicacion());
        archivo.writeInt(libro.getCopiasDisponibles());
        archivo.writeUTF(libro.getEditorial());
    }


    public static void listarTodos(javax.swing.table.DefaultTableModel modelo) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0); 

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            modelo.addRow(new Object[]{codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial});
        }
    }


    public static void buscarPorAutor(javax.swing.table.DefaultTableModel modelo, String autorBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (autor.equalsIgnoreCase(autorBuscado)) {
                modelo.addRow(new Object[]{codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial});
            }
        }
    }

    public static void buscarPorTitulo(javax.swing.table.DefaultTableModel modelo, String tituloBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (titulo.equalsIgnoreCase(tituloBuscado)) {
                modelo.addRow(new Object[]{codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial});
            }
        }
    }

    public static Libro buscarPorCodigo(String codigoBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        // Recorrer el archivo hasta encontrar el libro con el código buscado
        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            // Si el código coincide, devolver el objeto Libro
            if (codigo.equalsIgnoreCase(codigoBuscado)) {
                return new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial);
            }
        }

        return null;  // Si no se encuentra el libro con el código dado, devolver null
    }

    public static void buscarPorCategoria(javax.swing.table.DefaultTableModel modelo, String categoriaBuscada) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        modelo.setRowCount(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (categoria.equalsIgnoreCase(categoriaBuscada)) {
                modelo.addRow(new Object[]{codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial});
            }
        }
    }
    public static void eliminarLibro(String idBuscado) throws IOException {
        inicializarArchivo();

        RandomAccessFile temp = new RandomAccessFile("Temp.dat", "rw");
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (!codigo.equalsIgnoreCase(idBuscado)) {
                temp.writeUTF(codigo);
                temp.writeUTF(titulo);
                temp.writeUTF(autor);
                temp.writeUTF(categoria);
                temp.writeInt(anioPublicacion);
                temp.writeInt(copiasDisponibles);
                temp.writeUTF(editorial);
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
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (codigo.equalsIgnoreCase(idBuscado)) {
                
                temp.writeUTF(nuevoLibro.getCodigo());
                temp.writeUTF(nuevoLibro.getTitulo());
                temp.writeUTF(nuevoLibro.getAutor());
                temp.writeUTF(nuevoLibro.getCategoria());
                temp.writeInt(nuevoLibro.getAnioPublicacion());
                temp.writeInt(nuevoLibro.getCopiasDisponibles());
                temp.writeUTF(nuevoLibro.getEditorial());
            } else {
                
                temp.writeUTF(codigo);
                temp.writeUTF(titulo);
                temp.writeUTF(autor);
                temp.writeUTF(categoria);
                temp.writeInt(anioPublicacion);
                temp.writeInt(copiasDisponibles);
                temp.writeUTF(editorial);
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



