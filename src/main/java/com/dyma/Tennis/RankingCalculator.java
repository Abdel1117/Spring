package com.dyma.Tennis;

import com.dyma.Tennis.data.PlayerEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingCalculator {

    private final List<PlayerEntity> currentPlayersRanking;
    private final PlayerToSave playerToSave;

    public RankingCalculator(List<PlayerEntity> currentPlayersRanking, PlayerToSave playerToSave) {
        this.currentPlayersRanking = currentPlayersRanking;
        this.playerToSave = playerToSave;
    }

    public RankingCalculator(List<PlayerEntity> currentPlayersRanking) {
        this.currentPlayersRanking = currentPlayersRanking;
        this.playerToSave = null;
    }

    public List<PlayerEntity> getNewPlayersRanking() {
        currentPlayersRanking.sort((player1, player2) -> Integer.compare(player2.getPoints(), player1.getPoints()));
        List<PlayerEntity> updatedPlayers = new ArrayList<>();

        for(int i = 0; i < currentPlayersRanking.size(); i++){
            PlayerEntity updatedPlayer = currentPlayersRanking.get(i);

            updatedPlayer.setRank(i + 1);
            updatedPlayers.add(updatedPlayer);
        }
        return updatedPlayers;
    }
}