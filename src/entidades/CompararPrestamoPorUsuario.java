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
public class CompararPrestamoPorUsuario implements Comparator<PrestamoBibliotecario> {
    @Override
    public int compare(PrestamoBibliotecario p1, PrestamoBibliotecario p2) {
        return p1.getLibro().getTitulo().compareTo(p2.getLibro().getTitulo());
    }
}