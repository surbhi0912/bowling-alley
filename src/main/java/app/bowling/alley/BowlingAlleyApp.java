package app.bowling.alley;

import app.bowling.alley.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BowlingAlleyApp {
    public static List<Player> players = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        Constants.BOWLING_NUMBER_OF_PLAYERS = sc.nextInt();

        for(int playerCount = 0; playerCount < Constants.BOWLING_NUMBER_OF_PLAYERS; playerCount++){
            players.add(new Player());
        }
    }
}