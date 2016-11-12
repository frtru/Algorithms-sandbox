package mazeSolver;

//import java.util.HashMap;
import java.util.Random;
//import java.util.Stack;

//Application packages imports
import core.Algo;
import maze.Maze;
import maze.Node;
import maze.entity.Checkpoint;

public class AStar extends Algo{

	private class NodeData {
		
		
		
		private int m_f, m_g, m_h;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		m_mazeWidth  = Maze.INSTANCE.getWidth();
		m_mazeHeight = Maze.INSTANCE.getHeight();		
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		Maze.INSTANCE.getNodeAt(
				m_rand.nextInt(m_mazeHeight), 
				m_rand.nextInt(m_mazeWidth)).setCheckpoint(Checkpoint.POTENTIAL_SOLUTION);
		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	Random 		m_rand 					= new Random();
	Node 		m_currentNode 			= null;

	int 		m_mazeWidth = 0; 
	int			m_mazeHeight = 0;
	
	
}
