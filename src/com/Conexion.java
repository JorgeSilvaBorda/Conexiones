package com;

import com.exception.ExcepcionConexionNula;
import com.exception.ExcepcionPassword;
import com.exception.ExcepcionServidor;
import com.exception.ExcepcionUsuario;
import java.sql.Connection;

/**
 * Clase principal manejadora de las conexiones de acceso a las bases de datos.
 *
 * @author Jorge Silva Borda
 */
public class Conexion implements DatosConexion {

    private String servidor, usuario, password, basedato;
    private Connection con;
    private int TIPO;

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Conexion() {

    }

    /**
     * Constructor con todos los datos para la conexión
     *
     * @param servidor
     * @param usuario
     * @param password
     * @param basedato
     */
    public Conexion(String servidor, String usuario, String password, String basedato) {
	this.servidor = servidor;
	this.usuario = usuario;
	this.password = password;
	this.basedato = basedato;
    }
//</editor-fold>

    @Override
    public Conexion GetConexion(int tipo) throws ExcepcionConexionNula, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword {
	if (validar()) {
	    switch (TIPO) {
		case ORACLE: return getOracle();
		case SQLSERVER: return getSqlServer();
		case MYSQL: return getMySql();
		default:
		    return null;
	    }
	}else{
	    System.out.println("Error al validar los datos de la conexión.");
	}
	return null;
    }
    
    private Conexion getOracle(){
	return null;
    }
    
    private Conexion getSqlServer(){
	return null;
    }
    
    private Conexion getMySql(){
	return null;
    }

    private boolean validar() throws ExcepcionConexionNula, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword {
	if (this.con == null) {
	    throw new ExcepcionConexionNula("No se ha inicializado la variable de la conexión [Valor null].", new NullPointerException());
	} else if (servidor.equals("")) {
	    throw new ExcepcionServidor("No se ha definido el servidor de destino.");
	} else if (servidor == null) {
	    throw new ExcepcionServidor("No se ha inicializado el servidor de destino.", new NullPointerException());
	} else if (usuario.equals("")) {
	    throw new ExcepcionUsuario("No se ha definido el usuario de destino.");
	} else if (usuario == null) {
	    throw new ExcepcionUsuario("No se ha inicializado el usuario de destino.", new NullPointerException());
	} else if (password == null) {
	    throw new ExcepcionPassword("No se ha inicializado el password  de la base de datos.", new NullPointerException());
	}
	return true;
    }

    @Override
    public void probarConexion() {

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
    public String getPassword() {
	return password;
    }

    @Override
    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String getBasedato() {
	return basedato;
    }

    @Override
    public void setBasedato(String basedato) {
	this.basedato = basedato;
    }

    @Override
    public Connection getCon() {
	return con;
    }

    @Override
    public void setCon(Connection con) {
	this.con = con;
    }
//</editor-fold>
}
