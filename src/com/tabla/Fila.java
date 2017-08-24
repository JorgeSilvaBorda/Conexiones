package com.tabla;

import java.util.LinkedList;

/**
 * @author Jorge Silva Borda
 */
public class Fila {
    private final LinkedList<Object> datos;
    
    public Fila(){
	this.datos = new LinkedList();
    }
    
    public LinkedList<Object> getDatos(){
	return datos;
    }
    
    public void addDato(Object dato){
	datos.add(dato);
    }
}
