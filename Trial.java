/*
 *take a game object and calc the highest piece
 * determine if the highest piece is in a corner
 * a trial is when the ai executes one move
 * and then random moves until a win or loss
 * keeps track of the highest score and which
 * ever inital direction has the highest score
 * tells the ai to make that direction
 * 
 * 
 * try to upgrade trial so it can look at x moves infront
 * instead of just one move ahead
 */

import java.util.Random;

public class Trial {
    
    public gameObject myGameObject;//keep track of initial board
    public gameObject trialGame;
    public int [] originalBoard = {0,1,2,3,4,5,6,7,8,9,1,1,1,1,1,1};

    Trial(gameObject myGameObject){
        this.myGameObject = myGameObject;
        this.trialGame = new gameObject();
        this.originalBoard = getOriginalBoard(myGameObject.getCurrentBoard());

        setTrialGame();
    }

    public int[] getOriginalBoard(int [] board){
       
        for(int i = 0; i < board.length; i++){
			originalBoard[i] = board[i] ;
		}
        return originalBoard;
    }

    public void setTrialGame(){
        trialGame.setBoard(originalBoard);
    }

/*
 * try all four moves possible on the current board
 * keep track of the score
 * which ever move gives the highest score,
 * return that move
 * 
 */
    public  String executeTrial(int numMoves){
        String [] firstMoves = {"LEFT", "RIGHT", "UP", "DOWN", };
        int maxScore = 0;
        String bestMove = "";
        String randomMove = "";
        int counter = 0;

        for(int i = 0; i < firstMoves.length; i++){//need to keep track of original board position
            //make first move
            counter = 0;
            getOriginalBoard(originalBoard);
            trialGame.setBoard(originalBoard);
            trialGame.turn(firstMoves[i]);
            while(trialGame.getCanStillPlay() && counter < numMoves){//execute random moves till loss
                 Random random = new Random();
                String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
                randomMove = moves[random.nextInt(moves.length)];
                try{
                    trialGame.turn(randomMove);
                }
                catch(Exception e){

                }
                if(trialGame.getScore(trialGame.board) > maxScore){//if the left turn has highest score, return left as move
                    maxScore = trialGame.getScore(trialGame.board);//keep track of highest score
                    bestMove = firstMoves[i];
                }
            
                if(i == 6){                         //when the first move makes no diff, it keeps the getCanStillPlay variable as false
                    System.out.println(randomMove);//while loop doesn't execute
                    trialGame.printBoard();
                }
                counter++;
            }
          //  System.out.println("move is " + firstMoves[i] + " this moves score is " + myGameObject.getScore(myGameObject.board));
            
        }
       // System.out.println("max score was: " + maxScore);
        return bestMove;
    }

/*
 * make an advanced trial that calculates the score
 * based if the highest value piece is in the top left corner
 * additionally only takes the highest piece value as the score
 * score = biggest value +  20 if topLeft
 */
    public String advancedTrial(int numMoves){
        String [] firstMoves = {"LEFT", "RIGHT", "UP", "DOWN", };
        int maxScore = 0;
        String bestMove = "";
        String randomMove = "";
        int counter = 0;
        int fitnessScore = 0;

        for(int i = 0; i < firstMoves.length; i++){//need to keep track of original board position
            //make first move
            counter = 0;
            fitnessScore = 0;
            getOriginalBoard(originalBoard);
            trialGame.setBoard(originalBoard);
            trialGame.turn(firstMoves[i]);
            while(trialGame.getCanStillPlay() && counter < numMoves){//execute random moves till loss
                 Random random = new Random();
                String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
                randomMove = moves[random.nextInt(moves.length)];
                try{
                    trialGame.turn(randomMove);
                }
                catch(Exception e){

                }
                fitnessScore = trialGame.getBiggestValue(trialGame.getCurrentBoard());
                if(trialGame.getTopLeft(trialGame.getCurrentBoard())){
                    fitnessScore += 20;
                }
                if(fitnessScore > maxScore){//if the left turn has highest score, return left as move
                    maxScore = fitnessScore;//keep track of highest score
                    bestMove = firstMoves[i];
                }
                counter++;

            }   
         //   System.out.println("move is " + firstMoves[i]);
         //   trialGame.printBoard();
        }
    //    System.out.println("best move" + bestMove);
        return bestMove;    
    }
}
