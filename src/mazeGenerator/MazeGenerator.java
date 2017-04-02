package mazeGenerator;

//Application packages imports
import core.Algo;
import maze.Maze;
import maze.Node;
import maze.entity.Checkpoint;
import maze.entity.Type;

public abstract class MazeGenerator extends Algo{

	protected void setStartAndEndNodes() {
		// Over complicated way but safe for any dimension
		int width = Maze.INSTANCE.getWidth();
		int height= Maze.INSTANCE.getHeight();
		int minimalPathLength = 2;
	
		boolean startNodeFound = false,
				endNodeFound = false;
		for (int i=0; i < minimalPathLength && !(startNodeFound && endNodeFound); ++i) {
			for (int j=0; j < minimalPathLength && !(startNodeFound && endNodeFound); ++j) {
				if (!startNodeFound) {
					Node potentialStartingNode = Maze.INSTANCE.getNodeAt(i, j);
					if (potentialStartingNode.getType() == Type.FLOOR) {
						potentialStartingNode.setCheckpoint(Checkpoint.START);
						Maze.INSTANCE.setStartNode(potentialStartingNode);
						startNodeFound = true;
					}
				}
				if (!endNodeFound) {
					Node potentialEndingNode = Maze.INSTANCE.getNodeAt((width-1)-i, (height-1)-j);
					if (potentialEndingNode.getType() == Type.FLOOR) {
						potentialEndingNode.setCheckpoint(Checkpoint.END);
						Maze.INSTANCE.setEndNode(potentialEndingNode);
						endNodeFound = true;
					}		
				}
			}
		}
	}
	
/*	List of perfect maze generation algorithms
 *  Kruskal's
	Prim's
	Recursive Backtracker
	Aldous-Broder
	Growing Tree
	Hunt-and-Kill
	Wilson's
	Eller's
	Cellular Automaton (Easy)
	Recursive Division (Very Easy)
	Sidewinder (Predictable)
	Binary Tree (Flawed)*/
	
}
