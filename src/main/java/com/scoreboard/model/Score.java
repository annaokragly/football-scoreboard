package com.scoreboard.model;

/**
 * Value object representing a game score.
 * Encapsulates score validation logic in one place.
 */
public record Score(int home, int away) {

    public Score {
        if (home < 0 || away < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
    }

    public static Score initial() {
        return new Score(0, 0);
    }

    public int total() {
        return home + away;
    }
}
