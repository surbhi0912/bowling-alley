package app.bowling.alley.Player;

import java.util.List;

public class Player {
    private int totalScore = 0;
    private List<String> roundResults;

    public Player() {
    }

    public int getTotalScore() {
        return totalScore;
    }


    public List<String> getResult() {
        return roundResults;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setRoundResult(String currentRoundResult) {
        roundResults.add(currentRoundResult);
    }

    public void addRoundScore(int roundScore) {
        this.totalScore += roundScore;
    }
}
