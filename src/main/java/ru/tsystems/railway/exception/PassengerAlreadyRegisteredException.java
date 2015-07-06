package ru.tsystems.railway.exception;


public class PassengerAlreadyRegisteredException extends Exception {

    public PassengerAlreadyRegisteredException() {
    }

    public PassengerAlreadyRegisteredException(String message) {
        super(message);
    }
}
