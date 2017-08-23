package com.exception;

/**
 * @author Jorge Silva Borda
 */
public class ExcpcionSIDOracle extends Exception {

    public ExcpcionSIDOracle() {
    }

    public ExcpcionSIDOracle(String msg) {
        super(msg);
    }

    public ExcpcionSIDOracle(Throwable causa) {
        super(causa);
    }

    public ExcpcionSIDOracle(String msg, Throwable causa) {
        super(msg, causa);
    }
}
