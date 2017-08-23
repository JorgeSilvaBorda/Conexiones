package com;

import com.conexion.mysql.ConexionMySql;
import com.conexion.oracle.ConexionOracle;
import com.conexion.sqlserver.ConexionSqlServer;
import com.exception.ExcepcionConexionNula;
import com.exception.ExcepcionPassword;
import com.exception.ExcepcionServidor;
import com.exception.ExcepcionUsuario;
import com.exception.ExcpcionSIDOracle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase principal manejadora de las conexiones de acceso a las bases de datos.
 *
 * @author Jorge Silva Borda
 */
public class Conexion implements DatosConexion {

    private String servidor, usuario, password, basedato;
    private int puerto;
    private Connection con;
    private int TIPO;
    private Object CONEXIONACTUAL;

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Conexion() {

    }

    public Conexion(Connection conn) {
	this.con = conn;
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

    public Conexion(String servidor, String usuario, String password, String basedato, Connection conn) {
	this.servidor = servidor;
	this.usuario = usuario;
	this.password = password;
	this.basedato = basedato;
	this.con = conn;
    }
//</editor-fold>

    @Override
    public void activarConexion(int tipo) throws ExcepcionConexionNula, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ExcpcionSIDOracle {
	this.TIPO = tipo;
	if (validar()) {
	    switch (TIPO) {
		case ORACLE:
		    throw new ExcpcionSIDOracle("No se puede generar una conexión a Oracle Database si no se ha proporcionado el SID de la conexión.");
		case SQLSERVER:

		case MYSQL:
		default:
	    }
	} else {
	    System.out.println("Error al validar los datos de la conexión.");
	}
    }

    @Override
    public void activarConexion(int tipo, String SID) throws ExcepcionConexionNula, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword, ExcpcionSIDOracle {
	this.TIPO = tipo;
	if (validar()) {
	    switch (TIPO) {
		case ORACLE:
		    CONEXIONACTUAL = new ConexionOracle(getServidor(), getUsuario(), getBasedato(), getPassword(), getPuerto(), SID);
		case SQLSERVER:

		case MYSQL:

		default:

	    }
	} else {
	    System.out.println("Error al validar los datos de la conexión.");
	}
    }

    public void abrirConexion() throws ExcpcionSIDOracle, ClassNotFoundException, SQLException {
	if (CONEXIONACTUAL instanceof ConexionOracle) {
	    ((ConexionOracle) CONEXIONACTUAL).abrirConexion();
	} else if (CONEXIONACTUAL instanceof ConexionSqlServer) {

	} else if (CONEXIONACTUAL instanceof ConexionMySql) {

	}
    }

    public ResultSet ejecutarQuery(String query) throws SQLException {
	if (CONEXIONACTUAL instanceof ConexionOracle) {
	    return ((ConexionOracle) CONEXIONACTUAL).ejecutarQuery(query);
	} else if (CONEXIONACTUAL instanceof ConexionSqlServer) {

	} else if (CONEXIONACTUAL instanceof ConexionMySql) {

	}
	return null;
    }

    public ResultSet ejecutar(String query) throws SQLException {
	if (CONEXIONACTUAL instanceof ConexionOracle) {
	    ((ConexionOracle) CONEXIONACTUAL).ejecutar(query);
	} else if (CONEXIONACTUAL instanceof ConexionSqlServer) {

	} else if (CONEXIONACTUAL instanceof ConexionMySql) {

	}
	return null;
    }

    public void cerrar() throws SQLException {
	if (CONEXIONACTUAL instanceof ConexionOracle) {
	    ((ConexionOracle) CONEXIONACTUAL).cerrarConexion();
	} else if (CONEXIONACTUAL instanceof ConexionSqlServer) {

	} else if (CONEXIONACTUAL instanceof ConexionMySql) {

	}
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

    @Override
    public void setPuerto(int puerto) {
	this.puerto = puerto;
    }

    @Override
    public int getPuerto() {
	return puerto;
    }
//</editor-fold>
}
