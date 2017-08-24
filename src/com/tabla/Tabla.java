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
    
    public Tabla(String nombre){
	this.nombre = nombre;
	columnas = new LinkedList();
	filas = new LinkedList();
    }
    
    public Tabla(){
	columnas = new LinkedList();
	filas = new LinkedList();
    }
    
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
    
    public void imprimirColumnas(){
	columnas.forEach((columna) -> {
	    System.out.print("[" + columna + "]");
	});
    }
    
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
    
    public String getNombre(){
	return nombre;
    }
    
    public void setNombre(String nombre){
	this.nombre = nombre;
    }
    
    public String formatTimeStamp(Object dato){
	String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((java.sql.Timestamp)dato);
	return time;
    }
    
    public String formatBigDecimal(Object dato){
	java.math.BigDecimal bigDecimal = (java.math.BigDecimal)dato;
	return bigDecimal.toString();
    }
    
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
		    salida = salida + formatBigDecimal(dato)  + "\t";;
		}else if(dato instanceof java.sql.Timestamp){
		    salida = salida + formatTimeStamp(dato)  + "\t";;
		}else{
		    salida = salida + (String) dato + "\t";
		}
	    }
	    salida = salida + System.getProperty("line.separator");
	}
	return salida;
    }
}
