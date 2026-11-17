package com.dyma.Tennis.web;

import org.springframework.web.bind.annotation.RestController;

import com.dyma.Tennis.Player;
import com.dyma.Tennis.PlayerList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Tennis Players API")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Operation(summary = "Finds players", description = "Finds players")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Players list", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Player.class))) }) })

    @GetMapping
    public List<Player> list() {
        return PlayerList.ALL;
    }

    @Operation(summary = "Finds a players ", description = "Finds a player ")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Player ", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })

    @GetMapping("{lastName}")
    public Player getByNamePlayer(@PathVariable("lastName") String lastName) {
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow();
    }

    @Operation(summary = "Create a player ", description = "Create a player ")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created  Player ", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })
    @PostMapping
    public Player createPlayer(@RequestBody @Valid Player player) {
        return player;
    }

    @Operation(summary = "Update a player", description = "Update a player")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated  Player", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })
    @PutMapping
    public Player updatePlayer(@RequestBody @Valid Player player) {
        return player;
    }

    @Operation(summary = "Delete a player", description = "Delete a player")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "PLayer has been deleted", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })
    @DeleteMapping("{lastName}")
    public void deletePlayerByLastName(@PathVariable("lastName") String lastName) {
    }
}
