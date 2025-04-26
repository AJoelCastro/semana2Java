/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Lector;
import entidades.Libro;
import entidades.PrestamoBibliotecario;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author artur
 */
public class HistorialPrestamos {

    private final String nombreArchivo = "Historial Prestamos.txt";

    public ArrayList<PrestamoBibliotecario> leerIngresos() {
        ArrayList<PrestamoBibliotecario> historialPrestamos = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists())
            return historialPrestamos;

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                // Leer datos del archivo en el mismo orden que fueron guardados
                String titulo = raf.readUTF();
                String autor = raf.readUTF();
                int idPrestamo = raf.readInt();
                int copiasDisponibles = raf.readInt();
                String fechaPrestamo = raf.readUTF();
                String fechaPrevista = raf.readUTF();
                String estadoLibro = raf.readUTF();

                Libro libro = new Libro();
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setCopiasDisponibles(copiasDisponibles);

                Lector lector = buscarPrestamo(idPrestamo);

                PrestamoBibliotecario prestamo = new PrestamoBibliotecario(idPrestamo,
                        fechaPrestamo, fechaPrevista, "--/--/--", estadoLibro, libro, lector
                );

                historialPrestamos.add(prestamo);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
        return historialPrestamos;
    }
    
    public Lector buscarPrestamo(int idPrestamo) {
        Lector lec = new Lector();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(idPrestamo == prestamo.getIdPrestamo()){
                lec = prestamo.getLector();
            }
        }
        return lec;
    }
    public PrestamoBibliotecario buscarPrestamoTotal(int idPrestamo) {
        PrestamoBibliotecario prest = new PrestamoBibliotecario();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(idPrestamo == prestamo.getIdPrestamo()){
                prest = prestamo;
            }
        }
        return prest;
    }
    
    public void aniadirPrestamo(PrestamoBibliotecario prestamo) {
        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            raf.seek(raf.length()); // Moverse al final del archivo

            // Guardar los datos en el mismo orden que los vas a leer después
            raf.writeUTF(prestamo.getLibro().getTitulo());
            raf.writeUTF(prestamo.getLibro().getAutor());
            raf.writeInt(prestamo.getIdPrestamo());
            raf.writeInt(prestamo.getLibro().getCopiasDisponibles());
            raf.writeUTF(prestamo.getLector().getNombre());
            raf.writeUTF(prestamo.getLector().getTelefono());
            raf.writeUTF(prestamo.getFechaPrestamo());
            raf.writeUTF(prestamo.getFechaPrevista());
            raf.writeUTF(prestamo.getEstadoLibro());
        } catch (IOException e) {
            System.out.println("Error al añadir préstamo: " + e.getMessage());
        }
    }
    public void eliminarPrestamo(int idPrestamo) {
        PrestamoBibliotecario prest = buscarPrestamoTotal(idPrestamo);
        ArrayList<PrestamoBibliotecario> historialPrestamos = leerIngresos();
        historialPrestamos.remove(prest);
        guardarHistorialCompleto(historialPrestamos);
    }
    
    public void editarPrestamo(int idPrestamo, String newDatePrevista, Libro lib, Lector lec){
        PrestamoBibliotecario prest = buscarPrestamoTotal(idPrestamo);
        ArrayList<PrestamoBibliotecario> historialPrestamos = leerIngresos();
        if(historialPrestamos.contains(prest)){
            prest.setFechaPrevista(newDatePrevista);
            prest.setLibro(lib);
            prest.setLector(lec);
        }else{
            return;
        }
        guardarHistorialCompleto(historialPrestamos);
    }

    public void guardarHistorialCompleto(ArrayList<PrestamoBibliotecario> lista) {
        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            raf.setLength(0); // Vaciar el archivo antes de escribir

            for (PrestamoBibliotecario prestamo : lista) {
                raf.writeUTF(prestamo.getLibro().getTitulo());
                raf.writeUTF(prestamo.getLibro().getAutor());
                raf.writeInt(prestamo.getIdPrestamo());
                raf.writeInt(prestamo.getLibro().getCopiasDisponibles());
                raf.writeUTF(prestamo.getLector().getNombre());
                raf.writeUTF(prestamo.getLector().getTelefono());
                raf.writeUTF(prestamo.getFechaPrestamo());
                raf.writeUTF(prestamo.getFechaPrevista());
                raf.writeUTF(prestamo.getEstadoLibro());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar historial completo: " + e.getMessage());
        }
    }
    public DefaultTableModel getContenido() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {
            "Titulo Libro", "Autor", "Id Prestamo", "Copias Disponibles",
            "Nombre Lector", "Telefono", "Fecha-Prestamo", "Fecha-Prevista","Estado"
        };
        modelo.setColumnIdentifiers(columnas); // Establecer nombres de columnas

        ArrayList<PrestamoBibliotecario> lista = leerIngresos(); // Leer ingresos desde archivo
        for (PrestamoBibliotecario prestamo : lista) {
            Object[] fila = new Object[columnas.length];
            fila[0] = prestamo.getLibro().getTitulo();
            fila[1] = prestamo.getLibro().getAutor();
            fila[2] = prestamo.getIdPrestamo();
            fila[3] = prestamo.getLibro().getCopiasDisponibles();
            fila[4] = prestamo.getLector().getNombre();
            fila[5] = prestamo.getLector().getTelefono();
            fila[6] = prestamo.getFechaPrestamo();
            fila[7] = prestamo.getFechaPrevista();
            fila[8] = prestamo.getEstadoLibro();
            modelo.addRow(fila); // Agregar fila al modelo de la tabla
        }

        return modelo; // Retornar el modelo para usarlo en la GUI
    }
}
