package com.dyma.Tennis.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dyma.Tennis.data.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyma.Tennis.Player;
import com.dyma.Tennis.PlayerList;
import com.dyma.Tennis.PlayerNotFoundException;
import com.dyma.Tennis.Rank;
import com.dyma.Tennis.PlayerToSave;
import com.dyma.Tennis.RankingCalculator;
import com.dyma.Tennis.data.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerEntity -> new Player(
                        playerEntity.getFirstName(),
                        playerEntity.getLastName(),
                        playerEntity.getBirthDate(),
                        new Rank(playerEntity.getRank(), playerEntity.getPoints())))
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());
    }

    public Player getByLastName(String lastName) {

        Optional<PlayerEntity> player = playerRepository.findOneByLastNameIgnoreCase(lastName);
        if(player.isEmpty()){
            throw new PlayerNotFoundException((lastName));
        }
        PlayerEntity playerEntity = player.get();
        return new Player(
                playerEntity.getFirstName(),
                playerEntity.getLastName(),
                playerEntity.getBirthDate(),
                new Rank(playerEntity.getRank(), playerEntity.getPoints()));
    }

    public Player create(PlayerToSave playerToSave) {
        return getPlayerNewRanking(PlayerList.ALL, playerToSave);
    }

    public Player update(PlayerToSave playerToSave) {
        getByLastName(playerToSave.lastName());
        List<Player> playersWithoutPlayerToUpdate = PlayerList.ALL.stream()
                .filter(player -> !player.lastName().equals(playerToSave.lastName()))
                .toList();

        RankingCalculator rankingCalculator = new RankingCalculator(playersWithoutPlayerToUpdate, playerToSave);
        List<Player> players = rankingCalculator.getNewPlayersRanking();

        return players.stream()
                .filter(player -> player.lastName().equals(playerToSave.lastName()))
                .findFirst().get();

    }

    public void delete(String lastName) {
        Player playerToDelete = getByLastName(lastName);
        PlayerList.ALL = PlayerList.ALL.stream()
                .filter(player -> !player.lastName().equals(playerToDelete.lastName()))
                .toList();
        RankingCalculator rankingCalculator = new RankingCalculator(PlayerList.ALL);
        rankingCalculator.getNewPlayersRanking();
    }

    private Player getPlayerNewRanking(List<Player> existingPlayers, PlayerToSave playerToSave) {
        RankingCalculator rankingCalculator = new RankingCalculator(existingPlayers, playerToSave);
        List<Player> players = rankingCalculator.getNewPlayersRanking();

        return players.stream()
                .filter(player -> player.lastName().equals(playerToSave.lastName()))
                .findFirst().get();

    }
}
