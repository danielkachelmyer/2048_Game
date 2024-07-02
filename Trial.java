/*
 *take a game object and calc the highest piece
 * determine if the highest piece is in a corner
 * a trial is when the ai executes one move
 * and then random moves until a win or loss
 * keeps track of the highest score and which
 * ever inital direction has the highest score
 * tells the ai to make that direction
 */

import java.util.Random;

public class Trial {
    
    public gameObject myGameObject;//keep track of initial board
    public int [] originalBoard = {0,1,2,3,4,5,6,7,8,9,1,1,1,1,1,1};

    Trial(gameObject myGameObject){
        this.myGameObject = myGameObject;
        this.originalBoard = getOriginalBoard(myGameObject.getCurrentBoard());
    }

    public int[] getOriginalBoard(int [] board){
       
        for(int i = 0; i < board.length; i++){
			originalBoard[i] = board[i] ;
		}
        return originalBoard;
    }

/*
 * try all four moves possible on the current board
 * keep track of the score
 * which ever move gives the highest score,
 * return that move
 * 
 */
    public  String tryRandomMoves(){
        String [] firstMoves = {"LEFT", "RIGHT", "UP", "DOWN", };
        int maxScore = 0;
        String bestMove = "";
        String randomMove = "";

        for(int i = 0; i < firstMoves.length; i++){//need to keep track of original board position
            //make first move
            getOriginalBoard(originalBoard);
            myGameObject.setBoard(originalBoard);
            myGameObject.turn(firstMoves[i]);
            while(myGameObject.getCanStillPlay()){//execute random moves till loss
                 Random random = new Random();
                String [] moves = {"LEFT", "RIGHT", "UP", "DOWN", };
                randomMove = moves[random.nextInt(moves.length)];
                try{
                    myGameObject.turn(randomMove);
                }
                catch(Exception e){

                }
                if(myGameObject.getScore(myGameObject.board) > maxScore){//if the left turn has highest score, return left as move
                    maxScore = myGameObject.getScore(myGameObject.board);//keep track of highest score
                    bestMove = firstMoves[i];
                }
            
                if(i == 6){                         //when the first move makes no diff, it keeps the getCanStillPlay variable as false
                    System.out.println(randomMove);//while loop doesn't execute
                    myGameObject.printBoard();
                }
            }
          //  System.out.println("move is " + firstMoves[i] + " this moves score is " + myGameObject.getScore(myGameObject.board));
            
        }
        return bestMove;
    }
}
