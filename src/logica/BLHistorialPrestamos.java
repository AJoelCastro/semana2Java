/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.*;
import entidades.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Administrador
 */
public class BLHistorialPrestamos {

    private static TableColumn columna;
    private static DefaultTableModel modelo;

    public static String escribirPrestamos(int idPrestamo, String fechaPrevista, Libro libro, Usuario usuario) {
        String mensaje = null;
        PrestamoBibliotecario pb = null;
        if (idPrestamo > 0 && fechaPrevista.trim().length() > 0 && libro!= null && usuario!= null) {
            mensaje = DALHistorialPrestamos.escribirPrestamos(pb);
        } else {
            mensaje = "Datos no validos";
        }
        return mensaje;
    }

    public static String setPrestamos(int posicion, int idPrestamo, String fechaPrevista, Libro libro, Usuario usuario) {
        String mensaje;
        PrestamoBibliotecario pb;
        if (posicion >= 0 && posicion <= DALHistorialPrestamos.getNumeroRegistros()) {
            if (idPrestamo > 0 && fechaPrevista.trim().length() > 0 && libro!= null && usuario!= null) {
                pb = new PrestamoBibliotecario(idPrestamo, fechaPrevista,libro,usuario);
                mensaje = DALHistorialPrestamos.setPrestamos(posicion, pb);
            } else {
                mensaje = "Datos no validos";
            }
        } else {
            mensaje = "Numero de registro no es valido";
        }
        return mensaje;
    }

    public static PrestamoBibliotecario getEmpleado(int pos) {
        PrestamoBibliotecario pb = null;
        if (pos >= 0 && pos < DALHistorialPrestamos.getNumeroRegistros()) {
            pb = DALHistorialPrestamos.getPrestamoBibliotecario(pos);
        }
        return pb;
    }

    public static ArrayList<PrestamoBibliotecario> getContenido() {
        if (DALHistorialPrestamos.getNumeroRegistros() > 0) {
            return DALHistorialPrestamos.getContenido();
        }
        return null;
    }

    public static int getNumeroRegistros() {
        return DALHistorialPrestamos.getNumeroRegistros();
    }

    public DefaultTableModel getContenidoH() { 
        DALHistorialPrestamos Hp = new DALHistorialPrestamos();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {
            "Libro", "Autor", "Id Prestamo",
            "Usuario","Correo", "Telefono", "Fecha-Prestamo", "Fecha-Prevista"
        };
        modelo.setColumnIdentifiers(columnas);

        ArrayList<PrestamoBibliotecario> lista = Hp.leerIngresos();
        System.out.println("lista"+lista);
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
