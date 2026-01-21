package com.scoreboard.model;

/**
 * Value object representing a game score.
 * Encapsulates score validation logic in one place.
 */
public record Score(int home, int away) {
    private static final int MAX_REALISTIC_SCORE = 50;

    public Score {
        if (home < 0 || away < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        if (home > MAX_REALISTIC_SCORE || away > MAX_REALISTIC_SCORE) {
            throw new IllegalArgumentException(
                    "Score exceeds realistic maximum (" + MAX_REALISTIC_SCORE + ")"
            );
        }
    }

    public static Score initial() {
        return new Score(0, 0);
    }

    public int total() {
        return home + away;
    }
}
