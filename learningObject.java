    public class learningObject{
        public int [] board;
        public String bestMove;
    
        public learningObject(int [] board){
            this.board = board;
            this.bestMove = getBestMove();
        }
    
        public int [] getBoard(){
            return board;
        }
    
        /*
         * based off the current board
         * return the move that leads to the highest score
         */
        public String getBestMove(){
            String returnMove = "";
            gameObject AIGame = new gameObject();//create seperate game object
            AIGame.setBoard(board);             
            Trial AITrial = new Trial(AIGame);  //run a trial to determine best move
            returnMove = AITrial.executeTrial(22200);
            return returnMove;
        }
    
    
    
        
    }

