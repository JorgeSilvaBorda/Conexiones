package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcepcionSIDOracle extends Exception {

    public ExcepcionSIDOracle() {
    }

    public ExcepcionSIDOracle(String msg) {
        super(msg);
    }

    public ExcepcionSIDOracle(Throwable causa) {
        super(causa);
    }

    public ExcepcionSIDOracle(String msg, Throwable causa) {
        super(msg, causa);
    }
}
