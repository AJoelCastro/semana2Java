/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package datos;

import java.util.*;
import java.io.*;
import entidades.Lector;

/**
 *
 * @author Davo
 */
public class ListaLectores {

    private static RandomAccessFile flujo;
    private static final int TAMREG = 140;
    private static int numRegistros;
    private static final String N_ARCHIVO = "LECTORES.txt";

    private static String crearArchivo() {
        try {
            flujo = new RandomAccessFile(N_ARCHIVO, "rw");
            numRegistros = (int) Math.ceil((double) flujo.length() / (double) TAMREG);
        } catch (IOException ex) {
            return "Problema al crear el flujo: " + ex.getMessage();
        }
        return null;
    }

    public static String escribirLector(Lector lector) {
        String mensaje = "";
        try {
            crearArchivo();
            flujo.close();
            mensaje = setLector(numRegistros, lector);
            if (mensaje.compareTo("ok") == 0) {
                numRegistros++;
            }
        } catch (IOException ex) {
            mensaje = "Excepcion: " + ex.getMessage();
        } finally {
            return mensaje;
        }
    }

    public static String setLector(int posicion, Lector lector) {
        String mensaje = "";
        try {
            if (lector.getTamaño() + 4 > TAMREG) {
                mensaje = "Tamanio de registro es insuficiente";
            } else {
                crearArchivo();
                flujo.seek(posicion * TAMREG);
                flujo.writeUTF(lector.getNombre());
                flujo.writeUTF(lector.getApellido());
                flujo.writeInt(lector.getDni());
                flujo.writeUTF(lector.getDireccion());
                flujo.writeBoolean(lector.isActivo());
                flujo.writeInt(lector.getLimitePrestamos());
                flujo.writeUTF(lector.getCorreo());
                flujo.writeUTF(lector.getTelefono());
                mensaje = "Ok";
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

    public static Lector getLector(int pos) {
        String nombre, apellido, direccion, correo, telefono;
        boolean activo;
        int limitePrestamos,dni;
        Lector lector = null;
        try {
            crearArchivo();
            flujo.seek(pos * TAMREG);
            nombre = flujo.readUTF();
            apellido = flujo.readUTF();
            dni = flujo.readInt();
            direccion = flujo.readUTF();
            activo = flujo.readBoolean();
            limitePrestamos = flujo.readInt();
            correo = flujo.readUTF();
            telefono = flujo.readUTF();
            lector = new Lector(direccion, activo, limitePrestamos, nombre, apellido,dni, correo, telefono);
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        } finally {
            try {
                flujo.close();
            } catch (IOException ex) {
                System.out.println("El flujo ya estaba cerrado: " + ex.getMessage());
            }
        }
        return lector;
    }

    public static ArrayList<Lector> getContenido() {
        ArrayList<Lector> lista = new ArrayList<>();
        try {
            crearArchivo();
            flujo.close();
            for (int i = 0; i < numRegistros; i++) {
                lista.add(getLector(i));
            }
        } catch (IOException ex) {
            System.out.println("Problema de E/S: " + ex.getMessage());
        }
        return lista;
    }

    public static ArrayList<Lector> obtenerLectorPorNombre(String nombre) {
        ArrayList<Lector> resultado = new ArrayList<>();
        for (Lector lectores : getContenido()) {
            if (lectores.getNombre().equalsIgnoreCase(nombre)) {
                resultado.add(lectores); // Agregar a la lista si coincide el código
            }
        }
        return resultado; // Retorna los ingresos encontrados

    }

}