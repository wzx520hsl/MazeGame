// Name:Zixi Wang
// USC loginid:zixiwang
// CS 455 PA3
// Fall 2016

import java.util.Arrays;
import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   private boolean[][] virtualMazeData; //Maze that add a virtual wall outside the original maze 
   private boolean[][] hasVisited;//Maze that record where have been visited
   private MazeCoord startLoc;
   private MazeCoord endLoc;
   private LinkedList<MazeCoord> path;//LinkedList that store the path
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc)
   {
	   //Initialize the virtual maze, they are all false at the beginning
	   virtualMazeData=new boolean[mazeData.length+2][mazeData[0].length+2];
	   for(int i=0;i<mazeData.length;i++){
		   for(int j=0;j<mazeData[0].length;j++){
			   virtualMazeData[i+1][j+1]=mazeData[i][j];
		   }
	   }
	   this.startLoc=startLoc;
	   this.endLoc=endLoc;
	   path=new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return virtualMazeData.length-2;   
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return virtualMazeData[0].length-2;  
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      return !virtualMazeData[loc.getRow()+1][loc.getCol()+1];   
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;   
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return endLoc;   
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return path;  

   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {
	   //Each time the search is called, the path and hasVisited matrix should be refreshed
	  path=new LinkedList<MazeCoord>();
	  hasVisited=new boolean[numRows()+2][numCols()+2];
      return search(startLoc.getRow()+1,startLoc.getCol()+1);
      
     
   }
   /**
   	 Return the string representation of the maze, it is used for debugging
   	 @return String representaion of the matrix
    */ 
   public String toString(){
	   String r="";
	   for(int i=0;i<virtualMazeData.length;i++){
		   r+=Arrays.toString(virtualMazeData[i])+"\n";
	   }
	   return r;
   }
   /**
   	 The recursive backtracking algorithm to find a path.
   	 @param the numRows at the current position
   	 @param the numColums at the current position
   	 @return whether path was found.
    */   
   private boolean search(int y,int x){
	   if(!virtualMazeData[y][x]){
		   return false;
	   }
	   if(hasVisited[y][x]){
		   return false;
	   }
	   if(y==endLoc.getRow()+1&&x==endLoc.getCol()+1){
		   path.addLast(new MazeCoord(y-1,x-1));
		   return true;
	   }
	   hasVisited[y][x]=true;
	   if(search(y-1,x)){
		   path.addLast(new MazeCoord(y-1,x-1));
		   return true;
	   }else if(search(y,x+1)){
		   path.addLast(new MazeCoord(y-1,x-1));
		   return true;
	   }else if(search(y+1,x)){
		   path.addLast(new MazeCoord(y-1,x-1));
		   return true;
	   }else if(search(y,x-1)){
		   path.addLast(new MazeCoord(y-1,x-1));
		   return true;
	   }else{
		   return false;
	   }
		 
   }

}