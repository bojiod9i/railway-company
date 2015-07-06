package ru.tsystems.railway.exception;


public class OutOfDateException extends Exception {

    public OutOfDateException() {
    }

    public OutOfDateException(String message) {
        super(message);
    }
}
