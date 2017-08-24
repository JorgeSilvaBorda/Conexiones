package com;

import com.exception.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase principal manejadora de las conexiones de acceso a las bases de datos.
 * @author Jorge Silva Borda
 */
public class Conexion {
    private String SERVIDOR, USUARIO, PASSWORD, BASEDATOS, SID;
    private int PUERTO;
    private Connection connection;

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
    }
    
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.PUERTO = PUERTO;
    }
    
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, String SID, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.SID = SID;
	this.PUERTO = PUERTO;
    }
//</editor-fold>
    
    public void abrirConexionOracle() throws ExcpcionSIDOracle, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ClassNotFoundException, SQLException{
	if(SID.equals("")){
	    throw new ExcpcionSIDOracle("No se ha definido el SID de la conexión.");
	}else if(SID == null){
	    throw new ExcpcionSIDOracle("No se ha inicializado el SID de la conexión.", new NullPointerException());
	}else if(validar()){
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    this.connection = DriverManager.getConnection("jdbc:oracle:thin:@" + SERVIDOR + ":" + Integer.toString(PUERTO) + ":" + SID, USUARIO, PASSWORD);
	}
    }
    
    public boolean validar() throws ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword{
	if(SERVIDOR.equals("")){
	    throw new ExcepcionServidor("El nombre del servidor de destino no puede ser vacío.");
	}else if(SERVIDOR == null){
	    throw new ExcepcionServidor("El nombre del servidor de destino no puede ser nulo.", new NullPointerException());
	}else if(USUARIO.equals("")){
	    throw new ExcepcionUsuario("El nombre de usuario no puede ser vacío.");
	}else if(USUARIO == null){
	    throw new ExcepcionUsuario("El nombre de usuario no puede ser nulo.", new NullPointerException());
	}else if(PASSWORD.equals("")){
	    throw new ExcepcionPassword("La contraseña no puede ser vacía.");
	}else if(PASSWORD == null){
	    throw new ExcepcionPassword("La contraseña no puede ser nula.", new NullPointerException());
	}
	return true;
    }
    
    
}
