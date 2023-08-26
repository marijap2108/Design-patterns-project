package command;

import java.io.Serializable;
import model.DrawingModel;
import model.shape.Shape;

public class MoveCmd implements ICommand, Serializable {
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private int oldPosition;
	private int newPosition;
	
	public MoveCmd(int oldPosition, int newPosition, DrawingModel model) {
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.model = model;
	}

	@Override
	public void execute() {
		int op = oldPosition;
		int np = newPosition;
		
		if (op < np) {
			while(op < np) {
				Shape shape = model.getShapes().get(op + 1);
				model.getShapes().set(op + 1, model.getShapes().get(op));
				model.getShapes().set(op, shape);
				op++;
			}
			return;
		}
		
		while(op > np) {
			Shape shape = model.getShapes().get(op - 1);
			model.getShapes().set(op - 1, model.getShapes().get(op));
			model.getShapes().set(op, shape);
			op--;
		}
	}

	@Override
	public void execute(DrawingModel model) {
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute() {
		int op = newPosition;
		int np = oldPosition;
		
		if (op < np) {
			while(op < np) {
				Shape shape = model.getShapes().get(op + 1);
				model.getShapes().set(op + 1, model.getShapes().get(op));
				model.getShapes().set(op, shape);
				op++;
			}
			return;
		}
		
		while(op > np) {
			Shape shape = model.getShapes().get(op - 1);
			model.getShapes().set(op - 1, model.getShapes().get(op));
			model.getShapes().set(op, shape);
			op--;
		}
	}

	@Override
    public String toString() {
        return "Moved from " + oldPosition + " to " + newPosition;
    }
}
