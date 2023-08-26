package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Donut;

public class AddDonutCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Donut donut;
	private transient DrawingModel model;
	
	public AddDonutCmd(Donut donut, DrawingModel model) {
		this.donut = donut;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(donut);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(donut);
	}
	
	@Override
    public String toString() {
        return "Created donut " + donut.toString();
    }
}
