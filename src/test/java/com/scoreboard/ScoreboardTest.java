package com.scoreboard;

import com.scoreboard.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Scoreboard Tests")
class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    @DisplayName("Should start a new game with initial score 0-0")
    void testStartGame() {
        Game game = scoreboard.startGame("Poland", "Brazil");

        assertNotNull(game);
        assertNotNull(game.getId());
        assertEquals("Poland", game.getHomeTeam());
        assertEquals("Brazil", game.getAwayTeam());
        assertEquals(0, game.getHomeScore());
        assertEquals(0, game.getAwayScore());
        assertNotNull(game.getStartTime());
    }

    @Test
    @DisplayName("Should throw exception when starting game with empty home team")
    void testStartGameEmptyHomeTeam() {
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame("", "Brazil"));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame("   ", "Brazil"));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame(null, "Brazil"));
    }

    @Test
    @DisplayName("Should throw exception when starting game with empty away team")
    void testStartGameEmptyAwayTeam() {
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame("Poland", ""));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame("Poland", "   "));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startGame("Poland", null));
    }

    @Test
    @DisplayName("Should update game score successfully")
    void testUpdateScore() {
        Game game = scoreboard.startGame("Poland", "Brazil");

        scoreboard.updateScore(game.getId(), 2, 1);

        assertEquals(2, game.getHomeScore());
        assertEquals(1, game.getAwayScore());
        assertEquals(3, game.getTotalScore());
    }

    @Test
    @DisplayName("Should throw exception when updating with negative scores")
    void testUpdateScoreNegative() {
        Game game = scoreboard.startGame("Poland", "Brazil");

        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore(game.getId(), -1, 0));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore(game.getId(), 0, -1));
        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateScore(game.getId(), -1, -1));
    }

    @Test
    @DisplayName("Should finish game and remove from scoreboard")
    void testFinishGame() {
        Game game = scoreboard.startGame("Poland", "Brazil");
        Long gameId = game.getId();

        assertEquals(1, scoreboard.getGameCount());

        scoreboard.finishGame(gameId);

        assertEquals(0, scoreboard.getGameCount());
        assertNull(scoreboard.getGame(gameId));
    }
}