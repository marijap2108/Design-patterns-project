package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Point;

public class RemovePointCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Point point;
	private transient DrawingModel model;
	
	public RemovePointCmd(Point point, DrawingModel model) {
		this.point = point;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(point);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.add(point);
	}
	
	@Override
    public String toString() {
        return "Removed point " + point.toString();
    }
}