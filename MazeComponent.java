// Name:Zixi Wang


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
      this.maze=maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   int numRows=maze.numRows();
	   int numCols=maze.numCols();

	   Graphics2D g2 = (Graphics2D) g;
	   //Draw the broader of the maze
	   Rectangle outWall=new Rectangle(START_X,START_Y,BOX_WIDTH*numCols,BOX_HEIGHT*numRows);
	   g2.draw(outWall);
	   //Draw the walls and the entry and exit position
	   for(int j=0;j<numRows;j++){
		   for(int i=0;i<numCols;i++){
			   //The wall box
			   Rectangle box = new Rectangle(START_X+i*BOX_WIDTH, START_Y+j*BOX_HEIGHT,BOX_WIDTH , BOX_HEIGHT );
			   //The entry and exit box
			   Rectangle box2=new Rectangle(START_X+i*BOX_WIDTH+INSET, START_Y+j*BOX_HEIGHT+INSET,BOX_WIDTH-2*INSET , BOX_HEIGHT-2*INSET );
			   //Draw the walls
			   if(maze.hasWallAt(new MazeCoord(j,i))){
				   g2.setColor(Color.BLACK);
				   g2.fill(box);
				   g2.draw(box);
			   }
			   //Draw the entry position
			   if(j==maze.getEntryLoc().getRow()&&i==maze.getEntryLoc().getCol()){
				   g2.setColor(Color.YELLOW);
				   g2.fill(box2);
				   g2.draw(box2);
			   }
			   //Draw the exit position
			   if(j==maze.getExitLoc().getRow()&&i==maze.getExitLoc().getCol()){
				   g2.setColor(Color.GREEN);
				   g2.fill(box2);
				   g2.draw(box2);
			   }
			   
		   }
		   //Draw the path
		   if(!maze.getPath().isEmpty()){
		   drawPath(g2);
		   }
	   }

   }
   /**
   	 Draws the path.
   	 @param g the graphics2D context
    */   
   private void drawPath(Graphics2D g2){
	   //Get a iterator to scan the LinkedList
	   ListIterator<MazeCoord> iterator=maze.getPath().listIterator();
	   //The pointer that point to the last coordination
	   MazeCoord lastCoord=iterator.next();
	   //The pointer that point to the current coordination
	   MazeCoord currentCoord;
	   g2.setColor(Color.BLUE);


	   while(iterator.hasNext()){
		   currentCoord=iterator.next();
		   Line2D.Double line=new Line2D.Double(START_X+lastCoord.getCol()*BOX_WIDTH+BOX_WIDTH/2,START_Y+ lastCoord.getRow()*BOX_HEIGHT+BOX_HEIGHT/2, START_X+currentCoord.getCol()*BOX_WIDTH+BOX_WIDTH/2, START_Y+currentCoord.getRow()*BOX_HEIGHT+BOX_HEIGHT/2);
		   g2.draw(line);
		   lastCoord=currentCoord;
	   }
	  
   }
   
}
