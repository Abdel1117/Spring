package com.dyma.Tennis;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String lastName) {
        super("Player with last name " + lastName + " could not be found.");
    }
}
