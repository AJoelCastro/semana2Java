/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.PrestamoBibliotecario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author artur
 */
public class HistorialPrestamos {

    public ArrayList<PrestamoBibliotecario> leerIngresos() {
        ArrayList<PrestamoBibliotecario> historialPrestamos = new ArrayList<>();
        File f = new File("Prestamos bibliotecarios.txt");

        if (!f.exists()) 
            return historialPrestamos; // Si el archivo no existe, retorna lista vacía

        try (ObjectInputStream ingreso = new ObjectInputStream(new FileInputStream("Prestamos bibliotecarios.txt"))) {
            // Leer lista serializada desde el archivo
            historialPrestamos = (ArrayList<PrestamoBibliotecario>) ingreso.readObject();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error en la lectura: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Ha ocurrido un error en la lectura: " + e.getMessage());
        }

        return historialPrestamos; // Retornar lista de ingresos leída del archivo
    }

    public DefaultTableModel getContenido() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {
            "Titulo Libro", "ID Libro", "Autor", "Categoria",
            "Nombre Lector", "DNI", "Fecha-Prestamo", "Fecha-Prevista"
        };
        modelo.setColumnIdentifiers(columnas); // Establecer nombres de columnas

        ArrayList<PrestamoBibliotecario> lista = leerIngresos(); // Leer ingresos desde archivo
        for (PrestamoBibliotecario prestamo : lista) {
            Object[] fila = new Object[columnas.length];
            fila[0] = prestamo.getLibro().getTitulo();
            fila[1] = prestamo.getLibro().getId();
            fila[2] = prestamo.getLibro().getAutor();
            fila[3] = prestamo.getLibro().getCategoria();
            fila[4] = prestamo.getLector().getNombre();
            fila[5] = prestamo.getLector().getId();
            fila[6] = prestamo.getFechaPrestamo();
            fila[7] = prestamo.getFechaPrevista();
            modelo.addRow(fila); // Agregar fila al modelo de la tabla
        }

        return modelo; // Retornar el modelo para usarlo en la GUI
    }
}
