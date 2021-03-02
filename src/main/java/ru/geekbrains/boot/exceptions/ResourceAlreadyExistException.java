package ru.geekbrains.boot.exceptions;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException (String message) {
        super(message);
    }
}
