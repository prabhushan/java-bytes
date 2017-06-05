package com.prabhu.algorithms.week4;

public class Board {

	   int[][] board ;
	   int dimension;
	   int zeroRow;
	   int zeroCol;
	   int hammingValue=0;
	   int manValue=0;
	    public Board(int[][] blocks){
	    	board = blocks;
	    	for(int i=0;i<board.length;i++){
	    		for(int j=0;j<board.length;j++){
	    			if(board[i][j]==0){
	    				zeroRow = i;
	    				zeroCol = j;
	    				break;
	    			}
	    		}
	    	}
	    	System.out.println(zeroRow +","+zeroCol);
	    	// construct a board from an n-by-n array of blocks
	    }
	                                           // (where blocks[i][j] = block in row i, column j)
	    public int dimension() {
	    	if(board!=null && board.length>0){
	    		return dimension =  board.length;
	    	}
			return 0;
	    	// board dimension n
	    }
	    public int hamming()  {
	    	for(int i=0;i<board.length;i++){
	    		for(int j=0;j<board.length;j++){
	    			if((i==zeroRow && j==zeroCol)){
	    				continue;
	    			}
	    			if(board[i][j]!=getGoalValue(i, j) ){
	    		    	System.out.println(i + ","+j +"-->"+getGoalValue(i, j));

	    				hammingValue++;
	    			}
	    		}
	    	}
			return hammingValue;
	    	// number of blocks out of place
	    }
	    public int manhattan()  {
			return 0;
	    	// sum of Manhattan distances between blocks and goal
	    }
	    public boolean isGoal()  {
			return false;
	    	// is this board the goal board?
	    }
	    public Board twin()   {
			return null;
	    	// a board that is obtained by exchanging any pair of blocks
	    }
	    public boolean equals(Object y) {
			return false;
	    	// does this board equal y?
	    }
	    public Iterable<Board> neighbors()   {
			return null;
	    	// all neighboring boards
	    }
	    public String toString()  {
			return null;
	    	// string representation of this board (in the output format specified below)
	    }
	    
	    private int getGoalValue(int row,int col){
	    	int goalValue = (dimension*row)+(col+1);
	    	//System.out.println(row + ","+col +"-->"+goalValue);
	    	return goalValue;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
