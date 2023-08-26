package command;

import java.io.Serializable;

import adapter.HexagonAdapter;
import model.DrawingModel;

public class UpdateHexagonCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private transient HexagonAdapter originalState;
	
	public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setRadius(newState.getRadius());	
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
	}
	
	@Override
	public void execute(DrawingModel model) {
		execute();
	}

	@Override
	public void unexecute() {
		oldState.setColor(originalState.getColor());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setRadius(originalState.getRadius());
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
	}
	
	@Override
    public String toString() {
        return "Updated hexagon from " + originalState.toString() + " to " + newState.toString();
    }
}
