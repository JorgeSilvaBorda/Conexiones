package com.conexion.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jorge Silva Borda
 */
public class ConexionSqlServer {

    private final String SERVIDOR, USUARIO, PASSWORD, BASEDATOS;
    private final int PUERTO;
    private Connection connection;
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Constructor de ConexionOracle
     * @param SERVIDOR {@code String}. Con el nombre de servidor o IP de la base de datos.
     * @param USUARIO {@code String}. Con el usuario de la base de datos.
     * @param PASSWORD {@code String}. El password del usuario.
     * @param BASEDATOS {@code String}. El nombre de la base de datos de SQL Server.
     * @param PUERTO {@code int}. Con el puerto a utilizar para la conexión.
     */
    public ConexionSqlServer(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.PUERTO = PUERTO;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos de la clase">
    /**
     * Método para establecer la conexión y poder ejecutar instancias de ella.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void abrirConexion() throws ClassNotFoundException, SQLException {
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	this.connection = DriverManager.getConnection("jdbc:sqlserver://" + SERVIDOR + ":" + Integer.toString(PUERTO) + ";databaseName=" + BASEDATOS + ";user=" + USUARIO + ";password=" + PASSWORD);
    }
    
    /**
     * Cierra la conexión que se encuentra abierta para evitar encolamiento de peticiones.
     * @throws SQLException
     */
    public void cerrar() throws SQLException{
	try {
	    if(!this.connection.isClosed()){
		try{
		    this.connection.close();
		}catch (SQLException ex) {
		    throw ex;
		}
	    }
	} catch (SQLException ex) {
	    throw ex;
	}
    }
    
    /**
     * Elecuta una instrucción SQL.
     * @param query {@code String}. Con la instrucción SQL a ejecutar.
     * @throws SQLException
     */
    public void ejecutar(String query) throws SQLException{
	Statement st = this.connection.createStatement();
	st.execute(query);
    }
    
    /**
     * Ejecuta una query de consulta a la base de datos.
     * @param query {@code String}. Con la instrucción SQL a ejecutar.
     * @return {@link ResultSet}. Con la carga de datos obtenida.
     * @throws SQLException
     */
    public ResultSet ejecutarQuery(String query) throws SQLException{
	Statement st = this.connection.createStatement();
	ResultSet rs = st.executeQuery(query);
	return rs;
    }
    
    /**
     * Método de pruebas para ejecutar query e imprimir los resultados.
     * @param query {@code String}. Con la instrucción SQL a ejecutar.
     * @throws SQLException
     */
    public void testQuery(String query) throws SQLException{
	Statement st = this.connection.createStatement();
	ResultSet rs = st.executeQuery(query);
	int columnas = rs.getMetaData().getColumnCount();
	for(int i = 1; i <= columnas; i++){
	    System.out.print("[" + rs.getMetaData().getColumnName(i) + "]");
	}
	System.out.println("");
	while(rs.next()){
	    for(int i = 1; i <= columnas; i++){
		System.out.print("[" + rs.getObject(i) + "]");
	    }
	    System.out.println("");
	}
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesores">
    
    /**
     * Devuelve la conexión ({@link java.sql.Connection})
     * @return {@code java.sql.Connection}.
     */
    public Connection getConnection() {
	return connection;
    }
    
    /**
     * Establece la conexión ({@link java.sql.Connection})
     * @param connection {@code java.sql.Connnection}.
     */
    public void setConnection(Connection connection) {
	this.connection = connection;
    }
//</editor-fold>
}