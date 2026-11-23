package com.dyma.Tennis.services;

public class PlayerAlreadyExistsException extends RuntimeException {
  public PlayerAlreadyExistsException(String lastName) {
    super("Player with the last name " + lastName + " already exists." );
  }
}
