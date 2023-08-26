package command;

import model.DrawingModel;

public interface ICommand {
	void execute();
	void execute(DrawingModel model);
	
	void unexecute();
}
