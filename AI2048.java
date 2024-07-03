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
       int total = 1000;
       int aboveAvg = 0;
        for(int i = 0; i < total; i++){//for loop to iterate over tons of games
        if(runAI() > max){
            max = runAI();
            
        }
        if(runAI() > 1024){
            aboveAvg++;    
        }
        
       } 
       System.out.println(max);
       System.out.println("amount that were above avg " + aboveAvg + " out of " + total);
    }	

    public static int runAI(){
        String move = "";
        String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };//array to hold the moves variables
        gameObject AIGameObject = new gameObject();
        while(AIGameObject.getCanStillPlay()){//while the AI can still play
            
            Trial newTrial = new Trial(AIGameObject);//create a trial object for each move
            move = newTrial.executeTrial(20000);
            AIGameObject.turn(move);
            //if the trial board equals the current board, go through all the other moves
            if(move == "LEFT" && AIGameObject.getLeftInvalid() == true){   
                for(int i = 0; i < 4; i++){
                    AIGameObject.turn(moves[i]);
                }
            }
        }
        if(AIGameObject.getScore(AIGameObject.getCurrentBoard()) > 1024){
            AIGameObject.printBoard();
        }
        return AIGameObject.getScore(AIGameObject.getCurrentBoard());
        
    }
    
}
