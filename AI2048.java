/*
 * write an ai that can play the game
 * later parts will have it improve to win at the game
 */

 import java.util.Random;
public class AI2048 {

    public static void main(String[] args) {
        
    
		int a1 = 0, a2 = 0, a3 = 0, a4 = 0, 
            b1 = 0, b2 = 0, b3 = 0, b4 = 0, 
            c1 = 0, c2 = 0, c3 = 0, c4 = 0, 
            d1 = 0, d2 = 0, d3 = 0, d4 = 0;
        int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
		
        //generate board to visualize
		game2048.Randomize(board);
		game2048.Randomize(board);
        gameGraphics showBoard = new gameGraphics(board);
        String randomMove;
        for(int i = 0; i < 12; i++){

            Random random = new Random();
            String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
            randomMove = moves[random.nextInt(moves.length)];
            try{
                System.out.println("move " + i + " " + " it moved " + randomMove);
                game2048.move(board, 4, randomMove);
                game2048.Randomize(board);
                game2048.printBoard(board);
                System.out.println("debug dfsfsf");
                Trial thisTrial = new Trial(board, 4);
                System.out.println("fitness Score is " + thisTrial.getFitnessScore());
                System.out.println("best move is " + tryRandomMoves(board, randomMove, thisTrial.getFitnessScore()));
                System.out.println("debug");
                Thread.sleep(100);
        
            }
            catch(Exception e){
                continue;
            }
        }
    }

    public static String tryRandomMoves(int [] board, String prevChoice, int prevFitnessScore){
        String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
        int maxScore = 0;
        String bestMove = "";
        for(int i = 0; i < 4; i++){
            try{
                game2048.move(board, 4, moves[i]);
            
        Trial thisTrial = new Trial(board, 4);

        if(thisTrial.getFitnessScore() > maxScore){
            maxScore = thisTrial.getFitnessScore();
            bestMove = moves[i];
        }
            }
            catch(Exception e){
                continue;
            }
        }
        return bestMove;
    }
}
