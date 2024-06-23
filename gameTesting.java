public class gameTesting {
    public static void main(String[] args) {

        int a1 = 2, a2 = 2, a3 = 0, a4 = 0, 
            b1 = 0, b2 = 2, b3 = 0, b4 = 2, 
            c1 = 4, c2 = 0, c3 = 0, c4 = 0, 
            d1 = 2, d2 = 2, d3 = 2, d4 = 2;
        int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
        int mySize = 4;
        String myDirection = "LEFT";
        printBoard(board);
        System.out.println("moving " + myDirection);
        move(board, mySize, myDirection);
        printBoard(board);
    }



    //----
    public static void move(int [] board, int boardSize, String direction) {
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
                    
		switch(direction) {
		case "LEFT":    startingIndex = topRightCornerIndex;
                        endingIndex =  bottomRightCornerIndex + boardSize;
					    increment = -1;                                     //increment is 1 or -1 for L/R, boardSize or -boardSize for U/D
                        flag = (boardSize - 1) * -1;                        //to check where to end inner loop
                        rowCollum = boardSize;
		break;
		
        case "RIGHT":   startingIndex = topLeftCornerIndex;
                        endingIndex = bottomLeftCornerIndex + boardSize;
                        increment = 1;
                        flag = (boardSize - 1);
                        rowCollum = boardSize;
		break; 
		
		case "UP":      startingIndex = bottomLeftCornerIndex;
                        endingIndex = bottomRightCornerIndex + 1;
                        increment = (boardSize * -1);
                        flag = (boardSize * (boardSize - 1)) * - 1;
                        rowCollum = 1;
		break;
		
		case "DOWN":    startingIndex = topLeftCornerIndex;
                        endingIndex = topRightCornerIndex + 1;
                        increment = boardSize;
                        flag = (boardSize * (boardSize - 1));
                        rowCollum = 1;
		}
        int i;
		for(int j = startingIndex; j != endingIndex; j += rowCollum ) {
			i = j;

            while(i != j + flag){
                if(board [i + increment] == board[i]){  //check adjacent element if equal to current element
                    board[i + increment] = board[i + increment] * 2;
                    board[i] = 0;
                }

                i += increment;
            }
            for(int x = 0; x < boardSize; x++){//repeat for how long board is
                i = j;
                while(i != j + flag){
                    
                    if(board[i + increment] == 0){  //check adjacent element if 0
                        board[i + increment] = board[i];
                        board[i] = 0;
                    }
                   
                    i += increment;
                }
            }
           

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


