package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcepcionUsuario extends Exception {

    public ExcepcionUsuario() {
    }

    public ExcepcionUsuario(String msg) {
        super(msg);
    }

    public ExcepcionUsuario(Throwable causa) {
        super(causa);
    }

    public ExcepcionUsuario(String msg, Throwable causa) {
        super(msg, causa);
    }
}
