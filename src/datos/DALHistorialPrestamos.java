/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import entidades.*;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import logica.*;

/**
 *
 * @author Administrador
 */
public class DALHistorialPrestamos {

    private static RandomAccessFile flujo;
    private static final int TAMREG = 140;
    private static int numRegistros;
    private static final String N_ARCHIVO = "Historial Prestamos.txt";

    private static String crearArchivo() {
        try {
            flujo = new RandomAccessFile(N_ARCHIVO, "rw");
            numRegistros = (int) Math.ceil((double) flujo.length() / (double) TAMREG);
        } catch (IOException ex) {
            return "Problema al crear el flujo: " + ex.getMessage();
        }
        return null;
    } 

    public static String escribirPrestamos(PrestamoBibliotecario hp) {
        String mensaje = "";
        try {
            crearArchivo();
            flujo.close();
            mensaje = setPrestamos(numRegistros, hp);
            if (mensaje.compareTo("ok") == 0) {
                numRegistros++;
            }
        } catch (IOException ex) {
            mensaje = "Excepcion: " + ex.getMessage();
        } finally {
            return mensaje;
        }
    }
    
       public static ArrayList<PrestamoBibliotecario> leerIngresos() { 
        ArrayList<PrestamoBibliotecario> historialPrestamos = new ArrayList<>();
        File archivo = new File(N_ARCHIVO);
        if (!archivo.exists()){
            return historialPrestamos;
        }
            

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                
                String codigo = raf.readUTF();
                String titulo = raf.readUTF();
                String autor = raf.readUTF();
                String categoria = raf.readUTF();
                int anio = raf.readInt();
                int copias = raf.readInt();
                String editorial = raf.readUTF();
                
                int idPrestamo = raf.readInt();
                String fechaPrestamo = raf.readUTF();
                String fechaPrevista = raf.readUTF();
                
                String nombreUsuario = raf.readUTF();
                String apellidoUsuario = raf.readUTF();
                int dniUsuario = raf.readInt();
                String correoUsuario = raf.readUTF();
                String telefonoUsuario = raf.readUTF();
                String direccionUsuario = raf.readUTF();

                Libro libro = new Libro(codigo, titulo, autor, categoria, anio, copias, editorial);
                Usuario usuario = new Usuario(nombreUsuario,apellidoUsuario,dniUsuario,correoUsuario,telefonoUsuario,direccionUsuario);
                PrestamoBibliotecario prestamo = new PrestamoBibliotecario(idPrestamo, fechaPrevista, libro, usuario);
                historialPrestamos.add(prestamo);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
        return historialPrestamos;
    }

    public static String setPrestamos(int posicion, PrestamoBibliotecario hp) {
        String mensaje = "";
        try {
            if (hp.getTamaño() + 4 > TAMREG) {
                mensaje = "Tamanio de registro es insuficiente";
            } else {
                crearArchivo();
                flujo.seek(posicion * TAMREG);
                flujo.writeUTF(hp.getLibro().getCodigo() != null ? hp.getLibro().getCodigo() : "");
                flujo.writeUTF(hp.getLibro().getTitulo() != null ? hp.getLibro().getTitulo() : "");
                flujo.writeUTF(hp.getLibro().getAutor() != null ? hp.getLibro().getAutor() : "");
                flujo.writeUTF(hp.getLibro().getCategoria() != null ? hp.getLibro().getCategoria() : "");
                flujo.writeInt(hp.getLibro().getAnioPublicacion());
                flujo.writeInt(hp.getLibro().getCopiasDisponibles());
                flujo.writeUTF(hp.getLibro().getEditorial() != null ? hp.getLibro().getEditorial() : "");

                // Datos del préstamo
                flujo.writeInt(hp.getIdPrestamo());
                flujo.writeUTF(hp.getFechaPrestamo() != null ? hp.getFechaPrestamo() : "");
                flujo.writeUTF(hp.getFechaPrevista() != null ? hp.getFechaPrevista() : "");

                // Datos del lector
                flujo.writeUTF(hp.getUsuario().getNombre());
                flujo.writeUTF(hp.getUsuario().getApellido());
                flujo.writeInt(hp.getUsuario().getDni());
                flujo.writeUTF(hp.getUsuario().getCorreo());
                flujo.writeUTF(hp.getUsuario().getTelefono());
                flujo.writeUTF(hp.getUsuario().getDireccion());
                mensaje = "ok";
            }
        } catch (IOException ex) {
            mensaje = "Excepcion: " + ex.getMessage();
        } finally {
            try {
                flujo.close();
            } catch (IOException ex) {
                mensaje = "El flujo ya estaba cerrado: " + ex.getMessage();
            }
        }
        return mensaje;
    }

