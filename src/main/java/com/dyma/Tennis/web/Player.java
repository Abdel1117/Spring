package com.dyma.Tennis.web;

import java.time.LocalDate;

import com.dyma.Tennis.Rank;

public record Player(String firstName, String lastName, LocalDate birthDate, Rank rank) {

}
