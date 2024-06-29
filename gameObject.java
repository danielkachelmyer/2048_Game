import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class gameObject{
    private int [] board;
    private boolean canStillplay;
    private boolean leftInvalid;
    private boolean rightInvalid;
    private boolean upInvalid;
    private boolean downInvalid;
    private int score;
    
    gameObject(){
        this.board = getBoard();
        this.leftInvalid = false;
        this.rightInvalid = false;
        this.upInvalid = false;
        this.downInvalid = false;
        this.score = getScore(board);
        this.canStillplay = getCanStillPlay();

    }

    /*
     * 
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, 
            b1 = 0, b2 = 0, b3 = 0, b4 = 0, 
            c1 = 0, c2 = 0, c3 = 0, c4 = 0, 
            d1 = 0, d2 = 0, d3 = 0, d4 = 0;
     */
    public int[] getBoard(){
        int a1 = 2, a2 = 4, a3 = 2, a4 = 4, 
            b1 = 4, b2 = 2, b3 = 4, b4 = 2, 
            c1 = 2, c2 = 4, c3 = 2, c4 = 4, 
            d1 = 4, d2 = 2, d3 = 4, d4 = 2;
        int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
		return board;
    }

   /**
	 * adds up every value in the board
	 * to get the score
	 * @param boardValues current board values
	 * @return sum of all values giving
	 * your score
	 */
	public static int getScore(int [] boardValues) {
		int sum = 0;
		for(int i = 0; i < boardValues.length; i++) {
			sum+=boardValues[i];
		}
		return sum;
	}
/*
 * check if a new turn can be played
 * if a player can't move in any direction 
 * canStillPlay is false and the player has lost
 */
    public boolean getCanStillPlay(){
        if(leftInvalid && rightInvalid && upInvalid && downInvalid){
            return false;
        }
        return true;
    }

    public boolean getLeftInvalid(){
        return leftInvalid;
    }



    	/**
	 * shifts all elements to desired direction
     * if a user has successfully made a move
     * assume all directions to be valid again
	 * @param board the current state of the board
	 * @param boardSize the dimension of the board, size 4 for 4x4
	 * @param direction the direction the player wishes to move the pieces
	 */
public int[] move(int [] board, int boardSize, String direction) {
	
	//create copy of board to tell if there are no changes
	int [] returnBoard;
	returnBoard = Arrays.copyOf(board, board.length);
	
	
	//create corner index based off board size
	int topLeftCornerIndex = 0;
	int topRightCornerIndex = boardSize - 1;
	int bottomRightCornerIndex = (boardSize * boardSize) - 1;
	int bottomLeftCornerIndex = bottomRightCornerIndex - boardSize + 1;
	//values used to be used in the for loops to move the elements of the array around
	int startingIndex = 0;
	int endingIndex = 0;
	int increment = 0;
	int rowCollum = 0;
	int flag = 0;
				
	switch(direction) {//when shifting elements start on the far (direction) side, ex move left, start at far left
	case "LEFT":    startingIndex = topLeftCornerIndex;
					endingIndex =  bottomLeftCornerIndex + boardSize;
					increment = 1;                                     //increment is 1 or -1 for L/R, boardSize or -boardSize for U/D
					flag = (boardSize - 1);                        //to check where to end inner loop
					rowCollum = boardSize;
	break;
	
	case "RIGHT":   startingIndex = topRightCornerIndex;
					endingIndex = bottomRightCornerIndex + boardSize;
					increment = -1;
					flag = (boardSize - 1) * -1;
					rowCollum = boardSize;
	break; 
	
	case "DOWN":      startingIndex = bottomLeftCornerIndex;
					endingIndex = bottomRightCornerIndex + 1;
					increment = (boardSize * -1);
					flag = (boardSize * (boardSize - 1)) * - 1;
					rowCollum = 1;
	break;
	
	case "UP":    startingIndex = topLeftCornerIndex;
					endingIndex = topRightCornerIndex + 1;
					increment = boardSize;
					flag = (boardSize * (boardSize - 1));
					rowCollum = 1;
	}
	int i;
	int current;//current element your on
	int adjacent;//element that is next to current regarding direction
	int counterFlag = boardSize / 2;
	int counter;
	for(int j = startingIndex; j != endingIndex; j += rowCollum ) {
		i = j;
		counter = 0;
		for(int x = 0; x < boardSize; x++){//repeat for how long board is
			i = j;
			while(i != j + flag){
				current = i;
				adjacent = i + increment;
				if(board [adjacent] == board[current] && counter <= counterFlag){  //check adjacent element if equal to current element
					board[current] = board[current] * 2;
					board[adjacent] = 0;
					counter++;
				}

				
				if(board[current] == 0){  //check if current element equals 0
					board[current] = board[adjacent];
					board[adjacent] = 0;
				}
			   
				i += increment;
			}
		   
		}
	   

	}
	boolean sameBoard = Arrays.equals(board, returnBoard);
		if(sameBoard) {
			//nothing changes
            switch(direction){
                case "LEFT":leftInvalid = true;
                break;
                case "RIGHT":rightInvalid = true;
                break;
                case "UP":upInvalid = true;
                break;
                case "DOWN":downInvalid = true;
                break;
            }
			return null;//return null to let user know you can't move that direction
		}
		else {//assume because you were able to move you can move in another direction
        leftInvalid = false;
        rightInvalid = false;
        upInvalid = false;
        downInvalid = false;
		  return board;
		}
}

/**
	 * Takes all the zeores's left on the board
	 * stores them in an array list
	 * and then using a random object selects
	 * one of those index to become a two or four, (10% chance to be 4, 90% chance to be 2)
	 * @param board the current board
*/
public int [] Randomize(int [] board) {
		int [] returnArray;
		int randomInteger =-1;
		Random random = new Random();
		ArrayList<Integer> zeroes = new ArrayList<Integer>();
		for(int i = 0; i < board.length; i++) {
			if(board[i] == 0) {
				zeroes.add(i);
			}
		}
		int randomNumber = random.nextInt(10);
		int randomZeroesIndex = random.nextInt(zeroes.size());
		if(randomNumber < 9) {
			randomInteger = 2;
			board[zeroes.get(randomZeroesIndex)] = 2;
		}
		else {
			randomInteger = 4;
			board[zeroes.get(randomZeroesIndex)] = 4;
		}
		// [index, value]
		int [] returnSet = {randomZeroesIndex, randomInteger};
		returnArray = Arrays.copyOf(board, board.length);
		return returnArray;
	}

/*
 * a turn should do two things
 * move the board according to the users direction
 * randomize one new element
 * if a user can't move in that direction make it so that move is invalid
 */
public void turn(String direction){
    try{
        move(board, 4, direction);
    }
    catch(Exception e){

    }
    try{
        Randomize(board);
    }
    catch(Exception e){

    }
}


	/**
	 * prints the board in a 4 by 4
	 * grid
	 * @param board
	 */
	public  void printBoard() {
		System.out.println(board[0] + "  " + board[1] + "  " + board[2]+ "  " + board[3]);
		System.out.println(board[4] + "  " + board[5] + "  " + board[6]+ "  " + board[7]);
		System.out.println(board[8] + "  " + board[9] + "  " + board[10]+ "  " + board[11]);
		System.out.println(board[12] + "  " + board[13] + "  " + board[14]+ "  " + board[15]);
		System.out.println("--------------");	
	}
}

