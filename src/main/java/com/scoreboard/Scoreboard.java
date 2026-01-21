package com.scoreboard;

import com.scoreboard.model.Game;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Scoreboard {
    private final Map<Long, Game> games;
    private final AtomicLong nextId = new AtomicLong(1);

    public Scoreboard() {
        this.games = new ConcurrentHashMap<>();
    }

    public Game startGame(String homeTeam, String awayTeam) {
        Long id = nextId.getAndIncrement();
        Game game = new Game(id, homeTeam, awayTeam);
        games.put(id, game);
        return game;
    }

    public boolean finishGame(Long id) {
        return games.remove(id) != null;
    }

    public void updateScore(Long id, int homeScore, int awayScore) {
        Game game = games.computeIfPresent(id, (key, existingGame) -> {
            existingGame.updateScore(homeScore, awayScore);
            return existingGame;
        });

        if (game == null) {
            throw new GameNotFoundException(id);
        }
    }

    public List<Game> getSummary() {
        List<Game> summary = new ArrayList<>(games.values());

        summary.sort((game1, game2) -> {
            int totalCompare = Integer.compare(
                    game2.getTotalScore(),
                    game1.getTotalScore()
            );

            if (totalCompare != 0) {
                return totalCompare;
            }

            return game2.getStartTime().compareTo(game1.getStartTime());
        });

        return List.copyOf(summary);
    }

    public Game getGame(Long id) {
        return games.get(id);
    }

    public List<Game> getAllGames() {
        return List.copyOf(games.values());
    }
    public int getGameCount() {
        return games.size();
    }
}
