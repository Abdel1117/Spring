package com.dyma.Tennis.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dyma.Tennis.Player;
import com.dyma.Tennis.PlayerList;
import com.dyma.Tennis.PlayerNotFoundException;

@Service
public class PlayerService {

    public List<Player> getAllPlayers() {
        return PlayerList.ALL.stream()
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());
    }

    public Player getByLastName(String lastName) {
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(lastName));
    }
}
