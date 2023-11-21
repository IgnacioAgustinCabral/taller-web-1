package com.tallerwebi.dominio.excepcion;

public class TokenInvalidoException extends RuntimeException{
    public  TokenInvalidoException(){
        super();
    }

    public TokenInvalidoException(String message){
        super(message);
    }

    public TokenInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
