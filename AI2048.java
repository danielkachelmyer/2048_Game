/*
 * write an ai that can play the game
 * later parts will have it improve to win at the game
 * 
 * 
 * currently have it call a trial for each move
 * what ever move the trial returns, make that move
 */

 import java.util.Random;
public class AI2048 {

    public static void main(String[] args) {
        String move = "";
        gameObject AIGameObject = new gameObject();
        while(AIGameObject.canStillplay){
            
            
            Trial newTrial = new Trial(AIGameObject);
            move = newTrial.tryRandomMoves();


   //         Random random = new Random();
     //       String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
 //           move = moves[random.nextInt(moves.length)];

            AIGameObject.printBoard();
            AIGameObject.turn(move);
            System.out.println("AI made move " + move);
            try {
                Thread.sleep(100); // Sleep for 2000 milliseconds (2 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        AIGameObject.getCanStillPlay();
        }
        AIGameObject.printBoard();
    }	

    
}
