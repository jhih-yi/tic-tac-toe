package Tic_Tac_Toe;
//https://github.com/DavidHurst/MiniMax-TicTacToe-Java

public class MinMax {
	char[][] place = new char[3][3];
	private static final int MAX_DEPTH = 6;
	
	public MinMax(char[][] place) {
		this.place = place;
	}
	
	public int[] getBestMove() {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	if(place[i][j]=='\0') {
            		place[i][j] = 'x';
            		int moveValue = minimax(place, MAX_DEPTH, false);
            		place[i][j] = '\0';
            		if (moveValue > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moveValue;
                    }
            	}
            }
        }
        return bestMove;
    }

	public int minimax(char[][] place, int depth, boolean isMax) {
        int boardVal = evaluateBoard(place);

        if (Math.abs(boardVal) == 10 || depth == 0 || isfull()) 
            return boardVal;
        
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (place[i][j]=='\0') {
                    	place[i][j] = 'x';
                        highestVal = Math.max(highestVal, minimax(place,depth - 1, false));
                        place[i][j] = '\0';
                    }
                }
            }
            return highestVal;
        } 
        else {
        	int lowestVal = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (place[i][j]=='\0') {
                    	place[i][j] = 'o';
                        lowestVal = Math.min(lowestVal, minimax(place,depth - 1, true));
                        place[i][j] = '\0';
                    }
                }
            }
            return lowestVal;
        }
    }
	
	private static int evaluateBoard(char[][] place) {
        int rowSum = 0;
        int Xwin = 'x' + 'x' + 'x';
        int Owin = 'o' + 'o' + 'o';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += place[i][j];
            }
            if (rowSum == Xwin) {
                return 10;
            } else if (rowSum == Owin) {
                return -10;
            }
            rowSum = 0;
        }

        rowSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += place[j][i];
            }
            if (rowSum == Xwin) {
                return 10;
            } else if (rowSum == Owin) {
                return -10;
            }
            rowSum = 0;
        }

        rowSum = 0;
        for (int i = 0; i < 3; i++) {
            rowSum += place[i][i];
        }
        if (rowSum == Xwin) {
            return 10;
        } else if (rowSum == Owin) {
            return -10;
        }

        rowSum = 0;
        int indexMax = 2;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += place[i][indexMax-i];
        }
        if (rowSum == Xwin) {
            return 10;
        } else if (rowSum == Owin) {
            return -10;
        }

        return 0;
    }
	
	
	
	public boolean isfull() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(place[i][j]=='\0')
					return false;
			}
		}
		return true;
	}
}
