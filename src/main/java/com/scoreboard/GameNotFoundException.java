package com.scoreboard;

/**
 * Exception thrown when attempting to operate on a non-existent game.
 */
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long gameId) {
        super("Game not found with id: " + gameId);
    }
}
