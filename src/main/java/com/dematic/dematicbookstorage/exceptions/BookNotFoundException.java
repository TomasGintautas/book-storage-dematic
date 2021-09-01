package com.dematic.dematicbookstorage.exceptions;

public class BookNotFoundException extends RuntimeException{

    private final String field;

    public BookNotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
