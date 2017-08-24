package com.conexion.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jorge Silva Borda
 */
public class ConexionOracle {

    private final String SERVIDOR, USUARIO, PASSWORD, BASEDATOS;
    private final String SID;
    private final int PUERTO;
    private Connection connection;

    public ConexionOracle(String SERVIDOR, String USUARIO, String PASSWORD, String BASEDATOS, String SID, int PUERTO) {
	this.SERVIDOR = SERVIDOR;
	this.USUARIO = USUARIO;
	this.PASSWORD = PASSWORD;
	this.BASEDATOS = BASEDATOS;
	this.SID = SID;
	this.PUERTO = PUERTO;
    }

    public void abrirConexionOracle() throws ClassNotFoundException, SQLException {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	this.connection = DriverManager.getConnection("jdbc:oracle:thin:@" + SERVIDOR + ":" + Integer.toString(PUERTO) + ":" + SID, USUARIO, PASSWORD);
    }
    
    public void cerrarConexionOracle() throws SQLException{
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
    
    public void ejecutar(String query) throws SQLException{
	Statement st = this.connection.createStatement();
	st.execute(query);
    }
    
    public ResultSet ejecutarQuery(String query) throws SQLException{
	Statement st = this.connection.createStatement();
	ResultSet rs = st.executeQuery(query);
	return rs;
    }

    public Connection getConnection() {
	return connection;
    }

    public void setConnection(Connection connection) {
	this.connection = connection;
    }

}
