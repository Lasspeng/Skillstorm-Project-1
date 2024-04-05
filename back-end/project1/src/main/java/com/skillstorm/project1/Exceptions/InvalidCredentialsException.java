package com.skillstorm.project1.Exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("An account with those credentials was not found");
    }
}
