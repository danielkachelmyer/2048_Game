    public class learningObject{
        public int [] board;
        public String move;
        int score;
    
        public learningObject(int [] board, String move){
            this.board = board;
            this.move = move;
        }
    
        public int [] getBoard(){
            return board;
        }
    
        public String getMove(){
            return move;
        }
    
        public int getScore(){
            int sum = 0;
            for(int i = 0; i < board.length; i++) {
                sum+=board[i];
            }
            return sum;
        }
    
        public void setBoard(int [] board){
            this.board = board;
        }
    
        public void setMove(String move){
            this.move = move;
        }
    
        
    }

