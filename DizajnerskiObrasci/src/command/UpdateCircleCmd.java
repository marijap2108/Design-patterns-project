package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Circle;

public class UpdateCircleCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Circle oldState;
	private Circle newState;
	private transient Circle originalState;
	
	public UpdateCircleCmd(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setRadius(newState.getRadius());
		oldState.setCenter(newState.getCenter().clone());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setColor(newState.getColor());
	}
	
	@Override
	public void execute(DrawingModel model) {
		execute();
	}

	@Override
	public void unexecute() {
		oldState.setRadius(originalState.getRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setColor(originalState.getColor());
	}
	
	@Override
    public String toString() {
        return "Updated circle from " + originalState.toString() + " to " + newState.toString();
    }
}
