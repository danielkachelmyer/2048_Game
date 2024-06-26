import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class gameTesting {
    public static void main(String[] args) {

        int a1 = 2, a2 = 2, a3 = 0, a4 = 0, 
            b1 = 0, b2 = 2, b3 = 0, b4 = 2, 
            c1 = 4, c2 = 0, c3 = 0, c4 = 0, 
            d1 = 2, d2 = 2, d3 = 2, d4 = 2;
        int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
        int mySize = 4;
        String myDirection = "DOWN";
        printBoard(board);
        System.out.println("moving " + myDirection);
        move(board, mySize, myDirection);
        printBoard(board);
    //-------------
    //--------------
    }



    //----
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


    public static void printBoard(int board []){
        for(int i = 0; i < 15; i+=4){
            System.out.println("");
            for(int j = i; j < i + 4; j++){
                System.out.print(board[j] + " ");
            }
        }
    }
}


