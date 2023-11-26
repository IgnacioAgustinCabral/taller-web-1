package com.tallerwebi.dominio.excepcion;

public class NullEmailValidoException extends Exception{
    public  NullEmailValidoException(){
        super();
    }

    public NullEmailValidoException(String message){
        super(message);
    }

    public NullEmailValidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
