package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Point;

public class UpdatePointCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Point oldState;
	private Point newState;
	private transient Point originalState;
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
		oldState.setSelected(true);
	}

	@Override
	public void execute(DrawingModel model) {
		execute();
	}
	
	@Override
	public void unexecute() {
		oldState.setColor(originalState.getColor());
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
	}

	@Override
	public String toString() {
		return "Updated point from " + originalState.toString() + " to " + newState.toString();
	}
}
