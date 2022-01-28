package com.etron.exceptions;


public class EmailIsTakenException extends RuntimeException {

    public EmailIsTakenException(String email) {
        super(EmailIsTakenException.generateMessage(email));
    }

    private static String generateMessage(String email) {
        return email + " is taken by another " ;
    }
}
