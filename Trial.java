public class Trial {
    
    private int [] board;
    private int highestPiece;
    private boolean corner;
    private int fitnessScore;
    private int boardSize;

    Trial(int board[], int boardSize){
        this.board = new int[board.length];
        System.arraycopy(board, 0, this.board, 0, board.length);
        
        // Initialize other variables
        setHighestPiece();
        setTopLef();
        setFitnessScore(); 
        this.boardSize = boardSize;
    }


    public void setHighestPiece(){
        this.highestPiece = getHighestPiece();

    }
        
    public void setTopLef(){
        this.corner = getCorner();
    }

    public void setFitnessScore(){
        this.fitnessScore = getFitnessScore();
    }

    public int getHighestPiece(){//get the highest value of all the values in the array
        int max = board[0];
        for(int i = 0; i < board.length; i++){
            if(board[i] > max){
                max = board[i];
            }
        }
        return max;
    }


    public boolean getCorner(){
    int topLeftCornerIndex = 0;
	int topRightCornerIndex = boardSize - 1;
	int bottomRightCornerIndex = (boardSize * boardSize) - 1;
	int bottomLeftCornerIndex = bottomRightCornerIndex - boardSize + 1;
	
        if( highestPiece == board[topLeftCornerIndex] ||//if the highest value piece is in a corner corner is true
            highestPiece == board[topRightCornerIndex] ||
            highestPiece == board[bottomLeftCornerIndex] ||
            highestPiece == board[bottomRightCornerIndex]){
                corner = true;
            return true;
        }
        corner = false;
        return false;
    }

    public int getFitnessScore(){
        int fitnessScore = 0;
        //if the highest value piece is in the top left corner add x amt to FS
        if(corner){
            fitnessScore += 20;
        }
        fitnessScore += highestPiece;

        return fitnessScore;
    }
}
