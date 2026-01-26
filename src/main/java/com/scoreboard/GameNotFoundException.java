package com.scoreboard;

/**
 * Exception thrown when attempting to operate on a non-existent game.
 */
public class GameNotFoundException extends RuntimeException {
    private final Long gameId;

    public GameNotFoundException(Long gameId) {
        super(String.format(
                "Game not found with ID: %d. It may have finished or never existed.",
                gameId
        ));
        this.gameId = gameId;
    }

    public Long getGameId() {
        return gameId;
    }
}
