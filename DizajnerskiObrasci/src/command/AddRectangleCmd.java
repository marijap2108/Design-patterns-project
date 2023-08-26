package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Rectangle;

public class AddRectangleCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	private transient DrawingModel model;
	
	public AddRectangleCmd(Rectangle rectangle, DrawingModel model) {
		this.rectangle = rectangle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(rectangle);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(rectangle);
	}
	
	@Override
    public String toString() {
        return "Created rectangle " + rectangle.toString();
    }
}