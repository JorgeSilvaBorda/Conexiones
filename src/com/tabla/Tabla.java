package com.tabla;

import java.util.LinkedList;

/**
 * @author Jorge Silva Borda
 */
public class Tabla {
    private final String nombre;
    private final LinkedList<String> columnas;
    private final LinkedList<Fila> filas;
    
    public Tabla(String nombre){
	this.nombre = nombre;
	columnas = new LinkedList();
	filas = new LinkedList();
    }
    
    public void addColumna(String columna){
	columnas.add(columna);
    }
    
    public void addFila(Fila fila){
	this.filas.add(fila);
    }
    
    public LinkedList<Fila> getFilas(){
	return filas;
    }
    
    public LinkedList<String> getColumnas(){
	return columnas;
    }
}
