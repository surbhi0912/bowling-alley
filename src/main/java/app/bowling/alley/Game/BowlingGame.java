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
        for(int round = 1; round <= Constants.BOWLING_NUMBER_OF_ROUNDS; round++){
            System.out.println("ROUND " + round);
            System.out.println("***************");
            for(int playerCount = 0; playerCount < Constants.BOWLING_NUMBER_OF_PLAYERS; playerCount++){
                System.out.println("Player " + playerCount+1);
                int roundScore = playRound(round, playerCount);
                if(round == Constants.BOWLING_NUMBER_OF_ROUNDS){ //for final round
                    if(roundScore >= Constants.BOWLING_PINS_COUNT){ //allow to play 2 extra balls
                        System.out.println("Enter score for extra chance 1 : ");
                        int extraChanceOne = sc.nextInt();
                        System.out.println("Enter score for extra chance 2 : ");
                        int extraChanceTwo = sc.nextInt();

                        if(extraChanceOne == Constants.BOWLING_PINS_COUNT){
                            roundScore += extraChanceOne + Constants.BOWLING_BONUS_STRIKE;
                        }

                        if(extraChanceTwo == Constants.BOWLING_PINS_COUNT){
                            roundScore += extraChanceTwo + Constants.BOWLING_BONUS_STRIKE;
                        }

                        if(extraChanceOne + extraChanceOne == Constants.BOWLING_PINS_COUNT){

                        }
                    }
                }
                BowlingAlleyApp.players.get(playerCount).addRoundScore(roundScore);
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
        System.out.println("Enter score for Chance 1 : ");
        chanceOne = sc.nextInt();

        if(chanceOne == Constants.BOWLING_PINS_COUNT){ //strike
            roundScore += chanceOne + Constants.BOWLING_BONUS_STRIKE;
            BowlingAlleyApp.players.get(playerCount).setRoundResult("{X,}");
        }
        else{
            roundScore += chanceOne;
            System.out.println("Enter score for Chance 2 : ");
            chanceTwo = sc.nextInt();
            if(chanceOne + chanceTwo == Constants.BOWLING_PINS_COUNT){ //spare
                roundScore += chanceTwo + Constants.BOWLING_BONUS_SPARE;
                BowlingAlleyApp.players.get(playerCount).setRoundResult("{chanceOne,/}");
            } else {
                roundScore += chanceTwo;
                BowlingAlleyApp.players.get(playerCount).setRoundResult("{chanceOne,chanceTwo}");
            }
        }
        return roundScore;
    }
}
