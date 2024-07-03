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
       int max = 0;
        for(int i = 0; i < 1000; i++){
        if(runAI() > max){
            max = runAI();
        }
       } 
       System.out.println(max);
    }	

    public static int runAI(){
        String move = "";
        String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
        gameObject AIGameObject = new gameObject();
        while(AIGameObject.getCanStillPlay()){
            
            Trial newTrial = new Trial(AIGameObject);
            move = newTrial.tryRandomMoves();
           // AIGameObject.printBoard();
            AIGameObject.turn(move);
           // System.out.println("AI made move " + move);
           
            //if the trial board equals the current board, go through all the other moves
            for(int i = 0; i < 4; i++){
                AIGameObject.turn(moves[i]);
            }
        }
        return AIGameObject.getScore(AIGameObject.getCurrentBoard());
        
    }
    
}
