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
class CompararPorCategoriaDescendente implements Comparator<Libro> {
    @Override
    public int compare(Libro libro1, Libro libro2) {
        return libro2.getCategoria().compareToIgnoreCase(libro1.getCategoria());  // Orden descendente
    }
}