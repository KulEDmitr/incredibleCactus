package com.example.hw_3.loaders;

public class UniException extends Exception {
    public UniException(final String message) {
        super("An error occurred during the execution of the request:\n"
                + message
                + "\nPlease, repeat you request.");
    }
}