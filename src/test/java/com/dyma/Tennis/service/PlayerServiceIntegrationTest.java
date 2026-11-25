package com.dyma.Tennis.service;

import com.dyma.Tennis.Player;
import com.dyma.Tennis.PlayerNotFoundException;
import com.dyma.Tennis.PlayerToSave;
import com.dyma.Tennis.services.PlayerService;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerServiceIntegrationTest {

    @Autowired
    private PlayerService playerService;


    @Test
    public void shouldCreatePlayer(){
        // Given
        PlayerToSave playerToSave = new PlayerToSave(
                "John",
                "Doe",
                LocalDate.of(2000, Month.JANUARY, 1),
                10000
        );


        // When
        playerService.create(playerToSave);
        Player createdPlayer = playerService.getByLastName(playerToSave.lastName());
        // Then
        Assertions.assertThat(createdPlayer.firstName()).isEqualTo("John");
        Assertions.assertThat(createdPlayer.lastName()).isEqualTo("Doe");
        Assertions.assertThat(createdPlayer.birthDate()).isEqualTo(LocalDate.of(2000, Month.JANUARY, 1));
        Assertions.assertThat(createdPlayer.rank().position()).isEqualTo(1);
        Assertions.assertThat(createdPlayer.rank().points()).isEqualTo(10000);
    }
    @Test
    public void shouldUpdatePlayer(){
        //Given
        PlayerToSave playerToSave = new PlayerToSave(
                "Rafael",
                "NadalTest",
                LocalDate.of(1986, Month.JUNE, 13),
                1000
        );

        //When
        playerService.update(playerToSave);

        Player updatedPlayer = playerService.getByLastName(playerToSave.lastName());

        // Then
        Assertions.assertThat(updatedPlayer.rank().position()).isEqualTo(3);
    }

    @Test
    public void shouldDeletePlayer(){
        //Given
        String lastName = "DjokovicTest";
        // When
        playerService.delete(lastName);
        List<Player> allPlayers = playerService.getAllPlayers();
        // THen
        Assertions.assertThat(allPlayers)
                .extracting("lastName", "rank.position")
                .containsExactly(Tuple.tuple("NadalTest", 1), Tuple.tuple("FedererTest", 2 ));
    }

    @Test
    public void shouldFailToDelete_WhenPlayerDoesNotExists(){
        //Given
        String playerToDelete = "DoeTest";
        // When
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(PlayerNotFoundException.class, () -> {
            playerService.delete(playerToDelete);
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("Player with last name DoeTest could not be found.");
    }
}
