/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tp;
import pojos.Equipo;


/**
 *
 * @author Sebas
 */
public class Tp {
     public static void main(String[] args) {       

         Equipo a = new Equipo();
         Equipo b = new Equipo();
         Equipo river = a.buscarEquipoPorNombre("River");
         Equipo racing = b.buscarEquipoPorNombre("Racing");
         river.jugarPartido(river.getNombre(), 1, 1);         
         racing.jugarPartido(racing.getNombre(), 1, 1);
         Equipo nuevo = new Equipo();
         nuevo.agregarEquipo("Independiente", 11, 5, "Tevez");
    }
}
