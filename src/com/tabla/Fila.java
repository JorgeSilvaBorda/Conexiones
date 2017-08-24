package com.tabla;

import java.util.LinkedList;

/**
 * @author Jorge Silva Borda
 */
public class Fila {
    private final LinkedList<Object> datos;
    
    /**
     * Constructor vacío
     */
    public Fila(){
	this.datos = new LinkedList();
    }
    
    /**
     * Método para obtener la lista de datos.
     * @return {@code LinkedList<Object>} con los datos dentro.
     */
    public LinkedList<Object> getDatos(){
	return datos;
    }
    
    /**
     * Agergar un dato al listado.
     * @param dato {@code Object} con el dato a agregar.
     * Debería generalmente recibir un objeto del tipo {@link String}.
     */
    public void addDato(Object dato){
	datos.add(dato);
    }
}
