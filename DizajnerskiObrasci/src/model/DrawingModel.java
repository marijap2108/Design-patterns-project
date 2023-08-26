package model;

import java.util.ArrayList;
import java.util.stream.IntStream;

import command.ICommand;
import model.shape.Point;
import model.shape.Shape;
import observer.Observable;

import java.beans.PropertyChangeSupport;

public class DrawingModel extends Observable {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private String selectedShape;
	private ArrayList<Shape> selectedShapeSelect = new ArrayList<Shape>();
	private ArrayList<ICommand> commands = new ArrayList<ICommand>();
	public boolean select;

	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void add(Shape newShape) {
		shapes.add(newShape);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Shape getSelectedShape() {
		return selectedShapeSelect.isEmpty() ? null : selectedShapeSelect.get(0);
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapeSelect;
	}

	public void setSelectedShape(Shape selectedShapeSelect) {
		if (selectedShapeSelect == null) {
			this.selectedShapeSelect.clear();
			propertyChangeSupport.firePropertyChange("selectedShape", null, this.selectedShapeSelect);
			return;
		}
		if (this.selectedShapeSelect.contains(selectedShapeSelect)) {
			this.selectedShapeSelect.remove(selectedShapeSelect);
			propertyChangeSupport.firePropertyChange("selectedShape", null, this.selectedShapeSelect);
			return;
		}
		this.selectedShapeSelect.add(selectedShapeSelect);
		propertyChangeSupport.firePropertyChange("selectedShape", null, this.selectedShapeSelect);
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getSelectedShapeSting() {
		return selectedShape;
	}

	public void setSelectedShapeString(String selectedShape) {
		this.selectedShape = selectedShape;
	}

	public ArrayList<ICommand> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<ICommand> commands) {
		this.commands = commands;
		commands.forEach(command -> command.execute(this));
	}

	public void setCommand(ICommand command) {
		commands.add(command);
	}

	public void removeCommands(int step) {
		IntStream.range(0, step).forEach(_i -> commands.remove(commands.size() - 1));
	}
}
