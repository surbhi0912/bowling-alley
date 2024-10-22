package app.bowling.alley.Game;

import app.bowling.alley.BowlingAlleyApp;
import app.bowling.alley.Constants;
import app.bowling.alley.Player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BowlingGame implements IGame {
    private Map<Integer, Integer> mapPlayerScore = new HashMap<>();

    @Override
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        for(int round = 0; round < Constants.BOWLING_NUMBER_OF_ROUNDS; round++){
            System.out.println("ROUND " + round);
            System.out.println("***************");
            for(int playerCount = 0; playerCount < Constants.BOWLING_NUMBER_OF_PLAYERS; playerCount++){
                System.out.println("Player " + playerCount+1);
                int roundScore = playRound(round, playerCount);
                if(round == Constants.BOWLING_NUMBER_OF_ROUNDS - 1){ //for final round
                    if(roundScore >= Constants.BOWLING_PINS_COUNT){ //allow to play 2 extra balls

                        playRound(round+1, playerCount);

                        System.out.println("Enter score for extra chance 1 : ");
                        int extraChanceOne = sc.nextInt();
                        roundScore += extraChanceOne;

                        System.out.println("Enter score for extra chance 2 : ");
                        int extraChanceTwo = sc.nextInt();
                        roundScore += extraChanceTwo;

                        if(extraChanceOne == Constants.BOWLING_PINS_COUNT){
                            roundScore += Constants.BOWLING_BONUS_STRIKE;
                        }

                        if(extraChanceTwo == Constants.BOWLING_PINS_COUNT){
                            roundScore += Constants.BOWLING_BONUS_STRIKE;
                        }

                        if(extraChanceOne + extraChanceOne == Constants.BOWLING_PINS_COUNT){

                        }
                    }
                }
                BowlingAlleyApp.players.get(playerCount).setRoundScore(roundScore);
                BowlingAlleyApp.players.get(playerCount).setTotalScore(roundScore);
                showResult();
            }

        }
    }

    @Override
    public void showResult() {
        for(int playerCount = 0; playerCount < Constants.BOWLING_NUMBER_OF_PLAYERS; playerCount++){
            System.out.println("P" + playerCount+1 + ": " + BowlingAlleyApp.players.get(playerCount).getResult() + " --> " + BowlingAlleyApp.players.get(playerCount).getTotalScore());
        }
    }

    public int playRound(int round, int playerCount) {
        Scanner sc = new Scanner(System.in);
        int chanceOne = 0, chanceTwo = 0, roundScore = 0;

        boolean extraRound = false;
        if(round == Constants.BOWLING_NUMBER_OF_ROUNDS){
            extraRound = true;
        }

        System.out.println("Enter score for Chance 1 : ");
        chanceOne = sc.nextInt();
        roundScore += chanceOne;

        if(chanceOne == Constants.BOWLING_PINS_COUNT) { //strike
            roundScore += Constants.BOWLING_BONUS_STRIKE;
            if (extraRound) {
                BowlingAlleyApp.players.get(playerCount).setRoundResult("{X,");
            } else { //regular round, so exit after strike
                BowlingAlleyApp.players.get(playerCount).setRoundResult("{X,}");
                return roundScore;
            }
        } else { //not strike in chance one
            BowlingAlleyApp.players.get(playerCount).setRoundResult("{"+chanceOne+",");
        }

        System.out.println("Enter score for Chance 2 : ");
        chanceTwo = sc.nextInt();
        roundScore += chanceTwo;

        if(chanceTwo == Constants.BOWLING_PINS_COUNT) { //strike at chance two
            roundScore += Constants.BOWLING_BONUS_STRIKE;
            BowlingAlleyApp.players.get(playerCount).setRoundResult(
                    BowlingAlleyApp.players.get(playerCount).getResult().get(round) + "X}");
        }

        if(chanceOne + chanceTwo == Constants.BOWLING_PINS_COUNT && chanceOne != 0 && chanceTwo != 0){ //spare
            roundScore += Constants.BOWLING_BONUS_SPARE;
            BowlingAlleyApp.players.get(playerCount).setRoundResult(
                    BowlingAlleyApp.players.get(playerCount).getResult().get(round) + "/}");
        } else {
            BowlingAlleyApp.players.get(playerCount).setRoundResult("{"+chanceOne+","+chanceTwo+"}");
        }

        return roundScore;
    }
}
