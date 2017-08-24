package com.tabla;

import java.util.LinkedList;

/**
 * @author Jorge Silva Borda
 */
public class Fila {
    private final LinkedList<String> datos;
    
    public Fila(){
	this.datos = new LinkedList();
    }
    
    public LinkedList<String> getDatos(){
	return datos;
    }
    
    public void addDato(String dato){
	datos.add(dato);
    }
}
