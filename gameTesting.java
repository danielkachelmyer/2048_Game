public class gameTesting {
    public static void main(String[] args) {
        System.out.println("hello world");


        int a1 = 2, a2 = 2, a3 = 0, a4 = 0, 
            b1 = 0, b2 = 2, b3 = 0, b4 = 2, 
            c1 = 4, c2 = 0, c3 = 0, c4 = 0, 
            d1 = 2, d2 = 2, d3 = 2, d4 = 2;
        int [] board  = {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
        int mySize = 4;
        String myDirection = "LEFT";
        printBoard(board);
        System.out.println();
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
        int iterate = 0;
                    
		switch(direction) {
		case "LEFT": startingIndex = topRightCornerIndex;
                    endingIndex =  bottomRightCornerIndex + boardSize;
					increment = -1;
                    iterate = -3;
                    rowCollum = boardSize;
                   // System.out.println("left");
                    //increment being -1 for left means right to left.
		break;
		
		case "RIGHT": startingIndex = topLeftCornerIndex;
                    endingIndex = bottomRightCornerIndex + boardSize;
                    increment = 1;
                    iterate = 3;
                    rowCollum = boardSize;
		break; 
		
		case "UP": startingIndex = 12;
					increment = -4;
		break;
		
		case "DOWN": startingIndex = 0;
					increment = 4;
		}
        int i;
		for(int j = startingIndex; j != endingIndex; j += rowCollum ) {
			i = j;
            for(int x = 0; x < boardSize; x++){//repeat for how long board is
                i = j;
                while(i != j + iterate){
          
                    if(board[i + increment] == 0){  //check adjacent element if 0
                        board[i + increment] = board[i];
                        board[i] = 0;
                    }
                    if(board [i + increment] == board[i]){  //check adjacent element if equal to current element
                        board[i + increment] = board[i + increment] * 2;
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


