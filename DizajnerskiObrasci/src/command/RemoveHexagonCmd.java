package command;

import java.io.Serializable;

import adapter.HexagonAdapter;
import model.DrawingModel;

public class RemoveHexagonCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private HexagonAdapter hexagon;
	private transient DrawingModel model;
	
	public RemoveHexagonCmd(HexagonAdapter hexagon, DrawingModel model) {
		this.hexagon = hexagon;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(hexagon);
	}
	
	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		model.add(hexagon);
	}
	
	@Override
    public String toString() {
        return "Removed hexagon " + hexagon.toString();
    }
}
