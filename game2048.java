
/**
 * @author Daniel Kachelmyer
 * @version 03/24/2023  2025
 * Create the game 2048 using
 * java code and only console
 * and user input to make moves
 * and print text
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class game2048 {
	public static void main (String args[]){
		/*
		 * create booleans to check if moves
		 * can be valid and if all are invalid
		 * then the game is over
		*/
		boolean moveLeftInvalid = false;
		boolean moveRightInvalid = false;
		boolean moveUpInvalid = false;		
		boolean moveDownInvalid = false;
		//message is whats printed if they cannot make that move
		String message = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the game 2048!");
		//initialize board values, order it this way to make debugging easier
		int a1 = 0, a2 = 0, a3 = 0, a4 = 0, 
			b1 = 0, b2 = 0, b3 = 0, b4 = 0, 
			c1 = 0, c2 = 0, c3 = 0, c4 = 0, 
			d1 = 0, d2 = 0, d3 = 0, d4 = 0;
		int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
		//randomize first two digits
		Randomize(board);
		Randomize(board);
		printBoard(board);
		int userInput = 0;
		
		gameGraphics showBoard = new gameGraphics(board);
//All of this was for the console input and output----------------------------------------------------{		
		//first while loop gives users the option to quit by entering -1 
		while(userInput != -1) {
			System.out.println("Enter your move, 1 to move left, 2 to move right, 3 to move down, 4 to move up or -1 to quit");
			//set up invalid boolean to assist with catching illegal argument exceptions
			boolean invalid = true;
			//second while loop implements a try catch statement so the user must only enter an integer
			while(invalid)
			try {
				userInput = input.nextInt();
				invalid = false;
			}
			catch(Exception e) {
				input.nextLine();
				System.out.println("enter an integer");
				
			}
			/*
			 * switch statement makes certain moves based
			 * off user inputs, default case is an integer
			 * that doesn't apply to the suggested input
			 * 
			*/
			switch(userInput){
			case -1:
				break;
			case 1://move left
					moveLeft(board);
					message = "cannot move left";
				break;
			case 2://move right
				moveRight(board);
				message = "cannot move right";
				break;
			case 3://move down
				moveDown(board);
				message = "cannot move down";
				break;
			case 4://move up
				moveUp(board);
				message = "cannot move up";
				break;
			default:
				System.out.println("enter valid number");
			}
			/*
			 * if a move can be made randomize 
			 * will execute because there will 
			 * be one free space to insert a random 
			 * number, if not the catch statement
			 * executes and let the user knows
			 * which move they can't make, will
			 * reset once they correct the board
			*/
			try {
				Randomize(board);
			}
			catch(Exception e){
				System.out.println(message);
				if(message.equals("cannot move left")) {
					moveLeftInvalid = true;	
				}
				if(message.equals("cannot move right")) {
					moveRightInvalid = true;	
				}
				if(message.equals("cannot move up")) {
					moveUpInvalid = true;	
				}
				if(message.equals("cannot move down")) {
					moveDownInvalid = true;	
				}
			}
			//prints board and score each move
			if(userInput!= -1) {
				printBoard(board);
			}
			System.out.println("score: " + getScore(board));
			//if user cannot make any moves game over is printed and the while loop ends
			if(moveLeftInvalid && moveRightInvalid && moveUpInvalid && moveDownInvalid) {
				System.out.println("GAME OVER!");
				userInput = -1;
			}
			//calls the checkGameWon method each move to see if the user has won, does not end game
			if(checkGameWon(board)) {
				System.out.println("you won!");
				System.out.println("try and go higher");
			}

		}
		
	}
//-----------------------------------------------------------------------------------------------------------}

//-----------------------------------------------------------------------------------------------------
	/**
	 * shifts all elements to desired direction
	 * @param board the current state of the board
	 * @param boardSize the dimension of the board, size 4 for 4x4
	 * @param direction the direction the player wishes to move the pieces
	 */
