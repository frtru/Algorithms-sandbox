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
		// Select a random starting node
		m_currentNode = Maze.INSTANCE.getNodeAt(
				m_rand.nextInt(Maze.INSTANCE.getHeight()), 
				m_rand.nextInt(Maze.INSTANCE.getWidth()));
	}
	
	@Override
	public boolean next() {
		boolean hasNextIteration = false;
		m_nodeBuffer = getNextUnvisitedNeighbors(m_currentNode); // Results are stored into m_nodeBuffer
		
		if (!m_nodeBuffer.isEmpty()) {
			m_currentNode.setType(Type.FLOOR);
			m_visitedNodes.push(m_currentNode);		

			Integer randomDirection = getRandomDirection();
			Node neighbor = m_nodeBuffer.get(randomDirection);			

			if (!isVisited(neighbor)) {
				HashMap<Integer, Node> neighbors = m_currentNode.getNeighbors();
				Node wall = neighbors.get(randomDirection);
				wall.setType(Type.FLOOR); // Visit intermediate node (node between current and next since we move by 2)

				neighbor.setType(Type.FLOOR); // Visit node that is 2 tiles from currentNode
				m_visitedNodes.push(neighbor);	
				
				m_currentNode = neighbor; // Next neighbor becomes the current node
			}				
			hasNextIteration = true;
		}
		else if (!m_visitedNodes.isEmpty()) {
			backtrackUnvisitedNodes();
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
					node.setType(Type.WALL);
				}
			}
		}
		setStartAndEndNodes();
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
	
	private boolean isVisited(Node node) { return (node.getType() == Type.FLOOR); }
	
	private Integer getRandomDirection() {
		return (Integer) m_nodeBuffer.keySet().toArray()[m_rand.nextInt(m_nodeBuffer.size())];
	}
	
	private void backtrackUnvisitedNodes() {
		 // Backtrack to most recent unvisited node into neighbors
		Node nextBranchNode = null;
		do {
			nextBranchNode = m_visitedNodes.pop();
			m_nodeBuffer = getNextUnvisitedNeighbors(nextBranchNode);				
		} while(m_nodeBuffer.isEmpty() && !m_visitedNodes.isEmpty()); 
		
		if (nextBranchNode != null) {
			m_currentNode = nextBranchNode;
		}
	}
	
	// The next 2 functions wouldn't be necessary if we were 
	// using real neighbors (since we need to move by 2)	
	/*private HashMap<Integer,Node> getUnvisitedIntermediateNeighbors(Node n) {
		HashMap<Integer,Node> unvisitedNeighbors = new HashMap<Integer,Node>();
		for (HashMap.Entry<Integer,Node> entry: n.getNeighbors().entrySet()) {
			if (!isVisited(entry.getValue())) {
				unvisitedNeighbors.put(entry.getKey(), entry.getValue());
			}
		}
		return unvisitedNeighbors;
	}*/

	private  HashMap<Integer,Node> getNextUnvisitedNeighbors(Node n) {
		HashMap<Integer,Node> nextUnvisitedNeighbors = new HashMap<Integer,Node>();
		// For each neighbors, fetch the neighbors 
		// following the direction they are according 
		// to the current node (the neighbor of the neighbor
		// in the same direction)
		for (HashMap.Entry<Integer,Node> entry: n.getNeighbors().entrySet()) {
			Node nextNeighbor = entry.getValue().getNeighborFromDirection(entry.getKey());
			if (nextNeighbor != null) {
				if (!isVisited(nextNeighbor)) {
					nextUnvisitedNeighbors.put(entry.getKey(), nextNeighbor );
				}
			}
		}
		return nextUnvisitedNeighbors;
	}
	
	Random 		m_rand 					= new Random();
	Node 		m_currentNode 			= null;
	Stack<Node> m_visitedNodes 			= new Stack<Node>();
	HashMap<Integer,Node> m_nodeBuffer 	= new HashMap<Integer,Node>();
}
