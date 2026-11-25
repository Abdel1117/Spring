package com.dyma.Tennis.service;

import com.dyma.Tennis.PlayerNotFoundException;
import com.dyma.Tennis.data.PlayerRepository;
import com.dyma.Tennis.Player;
import com.dyma.Tennis.data.PlayerEntityList;
import com.dyma.Tennis.services.PlayerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    public void souldReturnPlayersRanking(){
        // Given
        Mockito.when(playerRepository.findAll()).thenReturn(PlayerEntityList.ALL);
        // When
        List<Player> allPlayers = playerService.getAllPlayers();

        // Then
        Assertions.assertThat(allPlayers)
                .extracting("lastName")
                .containsExactly("Nadal", "Djokovic", "Federer", "Murray");

    }

    @Test
    public void shouldRetrievePlayer(){
        // Given
        String playerToRetrieve = "nadal";
        Mockito.when(playerRepository.findOneByLastNameIgnoreCase(playerToRetrieve)).thenReturn(Optional.of(PlayerEntityList.RAFAEL_NADAL));
        //When
        Player retrievePlayer = playerService.getByLastName(playerToRetrieve);

        // Then
        Assertions.assertThat(retrievePlayer.lastName()).isEqualTo("Nadal");
        Assertions.assertThat(retrievePlayer.firstName()).isEqualTo("Rafael");
        Assertions.assertThat(retrievePlayer.rank().position()).isEqualTo(1);
    }

    @Test
    public void shoudlFailToRetrievePlayer_WhenPlayerDoesNotExist(){
        // Given
        String unknowPlayer = "doe";
        Mockito.when(playerRepository.findOneByLastNameIgnoreCase(unknowPlayer)).thenReturn(Optional.empty());
        // When / Then
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getByLastName(unknowPlayer);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("Player with last name doe could not be found.");

    }
}
