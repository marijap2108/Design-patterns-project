package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Donut;

public class UpdateDonutCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Donut oldState;
	private Donut newState;
	private transient Donut originalState;
	
	public UpdateDonutCmd(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
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
		oldState.setInnerRadius(originalState.getInnerRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setColor(originalState.getColor());
	}
	
	@Override
    public String toString() {
        return "Updated donut from " + originalState.toString() + " to " + newState.toString();
    }
}
