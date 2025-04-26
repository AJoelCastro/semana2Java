/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author artur
 */
public class HistorialPrestamos {
    private List<PrestamoBibliotecario> historial;

    public HistorialPrestamos() {
        historial = new ArrayList<>();
    }

    public void agregarPrestamo(PrestamoBibliotecario prestamo) {
        historial.add(prestamo);
    }

    public List<PrestamoBibliotecario> getHistorial() {
        return historial;
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay préstamos registrados en el historial.");
        } else {
            for (int i = 0; i < historial.size(); i++) {
                System.out.println("Préstamo #" + (i + 1));
                System.out.println(historial.get(i).toString());
                System.out.println("-----------------------------------");
            }
        }
    }
}
