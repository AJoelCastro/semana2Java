/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Libro;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    public static ArrayList<Libro> listarTodos() throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        ArrayList<Libro> libros = new ArrayList<>();

        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            libros.add(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
        }
        return libros;
    }

    public static ArrayList<Libro> buscarPorAutor(String autorBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        
        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (autor.equalsIgnoreCase(autorBuscado)) {
                librosEncontrados.add(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
            }
        }
        return librosEncontrados;
    }

    public static ArrayList<Libro> buscarPorTitulo(String tituloBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        
        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (titulo.equalsIgnoreCase(tituloBuscado)) {
                librosEncontrados.add(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
            }
        }
        return librosEncontrados;
    }

    public static ArrayList<Libro> buscarPorCodigo(String codigoBuscado) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        
        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (codigo.equalsIgnoreCase(codigoBuscado)) {
                librosEncontrados.add(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
            }
        }
        return librosEncontrados;
    }

    public static ArrayList<Libro> buscarPorCategoria(String categoriaBuscada) throws IOException {
        inicializarArchivo();
        archivo.seek(0);

        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        
        while (archivo.getFilePointer() < archivo.length()) {
            String codigo = archivo.readUTF();
            String titulo = archivo.readUTF();
            String autor = archivo.readUTF();
            String categoria = archivo.readUTF();
            int anioPublicacion = archivo.readInt();
            int copiasDisponibles = archivo.readInt();
            String editorial = archivo.readUTF();

            if (categoria.equalsIgnoreCase(categoriaBuscada)) {
                librosEncontrados.add(new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial));
            }
        }
        return librosEncontrados;
    }

    public static void eliminarLibro(String codigoBuscado) throws IOException {
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

            if (!codigo.equalsIgnoreCase(codigoBuscado)) {
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

    public static void editarLibro(String codigoBuscado, Libro nuevoLibro) throws IOException {
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

            if (codigo.equalsIgnoreCase(codigoBuscado)) {
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