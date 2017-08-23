package com.conexion.oracle;

import com.conexion.Conexion;
import com.exception.ExcpcionSIDOracle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jorge Silva Borda
 */
public class ConexionOracle implements Conexion {

    private String servidor, usuario, baseDatos, password, sid;
    private int puerto;
    private Connection conn;

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ConexionOracle() {
	
    }
    
    public ConexionOracle(String servidor, String usuario, String baseDatos, String password, int puerto, String sid) {
	this.servidor = servidor;
	this.usuario = usuario;
	this.baseDatos = baseDatos;
	this.password = password;
	this.puerto = puerto;
	this.sid = sid;
    }
//</editor-fold>

    @Override
    public void abrirConexion() throws ExcpcionSIDOracle, ClassNotFoundException, SQLException {
	if (sid.equals("")) {
	    throw new ExcpcionSIDOracle("No se ha asignado el SID de la conexión de Oracle.");
	} else {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    conn = DriverManager.getConnection("jdbc:oracle:thin:@" + getServidor() + ":" + Integer.toString(getPuerto()) + ":" + getSid(), getUsuario(), getPassword());
	}
    }
    
    @Override
    public ResultSet ejecutarQuery(String query) throws SQLException{
	return conn.createStatement().executeQuery(query);
    }
    
    @Override
    public void ejecutar(String query) throws SQLException{
	conn.createStatement().executeQuery(query);
    }
    
    @Override
    public void cerrarConexion() throws SQLException{
	if(!this.conn.isClosed()){
	    conn.close();
	}
    }

    //<editor-fold defaultstate="collapsed" desc="Accesores">
    @Override
    public String getServidor() {
	return servidor;
    }

    @Override
    public void setServidor(String servidor) {
	this.servidor = servidor;
    }

    @Override
    public String getUsuario() {
	return usuario;
    }

    @Override
    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    @Override
    public String getBaseDatos() {
	return baseDatos;
    }

    @Override
    public void setBaseDatos(String baseDatos) {
	this.baseDatos = baseDatos;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public void setPassword(String password) {
	this.password = password;
    }
    
    @Override
    public int getPuerto() {
	return puerto;
    }

    @Override
    public void setPuerto(int puerto) {
	this.puerto = puerto;
    }

    @Override
    public Connection getConn() {
	return conn;
    }

    @Override
    public void setConn(Connection conn) {
	this.conn = conn;
    }
    
    public String getSid() {
	return sid;
    }

    public void setSid(String sid) {
	this.sid = sid;
    }
    
//</editor-fold>
}
