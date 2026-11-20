package com.dyma.Tennis;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record Player(@NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last Name is mandatory") String lastName,
        @NotNull(message = "Birth Date is mandatory") @PastOrPresent(message = "Veuillez mettre une date dans le passé") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
        @Valid Rank rank) {

}