    public static PrestamoBibliotecario getPrestamoBibliotecario(int pos) {
        DALHistorialPrestamos Hp = new DALHistorialPrestamos();
        String nombreU, correo, telefono, titulo, estado, fechDev, fechPre, fechPres, autor;
        int idPrestamo, cantidad, dni;
        PrestamoBibliotecario empleado = null;
        try {
            crearArchivo();
            flujo.seek(pos * TAMREG);
            String codigo = flujo.readUTF();
            titulo = flujo.readUTF();
            autor = flujo.readUTF();
            String categoria = flujo.readUTF();
            int anio = flujo.readInt();
            int copias = flujo.readInt();
            String editorial = flujo.readUTF();

            // Leer datos del préstamo
            idPrestamo = flujo.readInt();
            String fechaPrestamo = flujo.readUTF();
            String fechaPrevista = flujo.readUTF();

            // Leer datos del lector
            String nombreUsuario = flujo.readUTF();
            String apellidoUsuario = flujo.readUTF();
            int dniUsuario = flujo.readInt();
            String correoUsuario = flujo.readUTF();
            String telefonoUsuario = flujo.readUTF();
            String direccionUsuario = flujo.readUTF();
            Libro libro = new Libro();
            libro = BLInventarioLibro.buscarPorCodigo(codigo);
            Usuario usuario = new Usuario();
            usuario = Hp.buscarUsuarioPorDni(dniUsuario);
            PrestamoBibliotecario Pb = new PrestamoBibliotecario(idPrestamo, fechaPrevista, libro, usuario);
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        } finally {
            try {
                flujo.close();
            } catch (IOException ex) {
                System.out.println("El flujo ya estaba cerrado: " + ex.getMessage());
            }
        }
        return empleado;
    }

    public static ArrayList<PrestamoBibliotecario> getContenido() { 
        ArrayList<PrestamoBibliotecario> Pb = new ArrayList<>();
        try {
            crearArchivo();
            flujo.close();
            for (int i = 0; i < numRegistros; i++) {
                Pb.add(getPrestamoBibliotecario(i));
            }
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        }
        return Pb;
    }

    public static int getNumeroRegistros() { 
        int numeroRegistros = -1;
        try {
            crearArchivo();
            numeroRegistros = numRegistros;
            flujo.close();
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        } finally {
            return numeroRegistros;
        }
    }
    
