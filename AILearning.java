/*
 * The idea is to have a file that
 * holds learning Objects 
 * each learning object has a move, board and score
 * when the ai makes a move, it consults the file of learning objects
 * it looks for a similar board and its move made, based off which move got the highest score
 * if the move made results in a higher score record it and change the array
 */

import java.util.ArrayList;
import java.util.Random;

public class AILearning {
    public static ArrayList<learningObject> learningObjectsList = new ArrayList<>();
    public static void main(String[] args) {
        int numGames = 100;
    
         for(int i = 0; i < numGames; i++){
            runAI();
         }
    System.out.println(learningObjectsList.size());
    }
/*
 * runAI should start a game and make a random move
 * before making that move checks the learning Objects List to 
 * see if there is a board with a certain similarity, if there is
 * access that object and its best move
 * try to make that move
 */
    public static void runAI(){
        String randomMove = "";
        int threshold = 1;    
        int similarity = 0;
        int randomMoveCounter = 0;
        int AIMoveCounter = 0;
        int debug = 0;
        gameObject AIGameObject = new gameObject();
            while(AIGameObject.getCanStillPlay()){//while the AI can still play
                for(learningObject AILearningObject : learningObjectsList){
                    debug++;
                    similarity = similarity(AIGameObject.getCurrentBoard(), AILearningObject.getBoard());
                 //   System.out.println(similarity);
                    if(similarity >= threshold){
                        threshold = similarity;// set the new threshold to be this current similarity score
                        randomMove = AILearningObject.getBestMove();
                      //  System.out.println("made ai move");
                    }
                }
                try{//try to make the move from learning Object list
                    if(randomMove!= ""){
                        AIMoveCounter++;
                        AIGameObject.turn(randomMove);
                    }
                    else{//random move wasn't assigned
                        randomMoveCounter++;
                        Random random = new Random();
                        String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
                        randomMove = moves[random.nextInt(moves.length)];
                        AIGameObject.turn(randomMove);
                    }
                }
                catch(Exception e){

                }
                learningObject addObject = new learningObject(AIGameObject.getBoard());
                learningObjectsList.add(addObject);
                randomMove = "";
            }
        //    AIGameObject.printBoard();
            threshold = 1;
        //    System.out.println("AI moves " + AIMoveCounter);
        //    System.out.println("random moves " + randomMoveCounter);
            System.out.println(AIGameObject.getScore(AIGameObject.getCurrentBoard()));
           // System.out.println(debug);
        
    }
    
//returns a boolean if two boards are similar enough
    public static int similarity(int [] a, int [] b){
        int similarity = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == b[i]){
                similarity++;
            }
        }
        return similarity;
    }
}

