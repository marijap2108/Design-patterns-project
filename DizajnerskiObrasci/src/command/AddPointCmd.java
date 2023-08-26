package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Point;

public class AddPointCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Point point;
	private transient DrawingModel model;
	
	public AddPointCmd(Point point, DrawingModel model) {
		this.point = point;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.add(point);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(point);
	}
	
	@Override
    public String toString() {
        return "Created point " + point.toString();
    }
}