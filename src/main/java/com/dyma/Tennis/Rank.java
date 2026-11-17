package com.dyma.Tennis;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(@Positive int position, @PositiveOrZero int points) {
}
