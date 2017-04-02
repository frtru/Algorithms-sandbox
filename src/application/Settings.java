package application;

import mazeGenerator.DFS;
import mazeSolver.AStar;

public class Settings {
	
	// TODO: Fetch opts from user input and set maze dims, generation and solving algos
	// Maybe do a little bit of UI stuff with textbox and start button, also a result box with timing and stuff	
	
	public static final int MAZE_WIDTH 		= 75;
	public static final int MAZE_HEIGHT		= 75;
	
	public static final int NODE_WIDTH_PX  	= 5;
	public static final int NODE_HEIGHT_PX 	= 5;
	
	public static final DFS GENERATOR = new DFS();
	public static final AStar SOLVER = new AStar();
}
