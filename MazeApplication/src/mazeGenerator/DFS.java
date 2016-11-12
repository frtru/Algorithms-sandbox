package mazeGenerator;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

//Application packages imports
import maze.Node;
import maze.Maze;
import maze.entity.Type;
import maze.entity.Checkpoint;

public class DFS extends MazeGenerator{
 
	public void init() {
		m_mazeWidth  = Maze.INSTANCE.getWidth();
		m_mazeHeight = Maze.INSTANCE.getHeight();
		
		// Select a random starting node
		m_currentNode = Maze.INSTANCE.getNodeAt(
				m_rand.nextInt(m_mazeHeight), 
				m_rand.nextInt(m_mazeWidth));
	}
	
	@Override
	public boolean next() {
		boolean hasNextIteration = false;
		m_nodeBuffer = m_currentNode.getNextUnvisitedNeighbors(); // Results are stored into m_nodeBuffer
		
		if (!m_nodeBuffer.isEmpty()) {
			m_currentNode.visit(Type.FLOOR);
			m_visitedNodes.push(m_currentNode);		

			Integer randomDirection = (Integer) m_nodeBuffer.keySet().toArray()[m_rand.nextInt(m_nodeBuffer.size())];
			Node neighbor = m_nodeBuffer.get(randomDirection);			

			if (!neighbor.isVisited()) {
				HashMap<Integer, Node> neighbors = m_currentNode.getNeighbors();
				Node wall = neighbors.get(randomDirection);
				wall.visit(Type.FLOOR); // Visit intermediate node (node between current and next since we move by 2)

				neighbor.visit(Type.FLOOR); // Visit node that is 2 tiles from currentNode
				m_visitedNodes.push(neighbor);	
				
				m_currentNode = neighbor; // Next neighbor becomes the current node
			}				
			hasNextIteration = true;
		}
		else if (!m_visitedNodes.isEmpty()) { // Backtrack to most recent unvisited node into neighbors
			Node nextBranchNode = null;
			do {
				nextBranchNode = m_visitedNodes.pop();
				m_nodeBuffer = nextBranchNode.getNextUnvisitedNeighbors();				
			} while(m_nodeBuffer.isEmpty() && !m_visitedNodes.isEmpty()); 
			
			if (nextBranchNode != null) {
				m_currentNode = nextBranchNode;
			}
			hasNextIteration = true;
		}
		return hasNextIteration;
	}

	@Override
	public void end() {
		int width = Maze.INSTANCE.getWidth(),
			height= Maze.INSTANCE.getHeight();
		for (int i=0; i < width; ++i ) {
			for (int j=0; j < height; ++j) {
				Node node = Maze.INSTANCE.getNodeAt(i, j);
				if (node.getType() == Type.UNKNOWN) {
					node.visit(Type.WALL);
				}
			}
		}
		setStartAndEndNodes();
		Maze.INSTANCE.undoVisitedNodes();
	}

	@Override
	protected void setStartAndEndNodes() {
		// Over complicated way but safe for any dimension
		int width = Maze.INSTANCE.getWidth(),
			height= Maze.INSTANCE.getHeight(),
			minimalPathLength = 2; // There's at least 1 node which is a floor one on a 2x2 grid space
	
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
	
	Random 		m_rand 					= new Random();
	Node 		m_currentNode 			= null;
	Stack<Node> m_visitedNodes 			= new Stack<Node>();
	HashMap<Integer,Node> m_nodeBuffer 	= new HashMap<Integer,Node>();

	int 		m_mazeWidth = 0; 
	int			m_mazeHeight = 0;

}
