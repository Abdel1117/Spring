package com.dyma.Tennis.web;

import org.springframework.web.bind.annotation.RestController;

import com.dyma.Tennis.Player;
import com.dyma.Tennis.PlayerList;
import com.dyma.Tennis.PlayerToSave;
import com.dyma.Tennis.PlayerToSave;
import com.dyma.Tennis.services.PlayerService;

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

import org.springframework.beans.factory.annotation.Autowired;
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

        @Autowired
        private PlayerService playerService;

        @Operation(summary = "Finds players", description = "Finds players")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Players list", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Player.class))) }) })

        @GetMapping
        public List<Player> list() {
                return playerService.getAllPlayers();
        }

        @Operation(summary = "Finds a player", description = "Finds a player")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Player", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }),
                        @ApiResponse(responseCode = "404", description = "A player with the specified last name was not found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })
        })
        @GetMapping("{lastName}")
        public Player getByLastName(@PathVariable("lastName") String lastName) {
                return playerService.getByLastName(lastName);
        }

    @Operation(summary = "Creates a player", description = "Creates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToSave.class))}),
            @ApiResponse(responseCode = "400", description = "Player with specified last name already exists.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})

    })
    @PostMapping
    public Player createPlayer(@RequestBody @Valid PlayerToSave playerToSave) {
        return playerService.create(playerToSave);
    }

        @Operation(summary = "Update a player", description = "Update a player")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated  Player", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })
        @ApiResponse(responseCode = "404", description = "A player with the specified last name was not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })
        @PutMapping
        public Player updatePlayer(@RequestBody @Valid PlayerToSave playerToSave) {
                return playerService.update(playerToSave);
        }

        @Operation(summary = "Delete a player", description = "Delete a player")
        @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "PLayer has been deleted", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }) })
        @ApiResponse(responseCode = "404", description = "A player with the specified last name was not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })

        @DeleteMapping("{lastName}")
        public void deletePlayerByLastName(@PathVariable("lastName") String lastName) {

                playerService.delete(lastName);
        }
}
