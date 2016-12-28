

public class MazeTester {
	public static void main(String args[]){
		boolean[][] a=new boolean[3][3];
		a[0][0]=true;
		a[0][1]=true;
		a[1][1]=true;
		a[2][1]=true;
		a[2][2]=true;
		MazeCoord y=new MazeCoord(0,0);
		MazeCoord x=new MazeCoord(2,2);
		Maze m=new Maze(a,y,x);
		boolean z=m.search();
		System.out.println(m.getPath());
	}


}