public static int[] move(int [] board, int boardSize, String direction) {
	String message = "";
	
	//create copy of board to tell if there are no changes
	int [] returnBoard;
	returnBoard = Arrays.copyOf(board, board.length);
	
	
	//create corner index based off board size
	int topLeftCornerIndex = 0;
	int topRightCornerIndex = boardSize - 1;
	int bottomRightCornerIndex = (boardSize * boardSize) - 1;
	int bottomLeftCornerIndex = bottomRightCornerIndex - boardSize + 1;
	
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
					message = "left";
	break;
	
	case "RIGHT":   startingIndex = topRightCornerIndex;
					endingIndex = bottomRightCornerIndex + boardSize;
					increment = -1;
					flag = (boardSize - 1) * -1;
					rowCollum = boardSize;
					message = "right";
	break; 
	
	case "DOWN":      startingIndex = bottomLeftCornerIndex;
					endingIndex = bottomRightCornerIndex + 1;
					increment = (boardSize * -1);
					flag = (boardSize * (boardSize - 1)) * - 1;
					rowCollum = 1;
					message = "down";
	break;
	
	case "UP":    startingIndex = topLeftCornerIndex;
					endingIndex = topRightCornerIndex + 1;
					increment = boardSize;
					flag = (boardSize * (boardSize - 1));
					rowCollum = 1;
					message = "down";
	}
	int i;
	int current;
	int adjacent;
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
			return null;//return null to let user know you can't move that direction
		}
		else {
		  return board;
		}
}

	
//-----------------------------------------------------------------------------------------------------
	
	/**
	 * shifts all elements from right
	 * to left, adding if the elements
	 * are the same and shifting over if 
	 * there is a zero next to it
	 * @param board the current state of the
	 * board
	 */
	public static int[] moveLeft(int [] board) {
		int [] returnBoard;
		returnBoard = Arrays.copyOf(board, board.length);
		ArrayList<Integer> firstRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> secondRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> thirdRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> fourthRowAdded = new ArrayList<Integer>();
		//row 1
		for(int i = 3; i > 0; i--) {//add same number
			if(board[i] == board[i-1] && !firstRowAdded.contains(i)) {
				board[i - 1] = 2 * board[i];
				board[i] = 0;
				firstRowAdded.add(i - 1);
			}
			if(board[i - 1] == 0) {//shift zeros
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 3; i > 0; i--) {//shift zeros
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		//row 2
		for(int i = 7; i > 4; i--) {
			if(board[i] == board[i-1] && !secondRowAdded.contains(i)) {
				board[i - 1] = 2 * board[i];
				board[i] = 0;
				secondRowAdded.add(i - 1);
			}
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 7; i > 4; i--) {
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		//row 3
		for(int i = 11; i > 8; i--) {
			if(board[i] == board[i-1] && !thirdRowAdded.contains(i)) {
				board[i - 1] = 2 * board[i];
				board[i] = 0;
				thirdRowAdded.add(i - 1);
			}
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 11; i > 8; i--) {
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		//row 4
		for(int i = 15; i > 12; i--) {
			if(board[i] == board[i-1] && !fourthRowAdded.contains(i)) {
				board[i - 1] = 2 * board[i];
				board[i] = 0;
				fourthRowAdded.add(i - 1);
			}
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 15; i > 12; i--) {
			if(board[i - 1] == 0) {
				board[i - 1] = board[i];
				board[i] = 0;
			}
		}

		boolean sameBoard = Arrays.equals(board, returnBoard);
		if(sameBoard) {
			//nothing changes
			return null;
		}
		else {
		  return board;
		}
	}
		
	/**
	 * shifts all elements from left
	 * to right, adding if the elements
	 * are the same and shifting over if 
	 * there is a zero next to it
	 * @param board the current state of the
	 * board
	 */
	public static int [] moveRight(int [] board) {
		int [] returnBoard;
		returnBoard = Arrays.copyOf(board, board.length);
		ArrayList<Integer> firstRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> secondRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> thirdRowAdded = new ArrayList<Integer>();
		ArrayList<Integer> fourthRowAdded = new ArrayList<Integer>();
		//first row
		for(int i = 0; i < 3; i++) {//check to be added
			if(board[i+1] == board[i] && !firstRowAdded.contains(i)) {
				board[i + 1] = 2 * board[i];
				board[i] = 0;
				firstRowAdded.add(i + 1);
			}
			if(board[i + 1] == 0) {//shift rows if zero
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 0; i < 3; i++) {//shift rows if zero
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		//second row
		for(int i = 4; i < 7; i++) {
			if(board[i+1] == board[i] && !secondRowAdded.contains(i)) {
				board[i + 1] = 2 * board[i];
				board[i] = 0;
				secondRowAdded.add(i+ 1);
			}
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 4; i < 7; i++) {
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		} 
		//third row
		for(int i = 8; i < 11; i++) {
			if(board[i+1] == board[i] && !thirdRowAdded.contains(i)) {
				board[i + 1] = 2 * board[i];
				board[i] = 0;
				thirdRowAdded.add(i + 1);
			}
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 8; i < 11; i++) {
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		//fourth row
		for(int i = 12; i < 15; i++) {
			if(board[i+1] == board[i] && !fourthRowAdded.contains(i)) {
				board[i + 1] = 2 * board[i];
				board[i] = 0;
				fourthRowAdded.add(i + 1);
			}
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 12; i < 15; i++) {
			if(board[i + 1] == 0) {
				board[i + 1] = board[i];
				board[i] = 0;
			}
		}
		
		boolean sameBoard = Arrays.equals(board, returnBoard);
		if(sameBoard) {
			//nothing changes
			return null;
		}
		else {
		  return board;
		}
	}
	
	/**
	 * shifts all elements from top
	 * to down, adding if the elements
	 * are the same and shifting down if 
	 * there is a zero next to it
	 * @param board the current state of the
	 * board
	 */
	public static int [] moveUp(int [] board) {
		int [] returnBoard;
		returnBoard = Arrays.copyOf(board, board.length);
		ArrayList<Integer> firstColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> secondColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> thirdColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> fourthColumnAdded = new ArrayList<Integer>();
		//first column
		for(int i = 12; i > 0; i-=4) {
			if(board[i - 4] == board[i] && !firstColumnAdded.contains(i)) {
				board[i - 4] = 2 * board[i];
				board[i] = 0;
				firstColumnAdded.add(i - 4);
			}
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 12; i > 0; i-=4) {
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		//second column
		for(int i = 13; i > 1; i-=4) {
			if(board[i - 4] == board[i] && !secondColumnAdded.contains(i)) {
				board[i - 4] = 2 * board[i];
				board[i] = 0;
				secondColumnAdded.add(i - 4);
			}
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 13; i > 1; i-=4) {
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		//third column
		for(int i = 14; i > 2; i-=4) {
			if(board[i - 4] == board[i] && !thirdColumnAdded.contains(i)) {
				board[i - 4] = 2 * board[i];
				board[i] = 0;
				thirdColumnAdded.add(i - 4);
			}
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 14; i > 2; i-=4) {
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		//fourth column
		for(int i = 15; i > 3; i-=4) {
			if(board[i - 4] == board[i] && !fourthColumnAdded.contains(i)) {
				board[i - 4] = 2 * board[i];
				board[i] = 0;
				fourthColumnAdded.add(i - 4);
			}
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 15; i > 3; i-=4) {
			if(board[i - 4] == 0) {
				board[i - 4] = board[i];
				board[i] = 0;
			}
		}
		boolean sameBoard = Arrays.equals(board, returnBoard);
		if(sameBoard) {
			//nothing changes
			return null;
		}
		else {
		  return board;
		}
	}
	/**
	 *  shifts all elements from bottom
	 * to top, adding if the elements
	 * are the same and shifting up if 
	 * there is a zero next to it
	 * @param board the current state of the
	 * board
	 */
	public static int [] moveDown(int [] board) {
		int [] returnBoard;
		returnBoard = Arrays.copyOf(board, board.length);
		ArrayList<Integer> firstColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> secondColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> thirdColumnAdded = new ArrayList<Integer>();
		ArrayList<Integer> fourthColumnAdded = new ArrayList<Integer>();
		//first column
		for(int i = 0; i < 12; i+=4) {
			if(board[i + 4] == board [i] && !firstColumnAdded.contains(i)) {
				board[i + 4] = 2 * board[i];
				board[i] = 0;
				firstColumnAdded.add(i + 4);
			}
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 0; i < 12; i+=4) {
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		//second column
		for(int i = 1; i < 13; i+=4) {
			if(board[i + 4] == board [i] && !secondColumnAdded.contains(i)) {
				board[i + 4] = 2 * board[i];
				board[i] = 0;
				secondColumnAdded.add(i + 4);
			}
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 1; i < 13; i+=4) {
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		//third column
		for(int i = 2; i < 14; i+=4) {
			if(board[i + 4] == board [i] && !thirdColumnAdded.contains(i)) {
				board[i + 4] = 2 * board[i];
				board[i] = 0;
				thirdColumnAdded.add(i + 4);
			}
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 2; i < 14; i+=4) {
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		//fourth column
		for(int i = 3; i < 15; i+=4) {
			if(board[i + 4] == board [i] && !fourthColumnAdded.contains(i)) {
				board[i + 4] = 2 * board[i];
				board[i] = 0;
				fourthColumnAdded.add(i + 4);
			}
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		for(int i = 3; i < 15; i+=4) {
			if(board[i + 4] == 0) {
				board[i + 4] = board[i];
				board[i] = 0;
			}
		}
		boolean sameBoard = Arrays.equals(board, returnBoard);
		if(sameBoard) {
			//nothing changes
			return null;
		}
		else {
		  return board;
		}
	}
	/**
	 * Takes all the zeores's left on the board
	 * stores them in an array list
	 * and then using a random object selects
	 * one of those index to become a two or four, (10% chance to be 4, 90% chance to be 2) => (HAVE NOT DONE THIS YET)
	 * @param board
	 */
	public static int [] Randomize(int [] board) {
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
	

	/**
	 * prints the board in a 4 by 4
	 * grid
	 * @param printBoard
	 */
	public static void printBoard(int [] printBoard) {
		System.out.println(printBoard[0] + "  " + printBoard[1] + "  " + printBoard[2]+ "  " + printBoard[3]);
		System.out.println(printBoard[4] + "  " + printBoard[5] + "  " + printBoard[6]+ "  " + printBoard[7]);
		System.out.println(printBoard[8] + "  " + printBoard[9] + "  " + printBoard[10]+ "  " + printBoard[11]);
		System.out.println(printBoard[12] + "  " + printBoard[13] + "  " + printBoard[14]+ "  " + printBoard[15]);
		System.out.println("--------------");	
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

	/**
	 * uses a for loop to go through
	 * each value of the board and checks
	 * if it least one value is greater
	 * than or equal to 2048, if true
	 * makes the boolean gameWon true
	 * and returns
	 * @param current board values
	 * @return boolean, true if game won
	 * false if game hasn't been declared 
	 * won
	*/
	public static boolean checkGameWon(int [] board) {
		boolean gameWon = false;
		for(int i = 0; i < board.length; i++) {
			if(board[i] >= 2048) {
				gameWon = true;
			}
		}
		return gameWon;
	}

}



