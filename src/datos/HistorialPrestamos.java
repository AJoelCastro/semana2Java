/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Usuario;
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
                // Leer datos del libro
                String codigo = raf.readUTF();
                String titulo = raf.readUTF();
                String autor = raf.readUTF();
                String categoria = raf.readUTF();
                int anio = raf.readInt();
                int copias = raf.readInt();
                String editorial = raf.readUTF();

                // Leer datos del préstamo
                int idPrestamo = raf.readInt();
                String fechaPrestamo = raf.readUTF();
                String fechaPrevista = raf.readUTF();
                String estadoLibro = raf.readUTF();

                // Leer datos del lector
                String idLector = raf.readUTF();
                String nombreLector = raf.readUTF();
                String telefonoLector = raf.readUTF();

                Libro libro = new Libro(codigo, titulo, autor, categoria, anio, copias, editorial);
                Usuario usuario = new Usuario("Desconocido", "Sin dirección", 0, "NA", "NC");

                PrestamoBibliotecario prestamo = new PrestamoBibliotecario(idPrestamo,
                    fechaPrestamo, fechaPrevista, "--/--/--", estadoLibro, libro, usuario);

                historialPrestamos.add(prestamo);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
        return historialPrestamos;
    }
    
    public Usuario buscarPrestamoPorUsuario(int idPrestamo) {
        Usuario user = new Usuario();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(idPrestamo == prestamo.getIdPrestamo()){
                user = prestamo.getUsuario();
            }
        }
        return user;
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
    
    public PrestamoBibliotecario buscarPrestamoPorLibro(String nomLibro) {
        PrestamoBibliotecario prest = new PrestamoBibliotecario();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(nomLibro.equals(prestamo.getLibro().getTitulo())){
                prest = prestamo;
            }
        }
        return prest;
    }
    
    public PrestamoBibliotecario buscarPrestamoPorIdLector(int dni) {
        PrestamoBibliotecario prest = new PrestamoBibliotecario();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(dni==prestamo.getUsuario().getDni()){
                prest = prestamo;
            }
        }
        return prest;
    }
    public void aniadirPrestamo(PrestamoBibliotecario prestamo) {
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo1 : lista) {
            if (prestamo1.getIdPrestamo() == prestamo.getIdPrestamo()) {
                throw new Error("El id de préstamo ya existe");
            }
            if(prestamo1.getUsuario().getDni()==prestamo.getUsuario().getDni()){
                throw new Error("El usuario ya tiene un prestamo pendiente");
            }
        }
        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            raf.seek(raf.length());
            
            raf.writeUTF(prestamo.getLibro().getCodigo());
            raf.writeUTF(prestamo.getLibro().getTitulo());
            raf.writeUTF(prestamo.getLibro().getAutor());
            raf.writeUTF(prestamo.getLibro().getCategoria());
            raf.writeInt(prestamo.getLibro().getAnioPublicacion());
            raf.writeInt(prestamo.getLibro().getCopiasDisponibles());
            raf.writeUTF(prestamo.getLibro().getEditorial());

            // Datos del préstamo
            raf.writeInt(prestamo.getIdPrestamo());
            raf.writeUTF(prestamo.getFechaPrestamo());
            raf.writeUTF(prestamo.getFechaPrevista());
            raf.writeUTF(prestamo.getEstadoLibro());
            
            // Datos del lector
            raf.writeInt(prestamo.getUsuario().getDni());
            raf.writeUTF(prestamo.getUsuario().getNombre());
            raf.writeUTF(prestamo.getUsuario().getTelefono());

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
    
    public void editarPrestamo(int idPrestamo, String newDatePrevista, Libro lib, Usuario user){
        PrestamoBibliotecario prest = buscarPrestamoTotal(idPrestamo);
        ArrayList<PrestamoBibliotecario> historialPrestamos = leerIngresos();
        if(historialPrestamos.contains(prest)){
            prest.setFechaPrevista(newDatePrevista);
            prest.setLibro(lib);
            prest.setUsuario(user);
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
                raf.writeUTF(prestamo.getUsuario().getNombre());
                raf.writeUTF(prestamo.getUsuario().getTelefono());
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
            "Usuario", "Telefono", "Fecha-Prestamo", "Fecha-Prevista","Estado"
        };
        modelo.setColumnIdentifiers(columnas); // Establecer nombres de columnas

        ArrayList<PrestamoBibliotecario> lista = leerIngresos(); // Leer ingresos desde archivo
        for (PrestamoBibliotecario prestamo : lista) {
            Object[] fila = new Object[columnas.length];
            fila[0] = prestamo.getLibro().getTitulo();
            fila[1] = prestamo.getLibro().getAutor();
            fila[2] = prestamo.getIdPrestamo();
            fila[3] = prestamo.getLibro().getCopiasDisponibles();
            fila[4] = prestamo.getUsuario().getNombre();
            fila[5] = prestamo.getUsuario().getTelefono();
            fila[6] = prestamo.getFechaPrestamo();
            fila[7] = prestamo.getFechaPrevista();
            fila[8] = prestamo.getEstadoLibro();
            modelo.addRow(fila); // Agregar fila al modelo de la tabla
        }

        return modelo; // Retornar el modelo para usarlo en la GUI
    }
}
