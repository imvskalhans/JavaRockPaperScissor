import java.util.Random;

public class RockPaperScissor {

    private enum Move {
        ROCK, PAPER, SCISSORS
    }

    private static final Move[] MOVES = Move.values();

    private Move computerChoice;
    private int computerScore, playerScore;

    private Random random;

    public RockPaperScissor() {
        random = new Random();
    }

    public String getComputerChoice() {
        return computerChoice.name();
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public String playRockPaperScissor(String playerChoice) {
        Move playerMove = Move.valueOf(playerChoice.toUpperCase());

        computerChoice = MOVES[random.nextInt(MOVES.length)];

        String result;
        if (computerChoice == playerMove) {
            result = "Draw";
        } else if ((computerChoice == Move.ROCK && playerMove == Move.SCISSORS) ||
                (computerChoice == Move.SCISSORS && playerMove == Move.PAPER) ||
                (computerChoice == Move.PAPER && playerMove == Move.ROCK)) {
            result = "Computer Wins";
            computerScore++;
        } else {
            result = "Player Wins";
            playerScore++;
        }

        return result;
    }
}
