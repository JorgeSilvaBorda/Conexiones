package com;

import com.conexion.oracle.ConexionOracle;
import com.conexion.sqlserver.ConexionSqlServer;
import com.exception.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase principal manejadora de las conexiones de acceso a las bases de datos.
 *
 * @author Jorge Silva Borda
 */
public class Conexion {

    private final String SERVIDOR, USUARIO, PASSWORD, BASEDATOS;
    private String SID;
    private int PUERTO;

    private ConexionOracle oracle;
    private ConexionSqlServer sqlServer;

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    
    /**
     * Constructor útil tanto para MySql como para SQL Server.
     * No se especifica puerto en este caso, puesto que se permite que la conexión tome el puerto por defecto del motor de base de datos.
     * dentro de sus parámetros de inicialización.
     * @param SERVIDOR {@link java.lang.String}. Con el nombre o IP de la máquina de destino de la conexión.
     * @param USUARIO {@link java.lang.String}. Con el nombre de usuario de la base de datos para conectar.
     * @param PASSWORD {@link java.lang.String}. Contraseña del usuario de la base de datos.
     * @param BASEDATOS {@link java.lang.String}. Nombre de la base de datos.
     */
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
    }

    /**
     * Constructor útil tanto para MySql como para SQL Server.
     * dentro de sus parámetros de inicialización.
     * @param SERVIDOR {@link java.lang.String}. Con el nombre o IP de la máquina de destino de la conexión.
     * @param USUARIO {@link java.lang.String}. Con el nombre de usuario de la base de datos para conectar.
     * @param PASSWORD {@link java.lang.String}. Contraseña del usuario de la base de datos.
     * @param BASEDATOS {@link java.lang.String}. Nombre de la base de datos.
     * @param PUERTO {@link Integer}. Puerto de escucha del motor de base de datos.
     */
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.PUERTO = PUERTO;
    }

    /**
     * Constructor exclusivo para conexiones Oracle, puesto que cuenta con SID
     * dentro de sus parámetros de inicialización.
     * @param SERVIDOR {@link java.lang.String}. Con el nombre o IP de la máquina de destino de la conexión.
     * @param USUARIO {@link java.lang.String}. Con el nombre de usuario de la base de datos para conectar.
     * @param PASSWORD {@link java.lang.String}. Contraseña del usuario de la base de datos.
     * @param BASEDATOS {@link java.lang.String}. Nombre de la base de datos (Esquema en el caso de Oracle).
     * @param SID {@link java.lang.String}. SID de la instancia de Oracle.
     * @param PUERTO {@link Integer}. Puerto de escucha del motor de base de datos.
     */
    public Conexion(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, String SID, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.SID = SID;
	this.PUERTO = PUERTO;
    }
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos de Oracle">
    
    /**
     * Método que abre una conexión de tipo Oracle.
     * Posee una validación especial para el SID.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ExcepcionServidor
     * @throws ExcepcionUsuario
     * @throws ExcepcionPassword
     * @throws ExcepcionSIDOracle 
     * @see com.conexion.oracle.ConexionOracle
     */
    public void abrirConexionOracle() throws ClassNotFoundException, SQLException, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ExcepcionSIDOracle {
	if (validar()) {
	    if (SID.equals("")) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede estar vacío.");
	    }
	    if (SID == null) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede ser nulo.", new NullPointerException());
	    }
	    oracle = new ConexionOracle(SERVIDOR, USUARIO, PASSWORD, BASEDATOS, SID, PUERTO);
	    oracle.abrirConexion();
	}
	
    }
    
    /**
     * Método para ejecutar una instrucción SQL. No devuelve resultados.
     * @param query {@link java.lang.String}. Con la consulta que se desea enviar a ejecutar.
     * @throws SQLException
     * @throws ExcepcionServidor
     * @throws ExcepcionUsuario
     * @throws ExcepcionPassword
     * @throws ExcepcionSIDOracle
     */
    public void ejecutarOracle(String query) throws SQLException, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ExcepcionSIDOracle, ExcepcionSIDOracle, ExcepcionSIDOracle {
	if (validar()) {
	    if (SID.equals("")) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede estar vacío.");
	    }
	    if (SID == null) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede ser nulo.", new NullPointerException());
	    }
	    oracle.ejecutar(query);
	}
    }
    
    /**
     * Método para ejecutar una instrucción SQL y obtener su {@link ResultSet}.
     * @param query {@link java.lang.String}. Con la consulta que se desea hacer a la base de datos.
     * @return {@link ResultSet}. Con los resultados para poder recorrer.
     * @throws SQLException
     * @throws ExcepcionServidor
     * @throws ExcepcionUsuario
     * @throws ExcepcionPassword
     * @throws ExcepcionSIDOracle 
     * @see ResultSet
     */
    public ResultSet ejecutarQueryOracle(String query) throws SQLException, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ExcepcionSIDOracle {
	if (validar()) {
	    if (SID.equals("")) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede estar vacío.");
	    }
	    if (SID == null) {
		throw new ExcepcionSIDOracle("El SID de la conexión no puede ser nulo.", new NullPointerException());
	    }
	    return oracle.ejecutarQuery(query);
	}
	return null;
    }
    
    /**
     * Cierra la conexión Oracle para evitar rechazos por parte del motor.
     * @throws SQLException 
     */
    public void cerrarConexionOracle() throws SQLException{
	this.oracle.cerrar();
    }
    
    public void testQueryOracle(String query) throws SQLException{
	this.oracle.testQuery(query);
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos generales">
    
    /**
     * Validador general de parámetros comunes en las conexiones.
     * @return {@link boolean}. {@code true}, en caso de que todos los datos mínimos se encuentren presentes.
     * {@code Exception}, en caso de que falte algo.
     * @throws ExcepcionServidor
     * @throws ExcepcionUsuario
     * @throws ExcepcionPassword 
     */
    public boolean validar() throws ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword {
	if (SERVIDOR.equals("")) {
	    throw new ExcepcionServidor("El nombre del servidor de destino no puede ser vacío.");
	} else if (SERVIDOR == null) {
	    throw new ExcepcionServidor("El nombre del servidor de destino no puede ser nulo.", new NullPointerException());
	} else if (USUARIO.equals("")) {
	    throw new ExcepcionUsuario("El nombre de usuario no puede ser vacío.");
	} else if (USUARIO == null) {
	    throw new ExcepcionUsuario("El nombre de usuario no puede ser nulo.", new NullPointerException());
	} else if (PASSWORD.equals("")) {
	    throw new ExcepcionPassword("La contraseña no puede ser vacía.");
	} else if (PASSWORD == null) {
	    throw new ExcepcionPassword("La contraseña no puede ser nula.", new NullPointerException());
	}
	return true;
    }
    
//</editor-fold>

}
