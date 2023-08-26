package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Circle;

public class AddCircleCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Circle circle;
	private transient DrawingModel model;
	
	public AddCircleCmd(Circle circle, DrawingModel model) {
		this.circle = circle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(circle);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(circle);
	}
	
	@Override
    public String toString() {
        return "Created circle " + circle.toString();
    }
}