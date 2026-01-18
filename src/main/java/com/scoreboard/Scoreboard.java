package com.scoreboard;

import com.scoreboard.model.Game;

import java.util.*;

public class Scoreboard {
    private final Map<Long, Game> games;
    private long nextId;

    public Scoreboard() {
        this.games = new HashMap<>();
        this.nextId = 1;
    }

    public Game startGame(String homeTeam, String awayTeam) {
        Long id = nextId++;
        Game game = new Game(id, homeTeam, awayTeam);
        games.put(id, game);
        return game;
    }

    public void finishGame(Long id) {
        Game game = games.remove(id);
        if (game == null) {
            throw new NoSuchElementException("Cannot find game with id: " + id);
        }
    }

    public void updateScore(Long id, int homeScore, int awayScore) {
        Game game = games.get(id);
        if (game == null) {
            throw new NoSuchElementException("Cannot find game with id: " + id);
        }
        game.updateScore(homeScore, awayScore);
    }

    public Game getGame(Long id) {
        return games.get(id);
    }

    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    public int getGameCount() {
        return games.size();
    }
}
