/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

public class Seminario {
    
    private String nombre;
    public ArrayList <Alumno> asistentes;

    public Seminario(String nombre, ArrayList<Alumno> asistentes) {
        this.nombre = nombre;
        this.asistentes =new ArrayList <>();
    }

    public Seminario(String nombre) {
        this.nombre = nombre;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Alumno> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(ArrayList<Alumno> asistentes) {
        this.asistentes = asistentes;
    }
  
   // AgregarAlumno(): Alumno*

    
}
