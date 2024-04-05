package com.skillstorm.project1.Exceptions;

public class CapacityExceededException extends Exception {
    public CapacityExceededException() {
        super("This warehouse is at capacity. New inventory could not be added");
    }
}
