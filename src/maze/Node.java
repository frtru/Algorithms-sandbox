package maze;

import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import core.Direction;
import core.utils.Position;
import maze.entity.Type;
import maze.entity.Checkpoint;

public class Node {
	
	public Node(int a_X, int a_Y) {
		m_position	= new Position(a_X, a_Y);
		m_type 		= Type.UNKNOWN;
		m_checkpoint = Checkpoint.NONE;
		m_neighbors = new HashMap<Integer, Node>(Direction.IDS.size(), 1.0f);	
		m_floorNeighbors = new HashSet<Node>(Direction.IDS.size(), 1.0f);
		m_renderingComponent = new RenderableComponent(a_X, a_Y);
	}
	
	// Accessors
	public Position 				getPosition()		{ return m_position;  		}
	public Checkpoint 				getCheckPoint()		{ return m_checkpoint; 		}
	public Type 					getType()			{ return m_type;	  		}
	public HashMap<Integer, Node> 	getNeighbors() 		{ return m_neighbors; 		}
	public HashSet<Node>			getFloorNeighbors() { return m_floorNeighbors; 	}	
	
	// Setters
	public void setSouthNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.SOUTH),a_node); }
	public void setNorthNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.NORTH),a_node); }
	public void setWestNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.WEST), a_node); }
	public void setEastNeighbor(Node a_node) 	{ m_neighbors.put(Direction.IDS.get(Direction.EAST), a_node); }
	
	public void addFloorNeighbor(Node a_node) {
		m_floorNeighbors.add(a_node);
	}
	
	public void setType(Type a_type) { 
		m_type = a_type; 
		m_renderingComponent.updateFromType(a_type); 
	}
	
	public void setCheckpoint(Checkpoint a_checkpoint) { 
		m_checkpoint = a_checkpoint; 
		m_renderingComponent.updateFromCheckpoint(a_checkpoint);
	}

	// For debugging purposes
	public void setColor(Color a_color) {
		m_renderingComponent.setColor(a_color);
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
	
	private Position 				m_position;
	private Type 			 		m_type;
	private Checkpoint		 	 	m_checkpoint;
	private HashMap<Integer, Node> 	m_neighbors;
	private HashSet<Node> 			m_floorNeighbors;
	
	private RenderableComponent		 m_renderingComponent;
	
}
