/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.util.Comparator;

/**
 *
 * @author ArcosArce
 */
public class CompararPrestamoPorFecha implements Comparator<PrestamoBibliotecario> {

    @Override
    public int compare(PrestamoBibliotecario prestamo1, PrestamoBibliotecario prestamo2) {
        return prestamo1.getFechaPrestamo().compareTo(prestamo2.getFechaPrestamo());
    }
}