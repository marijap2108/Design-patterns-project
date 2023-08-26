package command;

import java.io.Serializable;

import adapter.HexagonAdapter;
import model.DrawingModel;

public class AddHexagonCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private HexagonAdapter hexagon;
	private transient DrawingModel model;
	
	public AddHexagonCmd(HexagonAdapter newShape, DrawingModel model) {
		this.hexagon = newShape;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(hexagon);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.remove(hexagon);
	}
	
	@Override
    public String toString() {
        return "Created hexagon " + hexagon.toString();
    }
}
