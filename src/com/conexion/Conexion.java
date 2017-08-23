/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import com.exception.ExcpcionSIDOracle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jorge Silva Borda
 */
public interface Conexion {

    void abrirConexion() throws ExcpcionSIDOracle, ClassNotFoundException, SQLException;

    void cerrarConexion() throws SQLException;

    void ejecutar(String query) throws SQLException;

    ResultSet ejecutarQuery(String query) throws SQLException;

    String getBaseDatos();

    Connection getConn();

    String getPassword();

    int getPuerto();

    //<editor-fold defaultstate="collapsed" desc="Accesores">
    String getServidor();

    String getUsuario();

    void setBaseDatos(String baseDatos);

    void setConn(Connection conn);
    
    void setPassword(String password);

    void setPuerto(int puerto);

    void setServidor(String servidor);

    void setUsuario(String usuario);
    //</editor-fold>
}
