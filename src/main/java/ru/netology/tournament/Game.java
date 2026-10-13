package ru.netology.tournament;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();

    public void register(Player player) {
        players.add(player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null) {
            throw new NotRegisteredException(
                    "Игрок с именем " + playerName1 + " не зарегистрирован"
            );
        }

        if (player2 == null) {
            throw new NotRegisteredException(
                    "Игрок с именем " + playerName2 + " не зарегистрирован"
            );
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        }

        if (player1.getStrength() < player2.getStrength()) {
            return 2;
        }

        return 0;
    }

    private Player findByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }

        return null;
    }
}