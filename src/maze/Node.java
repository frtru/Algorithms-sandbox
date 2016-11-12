package maze;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import core.Direction;
import core.utils.Position;
import maze.entity.Type;
import maze.entity.Checkpoint;

public class Node {
	
	public Node(int a_X, int a_Y) {
		m_position	= new Position(a_X, a_Y);
		m_visited 	= false;
		m_type 		= Type.UNKNOWN;
		m_checkpoint = Checkpoint.NONE;
		m_neighbors = new HashMap<Integer, Node>(Direction.IDS.size(), 1.0f);	
		m_renderingComponent = new RenderableComponent(a_X, a_Y);
	}
	
	// Accessors
	public Position getPosition() 			 	 { return m_position;  	}
	public Checkpoint getCheckPoint()			 { return m_checkpoint; }
	public Type getType()		 				 { return m_type;	  	}
	public boolean isVisited() 	 				 { return m_visited;   	}
	public HashMap<Integer, Node> getNeighbors() { return m_neighbors; 	}
		
	// Setters
	public void setVisited() 				  	{ m_visited = true; }
	public void setUnvisited() 				  	{ m_visited = false; }
	public void setSouthNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.SOUTH),a_node); }
	public void setNorthNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.NORTH),a_node); }
	public void setWestNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.WEST), a_node); }
	public void setEastNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.EAST), a_node); }
	
	public void setType(Type a_type) { 
		m_type = a_type; 
		m_renderingComponent.updateColorFromType(a_type); 
	}
	
	public void setCheckpoint(Checkpoint a_checkpoint) { 
		m_checkpoint = a_checkpoint; 
		m_renderingComponent.updateColorFromCheckpoint(a_checkpoint);
	}

	public void visit(Type a_type) {
		setVisited();
		setType(a_type);
	}
	
	protected void childVisit(int a_type) {}
	
	public void update(GraphicsContext a_context) {
		m_renderingComponent.update(a_context);
	}
	
	public Node getNeighborFromDirection(int a_direction) { 
		if (!Direction.safetyCheck(a_direction)) {
			m_position.print();
			Thread.dumpStack();
			System.exit(-1);
		}
		return m_neighbors.get(a_direction); 
	}
	
	// The next 2 functions wouldn't be necessary if we were 
	// using real neighbors (since we need to move by 2)	
	public HashMap<Integer,Node> getUnvisitedIntermediateNeighbors() {
		HashMap<Integer,Node> unvisitedNeighbors = new HashMap<Integer,Node>();
		for (HashMap.Entry<Integer,Node> entry: m_neighbors.entrySet()) {
			if (!entry.getValue().isVisited()) {
				unvisitedNeighbors.put(entry.getKey(), entry.getValue());
			}
		}
		return unvisitedNeighbors;
	}

	public HashMap<Integer,Node> getNextUnvisitedNeighbors() {
		HashMap<Integer,Node> nextUnvisitedNeighbors = new HashMap<Integer,Node>();
		// For each neighbors, fetch the neighbors 
		// following the direction they are according 
		// to the current node (the neighbor of the neighbor
		// in the same direction)
		for (HashMap.Entry<Integer,Node> entry: m_neighbors.entrySet()) {
			Node nextNeighbor = entry.getValue().getNeighborFromDirection(entry.getKey());
			if (nextNeighbor != null) {
				if (!nextNeighbor.isVisited()) {
					nextUnvisitedNeighbors.put(entry.getKey(), nextNeighbor );
				}
			}
		}
		return nextUnvisitedNeighbors;
	}
	
	protected Position 				 m_position;
	protected boolean			 	 m_visited;
	protected Type 			 		 m_type;
	protected Checkpoint		 	 m_checkpoint;
	protected HashMap<Integer, Node> m_neighbors;
	
	private RenderableComponent		 m_renderingComponent;
	
}
