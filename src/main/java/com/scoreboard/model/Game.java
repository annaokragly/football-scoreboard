package com.scoreboard.model;

import java.time.Instant;

public class Game {
    private final Long id;
    private final String homeTeam;
    private final String awayTeam;
    private Score score;
    private final Instant startTime;

    public Game(Long id, String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isBlank()) {
            throw new IllegalArgumentException("Home team name cannot be empty");
        }
        if (awayTeam == null || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Away team name cannot be empty");
        }
        this.homeTeam = validateTeamName(homeTeam, "Home");
        this.awayTeam = validateTeamName(awayTeam, "Away");
        if (this.homeTeam.equalsIgnoreCase(this.awayTeam)) {
            throw new IllegalArgumentException("Home and away teams cannot be the same");
        }

        this.id = id;
        this.score = Score.initial();
        this.startTime = Instant.now();
    }

    public void updateScore(int homeScore, int awayScore) {
        this.score = new Score(homeScore, awayScore);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getTotalScore() {
        return score.total();
    }

    public int getHomeScore() {
        return score.home();
    }

    public int getAwayScore() {
        return score.away();
    }

    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeam, score.home(), awayTeam, score.away());
    }

    private String validateTeamName(String teamName, String teamType) {
        if (teamName == null || teamName.isBlank()) {
            throw new IllegalArgumentException(teamType + " team name cannot be empty");
        }
        String trimmed = teamName.trim();
        if (trimmed.length() > 50) {
            throw new IllegalArgumentException(teamType + " team name too long (max 50 characters)");
        }
        return trimmed;
    }
}
