package mazeSolver;

//import java.util.HashMap;
import java.util.Random;
//import java.util.Stack;

//Application packages imports
import core.Algo;
import core.utils.Position;
import maze.Maze;
import maze.Node;
import maze.entity.Type;

public class AStar extends Algo{

	@Override
	public void init() {
		m_heuristicWeight = 2;
		m_currentNode = Maze.INSTANCE.getStartNode();
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
/*		Maze.INSTANCE.getNodeAt(
				m_rand.nextInt(m_mazeHeight), 
				m_rand.nextInt(m_mazeWidth)).setCheckpoint(Checkpoint.POTENTIAL_SOLUTION);
				
*/		
		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	private int	getFScore(Node n) {
		return getGScore(n) + m_heuristicWeight*getHScore(n);
	}
	
	// Returns the cost of node n
	private int getGScore(Node n) {
		// TODO: Should return the length of the path when reaching node n
		// Or maybe just the distance with the start
		return getDistanceBetweenNodes(n, Maze.INSTANCE.getStartNode());
		//return 0;
	}

	// Returns the heuristic of node n
	private int getHScore(Node n) {
		// Return distance from n to end node
		return getDistanceBetweenNodes(n, Maze.INSTANCE.getEndNode());
	}
	
	private int getDistanceBetweenNodes(Node n1, Node n2) {
		Position p1 = n1.getPosition(), 
				 p2 = n2.getPosition();
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	
	Random 		m_rand 					= new Random();
	Node 		m_currentNode 			= null;

	int 		m_heuristicWeight; 
}
