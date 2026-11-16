package com.dyma.Tennis.web;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Tennis Players API")
@RestController
public class PlayerController {

    @Operation(summary = "Finds players", description = "Finds players")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Players list", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Player.class))) }) })

    @GetMapping("/players")
    public List<Player> list() {
        return Collections.emptyList();
    }

}
