package com.conexion.mysql;

import java.sql.Connection;


/**
 *
 * @author Jorge Silva Borda
 */
public class ConexionMySql{
    
    private String servidor, usuario, baseDatos, password;
    private int puerto;
    private Connection conn;

//<editor-fold defaultstate="collapsed" desc="Constructores">
    public ConexionMySql() {
    }
    
    public ConexionMySql(String servidor, String usuario, String baseDatos, String password, int puerto) {
	this.servidor = servidor;
	this.usuario = usuario;
	this.baseDatos = baseDatos;
	this.password = password;
	this.puerto = puerto;
    }
//</editor-fold>    
}
