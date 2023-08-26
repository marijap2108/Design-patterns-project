package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.swing.JOptionPane;

import command.AddCircleCmd;
import command.AddDonutCmd;
import command.AddLineCmd;
import command.AddPointCmd;
import command.AddHexagonCmd;
import command.AddRectangleCmd;
import command.ICommand;
import command.MoveCmd;
import command.RemoveCircleCmd;
import command.RemoveDonutCmd;
import command.RemoveHexagonCmd;
import command.RemoveLineCmd;
import command.RemovePointCmd;
import command.RemoveRectangleCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import controller.DrawingController;
import model.DrawingModel;
import model.shape.Circle;
import model.shape.Donut;
import model.shape.Line;
import model.shape.Point;
import model.shape.Rectangle;
import model.shape.Shape;
import adapter.HexagonAdapter;
import view.DrawingFrame;

public class LogsFile implements IFileOperator {
	DrawingModel model;
	DrawingFrame frame;
	DrawingController controller;
	
	public LogsFile(DrawingModel model, DrawingFrame frame, DrawingController controller) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
	}

	@Override
	public void writeInFile(String path) throws IOException  {
		FileWriter file = new FileWriter(path);
		
		try (PrintWriter output = new PrintWriter(file)) {
			output.println(frame.getLogs().substring(0, (frame.getLogs().length()-1)));
		}
	}

	@Override
	public void readFromFile(String path) { 

		int result;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				result = JOptionPane.showConfirmDialog(frame,"Draw next shape?", "continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.NO_OPTION){
					break;
		        }
				drawLogs(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawLogs(String log) {
		ICommand command = null;
		String words[] = log.split(" ");
		switch(words[0]) {
		case "Created":
			switch(words[1]) {
			case "point":
				command = new AddPointCmd(new Point(Integer.parseInt(words[3]), Integer.parseInt(words[5]), false, new Color(Integer.parseInt(words[7]))), model);
				break;
			case "line":
				command = new AddLineCmd(new Line(new Point(Integer.parseInt(words[4]), Integer.parseInt(words[6])), new Point(Integer.parseInt(words[9]), Integer.parseInt(words[11])), false, new Color(Integer.parseInt(words[13]))), model);
				break;
			case "rectangle":
				command = new AddRectangleCmd(new Rectangle(new Point(Integer.parseInt(words[4]), Integer.parseInt(words[6])), Integer.parseInt(words[8]), Integer.parseInt(words[10]), false, new Color(Integer.parseInt(words[12])), new Color(Integer.parseInt(words[14]))), model);
				break;
			case "circle":
				command = new AddCircleCmd(new Circle(new Point(Integer.parseInt(words[4]), Integer.parseInt(words[6])), Integer.parseInt(words[8]), false, new Color(Integer.parseInt(words[10])), new Color(Integer.parseInt(words[12]))), model);
				break;
			case "donut":
				command = new AddDonutCmd(new Donut(new Point(Integer.parseInt(words[4]), Integer.parseInt(words[6])), Integer.parseInt(words[8]), Integer.parseInt(words[14]), false, new Color(Integer.parseInt(words[10])), new Color(Integer.parseInt(words[12]))), model);
				break;
			case "hexagon":
				command = new AddHexagonCmd(new HexagonAdapter(new Point(Integer.parseInt(words[3]), Integer.parseInt(words[5])), Integer.parseInt(words[7]), false, new Color(Integer.parseInt(words[9])), new Color(Integer.parseInt(words[11]))), model);
				break;
			default:
				return;
			}
			break;
		case "Removed":
			Shape s = null;
			Iterator<Shape> shapes = model.getShapes().iterator();
			switch(words[1]) {
			case "point":
				while(shapes.hasNext()) {
					s = shapes.next();
					if (s.toString().compareTo(log.replace("Removed point ", "")) == 0) {
						break;
					}
				}
				command = new RemovePointCmd((Point) s, model);
				break;
			case "line":
				while(shapes.hasNext()) {
					s = shapes.next();
					if (s.toString().compareTo(log.replace("Removed line ", "")) == 0) {
						break;
					}
				}
				command = new RemoveLineCmd((Line) s, model);
				break;
			case "rectangle":
				while(shapes.hasNext()) {
					s = shapes.next();
					if (s.toString().compareTo(log.replace("Removed rectangle ", "")) == 0) {
						break;
					}
				}
				command = new RemoveRectangleCmd((Rectangle) s, model);
				break;
			case "circle":
				while(shapes.hasNext()) {
					s = shapes.next();
					if (s.toString().compareTo(log.replace("Removed circle ", "")) == 0) {
						break;
					}
				}
				command = new RemoveCircleCmd((Circle) s, model);
				break;
			case "donut":
				while(shapes.hasNext()) {
					s = shapes.next();

					if (s.toString().compareTo(log.replace("Removed donut ", "")) == 0) {
						break;
					}
				}
				command = new RemoveDonutCmd((Donut) s, model);
				break;
			case "hexagon":
				while(shapes.hasNext()) {
					s = shapes.next();
					if (s.toString().compareTo(log.replace("Removed hexagon ", "")) == 0) {
						break;
					}
				}
				command = new RemoveHexagonCmd((HexagonAdapter) s, model);
				break;
			default:
				return;
			}
			break;
		case "Updated":
			Shape u = null;
			Iterator<Shape> updated = model.getShapes().iterator();
			switch(words[1]) {
			case "point":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdatePointCmd((Point) u, new Point(Integer.parseInt(words[11]), Integer.parseInt(words[13]), false, new Color(Integer.parseInt(words[15]))));
				break;
			case "line":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdateLineCmd((Line) u, new Line(new Point(Integer.parseInt(words[18]), Integer.parseInt(words[20])), new Point(Integer.parseInt(words[23]), Integer.parseInt(words[25])), false, new Color(Integer.parseInt(words[27]))));
				break;
			case "rectangle":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdateRectangleCmd((Rectangle) u, new Rectangle(new Point(Integer.parseInt(words[19]), Integer.parseInt(words[21])), Integer.parseInt(words[23]), Integer.parseInt(words[25]), false, new Color(Integer.parseInt(words[27])), new Color(Integer.parseInt(words[29]))));
				break;
			case "circle":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdateCircleCmd((Circle) u, new Circle(new Point(Integer.parseInt(words[17]), Integer.parseInt(words[19])), Integer.parseInt(words[21]), false, new Color(Integer.parseInt(words[23])), new Color(Integer.parseInt(words[25]))));
				break;
			case "donut":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdateDonutCmd((Donut) u, new Donut(new Point(Integer.parseInt(words[19]), Integer.parseInt(words[21])), Integer.parseInt(words[23]), Integer.parseInt(words[29]), false, new Color(Integer.parseInt(words[25])), new Color(Integer.parseInt(words[27]))));
				break;
			case "hexagon":
				while(updated.hasNext()) {
					u = updated.next();
					if (u.toString().compareTo(log.substring(log.indexOf("from ") + 5, log.indexOf(" to"))) == 0) {
						break;
					}
				}
				command = new UpdateHexagonCmd((HexagonAdapter) u, new HexagonAdapter(new Point(Integer.parseInt(words[15]), Integer.parseInt(words[17])), Integer.parseInt(words[19]), false, new Color(Integer.parseInt(words[21])), new Color(Integer.parseInt(words[23]))));
				break;
			default:
				return;
			}
			break;
		case "Moved":
			command = new MoveCmd(Integer.parseInt(words[2]), Integer.parseInt(words[4]), model);
			break;
		case "Undo":
			this.controller.undo();
			return;
		case "Redo":
			this.controller.redo();
			return;
		default:
			return;
		}
		
		model.setCommand(command);
		command.execute();
		frame.mntmUndo.setEnabled(true);
		frame.log(command.toString());
		frame.repaint();
	}
	
}