package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcepcionServidor extends Exception {

    public ExcepcionServidor() {
    }

    public ExcepcionServidor(String msg) {
        super(msg);
    }

    public ExcepcionServidor(Throwable causa) {
        super(causa);
    }

    public ExcepcionServidor(String msg, Throwable causa) {
        super(msg, causa);
    }
}
