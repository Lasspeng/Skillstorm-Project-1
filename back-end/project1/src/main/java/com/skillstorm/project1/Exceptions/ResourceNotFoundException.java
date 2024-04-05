package com.skillstorm.project1.Exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String resource) {
        super(resource + " was not found");
    }
}
