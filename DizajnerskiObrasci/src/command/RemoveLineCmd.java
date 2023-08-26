package command;

import java.io.Serializable;

import model.DrawingModel;
import model.shape.Line;

public class RemoveLineCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private Line line;
	private transient DrawingModel model;
	
	public RemoveLineCmd(Line line, DrawingModel model)
	{
		this.line = line;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.remove(line);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}


	@Override
	public void unexecute() {
		model.add(line);
	}
	
	@Override
    public String toString() {
        return "Removed line " + line.toString();
    }
}