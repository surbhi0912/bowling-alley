package app.bowling.alley.Player;

import java.util.List;

public class Player {
    private int totalScore = 0;
    private List<String> roundsResult;
    private List<Integer> roundsScore;

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getResult() {
        return roundsResult;
    }

    public void setTotalScore(int roundScore) {
        this.totalScore += roundScore;
    }

    public void setRoundResult(String currentRoundResult) {
        roundsResult.add(currentRoundResult);
    }

    public void setRoundScore(int roundScore) {
        roundsScore.add(roundScore);
    }
}
