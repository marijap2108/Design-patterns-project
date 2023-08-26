package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Line;

public class UpdateLineCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Line oldState;
	private Line newState;
	private transient Line originalState;
	
	public UpdateLineCmd(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint().clone());
		oldState.setEndPoint(newState.getEndPoint().clone());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void execute(DrawingModel model) {
		execute();
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
		oldState.setColor(originalState.getColor());
}
	
	@Override
    public String toString() {
        return "Updated line from " + originalState.toString() + " to " + newState.toString();
    }
}
