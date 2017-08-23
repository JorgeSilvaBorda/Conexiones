/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.exception.ExcepcionConexionNula;
import com.exception.ExcepcionPassword;
import com.exception.ExcepcionServidor;
import com.exception.ExcepcionUsuario;
import java.sql.Connection;

/**
 *
 * @author Jorge Silva Borda
 */
public interface DatosConexion {
    
    int ORACLE = 0;
    int SQLSERVER = 1;
    int MYSQL = 2;
    
    String getBasedato();

    Connection getCon();

    /**
     * Generar conexión MySql
     * @param tipo {@link Integer}. Debe ser valores entre 0 y 2 inclusive, donde:
     * 0 = Oracle.
     * 1 = Sql Server.
     * 2 = MySql.
     * @return {@link com.Conexion}
     * @throws com.exception.ExcepcionConexionNula
     * @throws com.exception.ExcepcionServidor
     * @throws com.exception.ExcepcionUsuario
     * @throws com.exception.ExcepcionPassword
     */
    Conexion GetConexion(int tipo) throws ExcepcionConexionNula, ExcepcionServidor, ExcepcionUsuario, ExcepcionPassword;

    String getPassword();

    //<editor-fold defaultstate="collapsed" desc="Accesores">
    String getServidor();

    String getUsuario();

    void probarConexion();

    void setBasedato(String basedato);

    void setCon(Connection con);
    
    void setPassword(String password);

    void setServidor(String servidor);

    void setUsuario(String usuario);
    //</editor-fold>
}