       public ArrayList<PrestamoBibliotecario> buscarPrestamoPorIdLector(int dni) {
        ArrayList<PrestamoBibliotecario> prestamos = new ArrayList<>();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(dni == prestamo.getUsuario().getDni()){
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
    
    public Usuario buscarUsuarioPorDni(int dni) {
        Usuario usuario = new Usuario();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(dni == prestamo.getUsuario().getDni()){
                usuario = prestamo.getUsuario();
            }
        }
        return usuario;
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
    
    public ArrayList<PrestamoBibliotecario> buscarPrestamoPorLibro(String nomLibro) {
        ArrayList<PrestamoBibliotecario> prestamos = new ArrayList<>();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            if(nomLibro.equals(prestamo.getLibro().getTitulo())){
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
    
    public ArrayList<PrestamoBibliotecario> buscarPrestamoPorIdLectorMultiple(int dni) {
        ArrayList<PrestamoBibliotecario> prestamos = new ArrayList<>();
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();

        for (PrestamoBibliotecario prestamo : lista) {
            if(dni == prestamo.getUsuario().getDni()) {
                prestamos.add(prestamo);
            }
        }

        return prestamos;
    }
    public static void aniadirPrestamo(PrestamoBibliotecario prestamo) {
        try (RandomAccessFile raf = new RandomAccessFile(N_ARCHIVO, "rw")) {
            raf.seek(raf.length());
            
            raf.writeUTF(prestamo.getLibro().getCodigo() != null ? prestamo.getLibro().getCodigo() : "");
            raf.writeUTF(prestamo.getLibro().getTitulo() != null ? prestamo.getLibro().getTitulo() : "");
            raf.writeUTF(prestamo.getLibro().getAutor() != null ? prestamo.getLibro().getAutor() : "");
            raf.writeUTF(prestamo.getLibro().getCategoria() != null ? prestamo.getLibro().getCategoria() : "");
            raf.writeInt(prestamo.getLibro().getAnioPublicacion());
            raf.writeInt(prestamo.getLibro().getCopiasDisponibles());
            raf.writeUTF(prestamo.getLibro().getEditorial() != null ? prestamo.getLibro().getEditorial() : "");
            
            raf.writeInt(prestamo.getIdPrestamo());
            raf.writeUTF(prestamo.getFechaPrestamo() != null ? prestamo.getFechaPrestamo() : "");
            raf.writeUTF(prestamo.getFechaPrevista() != null ? prestamo.getFechaPrevista() : "");
            
            raf.writeUTF(prestamo.getUsuario().getNombre());
            raf.writeUTF(prestamo.getUsuario().getApellido());
            raf.writeInt(prestamo.getUsuario().getDni());
            raf.writeUTF(prestamo.getUsuario().getCorreo());
            raf.writeUTF(prestamo.getUsuario().getTelefono());
            raf.writeUTF(prestamo.getUsuario().getDireccion());

        } catch (IOException e) {
            System.out.println("Error al añadir préstamo: " + e.getMessage());
        }
    }
    
    public static void eliminarPrestamo(int idPrestamo) {
        ArrayList<PrestamoBibliotecario> historialPrestamos = DALHistorialPrestamos.leerIngresos();
        boolean borrado = historialPrestamos.removeIf(p -> p.getIdPrestamo() == idPrestamo);
        if (borrado) {
            guardarHistorialCompleto(historialPrestamos);
        } else {
            System.out.println("No se encontró préstamo con ID " + idPrestamo);
        }
    }
    
    public void editarPrestamo(int idPrestamo, String newDatePrevista, Libro lib){
        PrestamoBibliotecario prest = buscarPrestamoTotal(idPrestamo);
        ArrayList<PrestamoBibliotecario> historialPrestamos = leerIngresos();

        for(PrestamoBibliotecario prestamo : historialPrestamos){
            if(prest.getIdPrestamo() == prestamo.getIdPrestamo()){
                prestamo.setFechaPrevista(newDatePrevista);
                prestamo.setLibro(lib);
            }
        }
        guardarHistorialCompleto(historialPrestamos);
    }

    public static void guardarHistorialCompleto(ArrayList<PrestamoBibliotecario> lista) {
        try (RandomAccessFile raf = new RandomAccessFile(N_ARCHIVO, "rw")) {
            raf.setLength(0);

            for (PrestamoBibliotecario prestamo : lista) {
                raf.writeUTF(prestamo.getLibro().getCodigo() != null ? prestamo.getLibro().getCodigo() : "");
                raf.writeUTF(prestamo.getLibro().getTitulo() != null ? prestamo.getLibro().getTitulo() : "");
                raf.writeUTF(prestamo.getLibro().getAutor() != null ? prestamo.getLibro().getAutor() : "");
                raf.writeUTF(prestamo.getLibro().getCategoria() != null ? prestamo.getLibro().getCategoria() : "");
                raf.writeInt(prestamo.getLibro().getAnioPublicacion());
                raf.writeInt(prestamo.getLibro().getCopiasDisponibles());
                raf.writeUTF(prestamo.getLibro().getEditorial() != null ? prestamo.getLibro().getEditorial() : "");
                
                raf.writeInt(prestamo.getIdPrestamo());
                raf.writeUTF(prestamo.getFechaPrestamo() != null ? prestamo.getFechaPrestamo() : "");
                raf.writeUTF(prestamo.getFechaPrevista() != null ? prestamo.getFechaPrevista() : "");
                
                raf.writeUTF(prestamo.getUsuario().getNombre());
                raf.writeUTF(prestamo.getUsuario().getApellido());
                raf.writeInt(prestamo.getUsuario().getDni());
                raf.writeUTF(prestamo.getUsuario().getCorreo());
                raf.writeUTF(prestamo.getUsuario().getTelefono());
                raf.writeUTF(prestamo.getUsuario().getDireccion());
                
            }
        } catch (IOException e) {
            System.out.println("Error al guardar historial completo: " + e.getMessage());
        }
    }
    public static DefaultTableModel getContenidoH() { 
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {
            "Libro", "Autor", "Id Prestamo",
            "Usuario","Correo", "Telefono", "Fecha-Prestamo", "Fecha-Prevista"
        };
        modelo.setColumnIdentifiers(columnas);
        ArrayList<PrestamoBibliotecario> lista = leerIngresos();
        for (PrestamoBibliotecario prestamo : lista) {
            Object[] fila = new Object[columnas.length];
            fila[0] = prestamo.getLibro().getTitulo();
            fila[1] = prestamo.getLibro().getAutor();
            fila[2] = prestamo.getIdPrestamo();
            fila[3] = prestamo.getUsuario().getNombre();
            fila[4] = prestamo.getUsuario().getCorreo();
            fila[5] = prestamo.getUsuario().getTelefono();
            fila[6] = prestamo.getFechaPrestamo();
            fila[7] = prestamo.getFechaPrevista();
            modelo.addRow(fila);
        }

        return modelo;
    }
}
