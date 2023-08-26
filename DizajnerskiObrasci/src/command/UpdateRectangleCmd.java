package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Rectangle;

public class UpdateRectangleCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Rectangle oldState;
	private Rectangle newState;
	private transient Rectangle originalState;
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint().clone());
		oldState.setWidth(newState.getWidth());
		oldState.setHeight(newState.getHeight());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void execute(DrawingModel model) {
		execute();
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeftPoint(originalState.getUpperLeftPoint());
		oldState.setWidth(originalState.getWidth());
		oldState.setHeight(originalState.getHeight());
		oldState.setColor(originalState.getColor());
		oldState.setInnerColor(originalState.getInnerColor());
	}
	
	@Override
    public String toString() {
        return "Updated rectangle from " + originalState.toString() + " to " + newState.toString();
    }
}
