package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcepcionConexionNula extends Throwable {

    public ExcepcionConexionNula() {

    }

    public ExcepcionConexionNula(String msg) {
        super(msg);
    }

    public ExcepcionConexionNula(Throwable causa) {
        super(causa);
    }

    public ExcepcionConexionNula(String msg, Throwable causa) {
        super(msg, causa);
    }
}
