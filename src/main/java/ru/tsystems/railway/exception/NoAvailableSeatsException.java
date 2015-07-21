package ru.tsystems.railway.exception;


public class NoAvailableSeatsException extends Exception {

    public NoAvailableSeatsException() {
    }

    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
