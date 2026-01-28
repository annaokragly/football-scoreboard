package com.scoreboard;

import com.scoreboard.model.Game;

import java.util.List;

/**
 * Demonstration of the Scoreboard library usage.
 */
public class ScoreboardDemo {

    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        System.out.println("═══════════════════════════════════════");
        System.out.println("LIVE FOOTBALL WORLD CUP SCOREBOARD DEMO");
        System.out.println("═══════════════════════════════════════\n");

        System.out.println("Starting games...\n");

        Game game1 = scoreboard.startGame("Mexico", "Canada");
        Game game2 = scoreboard.startGame("Spain", "Brazil");
        Game game3 = scoreboard.startGame("Germany", "France");
        Game game4 = scoreboard.startGame("Uruguay", "Italy");
        Game game5 = scoreboard.startGame("Argentina", "Australia");

        printAllGames(scoreboard);

        System.out.println("Updating scores...\n");

        updateAndPrint(scoreboard, game1, 0, 5);
        updateAndPrint(scoreboard, game2, 10, 2);
        updateAndPrint(scoreboard, game3, 2, 2);
        updateAndPrint(scoreboard, game4, 6, 6);
        updateAndPrint(scoreboard, game5, 1, 2);

        System.out.println("\nFinishing game: " + game5 + "\n");

        demonstrateFinishGame(scoreboard, game5.getId());

        printSummary(scoreboard);
    }

    private static void printAllGames(Scoreboard scoreboard) {
        System.out.println("Live games:\n");
        List<Game> games = scoreboard.getAllGames();
        for (Game game : games) {
            System.out.printf("  %s %d - %d %s\n",
                game.getHomeTeam(),
                game.getHomeScore(),
                game.getAwayScore(),
                game.getAwayTeam());
        }
        System.out.println();
    }

    private static void updateAndPrint(Scoreboard scoreboard, Game game, int homeScore, int awayScore) {
        scoreboard.updateScore(game.getId(), homeScore, awayScore);
        System.out.printf("  Updated: %s %d - %d %s\n",
            game.getHomeTeam(),
            game.getHomeScore(),
            game.getAwayScore(),
            game.getAwayTeam());
    }


    private static void demonstrateFinishGame(Scoreboard scoreboard, Long gameId) {
        boolean firstAttempt = scoreboard.finishGame(gameId);
        System.out.println("  First finish attempt: " + (firstAttempt ? "✓ Success" : "✗ Failed"));

        boolean secondAttempt = scoreboard.finishGame(gameId);
        System.out.println("  Second finish attempt: " + (secondAttempt ? "✓ Success" : "✗ Already finished (idempotent)\n"));
    }

    private static void printSummary(Scoreboard scoreboard) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("          LIVE GAMES SUMMARY");
        System.out.println("═══════════════════════════════════════\n");

        List<Game> summary = scoreboard.getSummary();
        if (summary.isEmpty()) {
            System.out.println("  No games on the scoreboard.");
            return;
        } else {
            for (int i = 0; i < summary.size(); i++) {
                Game game = summary.get(i);
                System.out.printf("  %2d. %-15s %2d - %2d %-15s\n",
                        i + 1,
                        game.getHomeTeam(),
                        game.getHomeScore(),
                        game.getAwayScore(),
                        game.getAwayTeam()
                );
            }
        }

        System.out.println("\n");
    }
}
