package br.com.matheus.dsclients.services.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String msg){
        super(msg);
    }
}
