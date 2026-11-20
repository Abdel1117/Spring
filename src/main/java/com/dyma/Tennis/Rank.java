package com.dyma.Tennis;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(@Positive(message = "Veuillez mettre une position au dessus de 0 ") int position,
        @PositiveOrZero(message = "Veuillez mettre un nombre positif") int points) {}
