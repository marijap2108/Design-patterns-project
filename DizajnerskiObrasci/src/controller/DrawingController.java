package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.*;
import dialog.*;
import model.DrawingModel;
import model.shape.Circle;
import model.shape.Donut;
import model.shape.Line;
import model.shape.Point;
import model.shape.Rectangle;
import model.shape.Shape;
import strategy.DrawingsFile;
import strategy.FileManager;
import strategy.LogsFile;
import view.DrawingFrame;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private int step = 0;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.model.addPropertyChangeListener(this.frame);
	}

	public void handleClick(MouseEvent e) {
		Shape newShape = null;
		ICommand command = null;
		Shape hit = null;
		Point point = new Point(e.getX(), e.getY());
		if (frame.isSelect()) {
			Iterator<model.shape.Shape> iterator = model.getShapes().iterator();

			while (iterator.hasNext()) {
				model.shape.Shape shape = iterator.next();
				shape.setSelected(false);
				if (shape.contains(point.getX(), point.getY())) {
					hit = shape;
				}
			}

			if (hit == null) {
				model.setSelectedShape(null);
			} else {
				model.setSelectedShape(hit);
			}

			if (model.getSelectedShape() != null)
				model.getSelectedShapes().forEach(shape -> shape.setSelected(true));

			frame.repaint();
			return;
		}

		if (this.frame.getSelectedShape() == null) {
			JOptionPane.showMessageDialog(null, "Please, select what you want to draw!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		switch (frame.getSelectedShape()) {
		case "Point":
			DglPoint p = new DglPoint();
			p.SetData(point.getX(), point.getY(), frame.innerColor);
			p.setVisible(true);
			if (p.isOk) {
				removeSteps();
				newShape = p.getPoint();
				command = new AddPointCmd((Point) newShape, model);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Line":
			if (model.getStartPoint() == null) {
				model.setStartPoint(new Point(point.getX(), point.getY()));
			} else {
				DglLine l = new DglLine();
				l.SetData(model.getStartPoint().getX(), model.getStartPoint().getY(), point.getX(), point.getY(),
						frame.innerColor);
				l.setVisible(true);
				if (l.isOk) {
					removeSteps();
					newShape = l.getLine();
					command = new AddLineCmd((Line) newShape, model);
					model.setCommand(command);
					command.execute();
				}
				model.setStartPoint(null);
			}
			break;
		case "Rectangle":
			DglRectangle r = new DglRectangle();
			r.SetData(point.getX(), point.getY(), frame.innerColor, frame.outlineColor);
			r.setVisible(true);
			if (r.isOk) {
				removeSteps();
				newShape = r.getRectangle();
				command = new AddRectangleCmd((Rectangle) newShape, model);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Circle":
			DglCircle c = new DglCircle();
			c.SetData(point.getX(), point.getY(), frame.innerColor, frame.outlineColor);
			c.setVisible(true);
			if (c.isOk) {
				removeSteps();
				newShape = c.getCircle();
				command = new AddCircleCmd((Circle) newShape, model);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Hexagon":
			DglHexagon h = new DglHexagon();
			h.SetData(point.getX(), point.getY(), frame.innerColor, frame.outlineColor);
			h.setVisible(true);
			if (h.isOk) {
				removeSteps();
				newShape = h.getHexagon();
				command = new AddHexagonCmd((HexagonAdapter) newShape, model);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Donut":
			DglDonut d = new DglDonut();
			d.SetData(point.getX(), point.getY(), frame.innerColor, frame.outlineColor);
			d.setVisible(true);
			if (d.isOk) {
				removeSteps();
				newShape = d.getDonut();
				command = new AddDonutCmd((Donut) newShape, model);
				model.setCommand(command);
				command.execute();
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Please, select what you want to draw!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (command != null) {
			frame.log(command.toString());
		}
		frame.repaint();
	}

	public void modifyMethod(ActionEvent e) {
		if (model.getSelectedShape() != null) {
			modify();
		} else {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getBtnSelect().setSelected(true);
		}
		frame.getBtnSelect().setSelected(false);
	}

	private void modify() {
		if (this.model.getSelectedShape() == null) {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Shape newShape = null;
		ICommand command = null;

		switch (model.getSelectedShape().getClass().getSimpleName()) {
		case "Point":
			DglPoint p = new DglPoint();
			p.SetData((Point) (model.getSelectedShape()));
			p.setVisible(true);
			if (p.isOk) {
				removeSteps();
				newShape = p.getPoint();
				command = new UpdatePointCmd((Point) model.getSelectedShape(), (Point) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Line":
			DglLine l = new DglLine();
			l.SetData((Line) (model.getSelectedShape()));
			l.setVisible(true);
			if (l.isOk) {
				removeSteps();
				newShape = l.getLine();
				command = new UpdateLineCmd((Line) model.getSelectedShape(), (Line) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Rectangle":
			DglRectangle r = new DglRectangle();
			r.SetData((Rectangle) (model.getSelectedShape()));
			r.setVisible(true);
			if (r.isOk) {
				removeSteps();
				newShape = r.getRectangle();
				command = new UpdateRectangleCmd((Rectangle) model.getSelectedShape(), (Rectangle) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Circle":
			DglCircle c = new DglCircle();
			c.SetData((Circle) (model.getSelectedShape()));
			c.setVisible(true);
			if (c.isOk) {
				removeSteps();
				newShape = c.getCircle();
				command = new UpdateCircleCmd((Circle) model.getSelectedShape(), (Circle) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "HexagonAdapter":
			DglHexagon h = new DglHexagon();
			h.SetData((HexagonAdapter) (model.getSelectedShape()));
			h.setVisible(true);
			if (h.isOk) {
				removeSteps();
				newShape = h.getHexagon();
				command = new UpdateHexagonCmd((HexagonAdapter) model.getSelectedShape(), (HexagonAdapter) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		case "Donut":
			DglDonut d = new DglDonut();
			d.SetData((Donut) (model.getSelectedShape()));
			d.setVisible(true);
			if (d.isOk) {
				removeSteps();
				newShape = d.getDonut();
				command = new UpdateDonutCmd((Donut) model.getSelectedShape(), (Donut) newShape);
				model.setCommand(command);
				command.execute();
			}
			break;
		default:
			return;
		}

		if (command != null) {
			frame.log(command.toString());
		}
		frame.repaint();
	}

	public void deleteMethod(ActionEvent e) {
		ArrayList<Shape> selectedShapes = model.getSelectedShapes();
		ArrayList<ICommand> command = new ArrayList<ICommand>();

		int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message",
				JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {

			selectedShapes.forEach(selectedShape -> {

				if (selectedShape != null) {

					removeSteps();

					switch (selectedShape.getClass().getSimpleName()) {
					case "Point":
						command.add(new RemovePointCmd((Point) selectedShape, model));
						break;
					case "Line":
						command.add(new RemoveLineCmd((Line) selectedShape, model));
						break;
					case "Rectangle":
						command.add(new RemoveRectangleCmd((Rectangle) selectedShape, model));
						break;
					case "Circle":
						command.add(new RemoveCircleCmd((Circle) selectedShape, model));
						break;
					case "HexagonAdapter":
						command.add(new RemoveHexagonCmd((HexagonAdapter) selectedShape, model));
						break;
					case "Donut":
						command.add(new RemoveDonutCmd((Donut) selectedShape, model));
						break;
					default:
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			});

			model.setSelectedShape(null);
		}

		command.forEach(com -> {
			com.execute();
			model.setCommand(com);
			frame.log(com.toString());
		});

		frame.repaint();
	}

	public void exportFile() throws IOException {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			LogsFile logsFile = new LogsFile(model, frame, this);
			FileManager fileManager = new FileManager(logsFile);

			removeSteps();

			fileManager.writeInFile(path);
		}
	}

	public void importFile() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			LogsFile logsFile = new LogsFile(model, frame, this);
			FileManager fileManager = new FileManager(logsFile);

			fileManager.readFromFile(path);
			frame.repaint();
		}
	}

	public void undo() {
		ArrayList<ICommand> commands = model.getCommands();
		commands.get(commands.size() - ++step).unexecute();
		frame.log("Undo " + commands.get(commands.size() - step).toString());
		frame.repaint();
		frame.mntmRedo.setEnabled(true);
		if (commands.size() < step + 1) {
			frame.mntmUndo.setEnabled(false);
		}
	}

	public void redo() {
		ArrayList<ICommand> commands = model.getCommands();
		frame.log("Redo " + commands.get(commands.size() - step).toString());
		commands.get(commands.size() - step--).execute();
		frame.repaint();
		frame.mntmUndo.setEnabled(true);
		if (step <= 0) {
			frame.mntmRedo.setEnabled(false);
		}
	}

	private void removeSteps() {
		model.removeCommands(step);
		step = 0;
		frame.mntmRedo.setEnabled(false);
		frame.mntmUndo.setEnabled(true);
	}

	public void toFront() {
		removeSteps();
		int position = model.getShapes().indexOf(model.getSelectedShape());
		MoveCmd command = new MoveCmd(position, position + 1, model);
		command.execute();
		frame.log(command.toString());
		model.setCommand(command);
		frame.repaint();
	}

	public void toBack() {
		removeSteps();
		int position = model.getShapes().indexOf(model.getSelectedShape());
		MoveCmd command = new MoveCmd(position, position - 1, model);
		command.execute();
		frame.log(command.toString());
		model.setCommand(command);
		frame.repaint();
	}

	public void bringToFront() {
		removeSteps();
		int position = model.getShapes().indexOf(model.getSelectedShape());
		MoveCmd command = new MoveCmd(position, model.getShapes().size() - 1, model);
		command.execute();
		frame.log(command.toString());
		model.setCommand(command);
		frame.repaint();
	}

	public void bringToBack() {
		removeSteps();
		int position = model.getShapes().indexOf(model.getSelectedShape());
		MoveCmd command = new MoveCmd(position, 0, model);
		command.execute();
		frame.log(command.toString());
		model.setCommand(command);
		frame.repaint();
	}

	public void importDrawing() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			DrawingsFile drawingFile = new DrawingsFile(model);
			FileManager fileManager = new FileManager(drawingFile);

			fileManager.readFromFile(path);

			frame.repaint();
		}
	}

	public void exportDrawing() throws IOException {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			DrawingsFile drawingFile = new DrawingsFile(model);
			FileManager fileManager = new FileManager(drawingFile);

			removeSteps();

			fileManager.writeInFile(path);
		}

	}
}
