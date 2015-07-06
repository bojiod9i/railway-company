package ru.tsystems.railway.exception;


public class NoAvaliableSeatsException extends Exception {

    public NoAvaliableSeatsException() {
    }

    public NoAvaliableSeatsException(String message) {
        super(message);
    }
}
