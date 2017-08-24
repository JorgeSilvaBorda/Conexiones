package com.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * @author Jorge Silva Borda
 */
public final class Tabla {
    private String nombre;
    private final LinkedList<String> columnas;
    private final LinkedList<Fila> filas;
    
    /**
     * Constructor.
     * Construye un objeto {@code Tabla} solo con su nombre.
     * @param nombre {@code String}. Con el nombre de la tabla.
     */
    public Tabla(String nombre){
	this.nombre = nombre;
	columnas = new LinkedList();
	filas = new LinkedList();
    }
    
    /**
     * Constructor vacío.
     * No recomendado.
     */
    public Tabla(){
	columnas = new LinkedList();
	filas = new LinkedList();
    }
    
    /**
     * Constructor en base a resultados de una consulta.
     * La tabla se autoconstruye a partir de los resultados de la consulta {@link ResultSet}.
     * @param rs {@code ResultSet}, con los datos de una consulta.
     * @throws SQLException 
     */
    public Tabla(ResultSet rs) throws SQLException{
	columnas = new LinkedList();
	filas = new LinkedList();
	for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
	    addColumna(rs.getMetaData().getColumnName(i));
	}
	while(rs.next()){
	    Fila fila = new Fila();
	    for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
		Object dato = rs.getObject(i);
		fila.addDato(dato);
	    }
	    addFila(fila);
	}
    }
    
    /**
     * Imprime las columnas almacenadas en la tabla.
     */
    public void imprimirColumnas(){
	columnas.forEach((columna) -> {
	    System.out.print("[" + columna + "]");
	});
    }
    
    /**
     * Imprime todas las filas almacenadas en la tabla.
     */
    public void imprimirFilas(){
	for(Fila fila : filas){
	    fila.getDatos().stream().map((dato) -> {
		System.out.print(dato);
		return dato;
	    }).forEachOrdered((_item) -> {
		System.out.println("\t");
	    });
	    System.out.println("");
	}
    }
    
    /**
     * Método para agregar columnas a la tabla.
     * @param columna {@code String}, con el nombre de la columna a agregar.
     */
    public void addColumna(String columna){
	columnas.add(columna);
    }
    
    /**
     * Agrega una fila de datos a la tabla.
     * @param fila {@link com.tabla.Fila}, con los datos dentro.
     */
    public void addFila(Fila fila){
	this.filas.add(fila);
    }
    
    /**
     * Obtiene todas las filas de la tabla en un LinkedList.
     * @return {@code LinkedList}, con las filas de la tabla.
     * @see LinkedList.
     * @see Fila.
     */
    public LinkedList<Fila> getFilas(){
	return filas;
    }
    
    /**
     * Obtiene un LinkedList con las columnas de la tabla.
     * @return {@code LinkedList<String>} con las columnas.
     * @see LinkedList.
     */
    public LinkedList<String> getColumnas(){
	return columnas;
    }
    
    /**
     * Devuelve el nombre de la tabla.
     * @return {@code String} con el nombre asignado a la tabla (Si es que tuviera).
     */
    public String getNombre(){
	return nombre;
    }
    
    /**
     * Método para ajustar el nombre de la tabla.
     * @param nombre {@code String} con el nombre a poner a la tabla.
     */
    public void setNombre(String nombre){
	this.nombre = nombre;
    }
    
    /**
     * Método para formatear los datos obtenidos de una columna que se encuentre con tipo de dato TimeStamp.
     * Se devuelve como {@code String}.
     * @param dato {@code Object} con el dato a transformar desde TimeStamp a String.
     * @return {@code String} con el dato convertido a formato: 'yyyy-MM-dd HH:mm:ss'
     * @see TimeStamp
     */
    public String formatTimeStamp(Object dato){
	String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((java.sql.Timestamp)dato);
	return time;
    }
    
    /**
     * Método para formatear los datos obtenidos de una columna que se encuentre con tipo de dato BigDecimal.
     * Se devuelve como {@code String}.
     * @param dato {@code Object} con el dato a transformar desde BigDecimal a String.
     * @return {@code String} con el dato convertido a String.
     * @see BigDecimal
     */
    public String formatBigDecimal(Object dato){
	java.math.BigDecimal bigDecimal = (java.math.BigDecimal)dato;
	return bigDecimal.toString();
    }
    
    /**
     * Imprime de forma tabulada todos los datos que se encuentren dentro de la tabla.
     * Primero se imprimen todos los encabezados para luego continuar con el resto de la información contenida en la tabla.
     * @return {@code String} con todos los datos de la tabla.
     */
    @Override
    public String toString(){
	String salida = "";
	for(Object columna : columnas){
	    salida = salida + "[" + (String)columna + "]";
	}
	salida = salida + System.getProperty("line.separator");
	
	for(Fila fila : filas){
	    for(Object dato : fila.getDatos()){
		if(dato instanceof java.math.BigDecimal){
		    salida = salida + formatBigDecimal(dato)  + "\t";
		}else if(dato instanceof java.sql.Timestamp){
		    salida = salida + formatTimeStamp(dato)  + "\t";
		}else{
		    salida = salida + (String) dato + "\t";
		}
	    }
	    salida = salida + System.getProperty("line.separator");
	}
	return salida;
    }
}
