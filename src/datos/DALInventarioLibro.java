/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import entidades.Libro;
import java.io.*;
import java.util.*;

/**
 *
 * @author ArcosArce
 */
public class DALInventarioLibro {
    private static RandomAccessFile flujo;
    private static final int TAMREG = 160;
    private static int numRegistros;
    private static final String N_ARCHIVO = "inventarioLibros.txt";

    private static String crearArchivo() {
        try {
            flujo = new RandomAccessFile(N_ARCHIVO, "rw");
            numRegistros = (int)Math.ceil((double)flujo.length() / (double)TAMREG);
        } catch (IOException ex) {
            return "Problema al crear el flujo: " + ex.getMessage();
        }
        return null;
    }

    // Registrar un libro en el archivo
    public static String registrarLibro(Libro libro) {
        String mensaje = "";
        try {
            crearArchivo();
            flujo.close();
            mensaje = setLibro(numRegistros, libro);
            if (mensaje.equals("ok")) numRegistros++;
        } catch (IOException ex) {
            mensaje = "Excepcion: " + ex.getMessage();
        }
        return mensaje;
    }

    // Guardar un libro en el archivo en una posición específica
    public static String setLibro(int posicion, Libro libro) {
        String mensaje = "";
        try {
            if (libro.getTitulo().length() + libro.getCategoria().length() + 4 > TAMREG)
                mensaje = "Tamaño de registro insuficiente";
            else {
                crearArchivo();
                flujo.seek(posicion * TAMREG);
                flujo.writeUTF(libro.getCodigo());
                flujo.writeUTF(libro.getTitulo());
                flujo.writeUTF(libro.getAutor());
                flujo.writeUTF(libro.getCategoria());
                flujo.writeInt(libro.getAnioPublicacion());
                flujo.writeInt(libro.getCopiasDisponibles());
                flujo.writeUTF(libro.getEditorial());
                mensaje = "ok";
            }
        } catch (IOException ex) {
            mensaje = "Excepcion: " + ex.getMessage();
        }
        return mensaje;
    }

    // Leer un libro desde una posición
    public static Libro getLibro(int pos) {
        String codigo, titulo, autor, categoria, editorial;
        int anioPublicacion, copiasDisponibles;
        Libro libro = null;
        try {
            crearArchivo();
            flujo.seek(pos * TAMREG);
            codigo = flujo.readUTF();
            titulo = flujo.readUTF();
            autor = flujo.readUTF();
            categoria = flujo.readUTF();
            anioPublicacion = flujo.readInt();
            copiasDisponibles = flujo.readInt();
            editorial = flujo.readUTF();
            libro = new Libro(codigo, titulo, autor, categoria, anioPublicacion, copiasDisponibles, editorial);
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        }
        return libro;
    }
    
    public static ArrayList<Libro> obtenerInventario() {
        ArrayList<Libro> lista = new ArrayList<>();
        try {
            crearArchivo();
            flujo.close();
            for (int i = 0; i < numRegistros; i++)
                lista.add(getLibro(i));
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        }
        return lista;
    }

    // Eliminar un libro por código
    public static void eliminarLibro(String codigoBuscado) throws IOException {
        // Buscar la posición del libro con el código dado
        boolean libroEncontrado = false;
        int posicion = -1;  // Guardamos la posición del libro a eliminar
        for (int i = 0; i < numRegistros; i++) {
            Libro libro = getLibro(i);  // Leer el libro en la posición i
            if (libro.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                libroEncontrado = true;
                posicion = i;
                break;
            }
        }

        // Si no se encuentra el libro, lanzamos una excepción
        if (!libroEncontrado) {
            throw new IOException("El libro con el código proporcionado no existe.");
        }

        // Sobrescribir el libro encontrado con un "libro vacío"
        Libro libroVacio = new Libro("", "", "", "", 0, 0, "");  // Crear un libro con datos vacíos
        String mensaje = setLibro(posicion, libroVacio);  // Sobrescribir el libro en esa posición

        if (!mensaje.equals("ok")) {
            throw new IOException("Error al sobrescribir el libro.");
        }

        // Actualizar el archivo después de la sobrescritura
        flujo = new RandomAccessFile("inventarioLibros.txt", "rw");

        // Actualizar la tabla o realizar otras acciones necesarias
        System.out.println("Libro eliminado exitosamente.");
    }

    // Editar un libro por código
    public static void editarLibro(String codigoBuscado, Libro nuevoLibro) throws IOException {
        boolean libroEncontrado = false;
        int posicion = -1;  // Variable para almacenar la posición del libro

        // Buscar el libro por código y obtener su posición
        for (int i = 0; i < numRegistros; i++) {
            Libro libro = getLibro(i);
            if (libro.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                libroEncontrado = true;
                posicion = i;  // Guardamos la posición del libro
                break;
            }
        }

        if (!libroEncontrado) {
            throw new IOException("El libro con el código proporcionado no existe.");
        }

        // Llamamos al método setLibro para reemplazar el libro en la posición encontrada
        String mensaje = setLibro(posicion, nuevoLibro);

        if (!mensaje.equals("ok")) {
            throw new IOException("No se pudo editar el libro: " + mensaje);
        }
    }

    // Obtener el número total de registros de libros
    public static int obtenerNumeroRegistros() {
        return numRegistros;
    }
}
