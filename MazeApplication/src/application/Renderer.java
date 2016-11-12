package application;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;

//Application packages imports
import core.Algo;
import core.utils.Pair;
import maze.Maze;

public final class Renderer {

	Renderer(GraphicsContext a_context) {
		m_context = a_context;
		
		m_isRendering 	= new SimpleBooleanProperty(false);
		m_stepCompleted = new SimpleBooleanProperty(false);
		
		m_stepCompleted.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if (newValue) {
            		m_stepCompleted.set(false);
            		if (m_algo.compareTo(m_currentStory.first)) {
            			m_algo = m_currentStory.second;
            			start();
            		}            		
            		// Otherwise story is finished
            	}
            }
        });
		m_renderingLoop = new AnimationTimer() {
	        public void handle(long currentNanoTime) {
	        	if (m_algo.next()) {
		    		for (int i = 0 ; i < Maze.INSTANCE.getWidth(); ++i) {
		    			for (int j = 0 ; j < Maze.INSTANCE.getHeight(); ++j) {
		    				Maze.INSTANCE
		    					.getNodeAt(i,j)
		    					.update(m_context);
		    			}		
		    		}
	        	} else {
	        		m_algo.end();
	        		stop();
	        		m_stepCompleted.set(true);
	        	}
	        }
	    };  
	}
	
	public void setStory(Algo a_generationAlgo, Algo a_solvingAlgo) {
		// Can't change an algo while rendering
		if (!m_isRendering.get()) {
			m_currentStory = Pair.of(a_generationAlgo, a_solvingAlgo);
			m_algo = a_generationAlgo;
		}
	}
	
	public void start() {
		m_algo.init();
		m_renderingLoop.start();
		m_isRendering.set(true);
	}

	public void pause() {
		m_renderingLoop.stop();
		m_isRendering.set(false);
	}

	private Algo					m_algo;
	private Pair<Algo,Algo>			m_currentStory;

	private GraphicsContext 		m_context;
    private AnimationTimer 			m_renderingLoop;
    private BooleanProperty 		m_isRendering;
    private BooleanProperty			m_stepCompleted;
	
}
