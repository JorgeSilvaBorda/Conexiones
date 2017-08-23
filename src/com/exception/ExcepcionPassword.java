package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcepcionPassword extends Exception {

    public ExcepcionPassword() {
    }

    public ExcepcionPassword(String msg) {
        super(msg);
    }

    public ExcepcionPassword(Throwable causa) {
        super(causa);
    }

    public ExcepcionPassword(String msg, Throwable causa) {
        super(msg, causa);
    }
}
