
package maze;

//JavaFX packages imports
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Application packages imports
import application.Settings;
import core.utils.Position;
import maze.entity.Type;
import maze.entity.Checkpoint;

public class RenderableComponent{
	
	public RenderableComponent(int a_X, int a_Y) {
		m_fillColor = new Color(0.0, 0.0, 0.0, 1.0); // Opacity/alpha = 1.0
		m_cachedRenderingPosition = new Position(
				a_X*Settings.NODE_WIDTH_PX, 
				a_Y*Settings.NODE_HEIGHT_PX);		
	}
	
	public void updateFromType(Type a_type) {
		switch(a_type) {
			case FLOOR:
				m_fillColor = Color.GRAY;
				break;
			case WALL:
				m_fillColor = Color.BLACK;
				break;
			default:
				m_fillColor = Color.BLUEVIOLET; // For debugging purposes
				break;
		}
	}
	
	public void updateColorFromCheckpoint(Checkpoint a_checkpoint) {
		switch(a_checkpoint) {
			case NONE:
				// Could be either wall or floor
				// so doing nothing is the best option
				break;
			case START:
				m_fillColor = Color.GREEN;
				break;
			case END:
				m_fillColor = Color.RED;
				break;
			case POTENTIAL_SOLUTION:
				m_fillColor = Color.BLUE;
				break;				
			default:
				m_fillColor = Color.BLUEVIOLET; // For debugging purposes
				break;
		}
	}	

	public void update(GraphicsContext a_context) {
		a_context.setFill(m_fillColor);
		a_context.fillRect(
			m_cachedRenderingPosition.getX(), 
			m_cachedRenderingPosition.getY(), 
			Settings.NODE_WIDTH_PX,
			Settings.NODE_HEIGHT_PX);
	}
	
	private Position 	m_cachedRenderingPosition;
	private Color 	 	m_fillColor;
	
}
