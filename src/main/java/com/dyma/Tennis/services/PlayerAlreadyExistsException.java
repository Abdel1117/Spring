package com.dyma.Tennis.services;

public class PlayerAlreadyExistsException extends RuntimeException {
  public PlayerAlreadyExistsException(String lastName) {
    super("Playter with the last name " + lastName + " already exists." );
  }
}
