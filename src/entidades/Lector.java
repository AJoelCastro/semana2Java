/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Asus
 */
public class Lector extends Persona {
    private String direccion;
    private boolean activo;  //control de acceso del usuario al sistemam por infracciones que pueda haber cometido(no devolucion/daño de libros)
    private int limitePrestamos; //limite de libros que puede ser prestados a la ves
    
    public Lector () {
        this("ND",false,0,"NN","NA",0,"NC","NT");
    }
    public Lector(String direccion, boolean activo, int limitePrestamos, String nombre, String apellido,int dni, String correo, String telefono) {
        super(nombre, apellido,dni, correo, telefono);
        this.direccion = direccion;
        this.activo = activo;
        this.limitePrestamos = limitePrestamos;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public int getTamaño() {
        return getApellido().length() * 2 + getNombre().length() * 2 + 4 + 4;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    @Override
    public String toString() {
        return super.toString() + "Lector{" + ", direccion=" + direccion + ", activo=" + activo + ", limitePrestamos=" + limitePrestamos + '}';
    }
    
}
