package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Line;

public class AddLineCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Line line;
	private transient DrawingModel model;
	
	public AddLineCmd(Line line, DrawingModel model)
	{
		this.line = line;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.add(line);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(line);
	}
	
	@Override
    public String toString() {
        return "Created line " + line.toString();
    }
}