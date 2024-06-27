import java.util.random.*;
import java.util.Random;

public class RandomMove {
    private String move;


    public RandomMove(){
        generateRandomMove();
    }

    private void generateRandomMove(){
        Random random = new Random();
        String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
        this.move = moves[random.nextInt(moves.length)];
    }

    public String getMove(){
        return move;
    }

    public void seetRandomMove(){
        generateRandomMove();
    }
}
