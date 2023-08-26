package oldProject.Drawing;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oldProject.Geometry.Point;
import oldProject.Geometry.Shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	private Drawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;

	public PnlDrawing(Drawing frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClick(e);
			}
		});
	}
	
	private void handleClick(MouseEvent e) {
		Shape newShape = null;
		Point point = new Point(e.getX(), e.getY());
		if (this.frame.isSelect()) {
			selectedShape = null;
			Iterator<Shape> iterator = shapes.iterator();

			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				shape.setSelected(false);
				if (shape.contains(point.getX(), point.getY()))
					selectedShape = shape;
			}
			
			if (selectedShape != null)
				selectedShape.setSelected(true);
			
			repaint();

			return;
		}
		
		if (this.frame.getSelectedShape() == null) {
			JOptionPane.showMessageDialog(null, "Please, select what you want to draw!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		switch(this.frame.getSelectedShape()) {
		case "Point":
			DglPoint p = new DglPoint();
			p.SetData(point.getX(), point.getY());
			p.setVisible(true);
			if (p.isOk) {
				newShape = p.getPoint();
			}
			break;
		case "Line":
			if (getStartPoint() == null) {
				setStartPoint(new Point(point.getX(), point.getY()));
			} else {
				DglLine l = new DglLine();
				l.SetData(getStartPoint().getX(), getStartPoint().getY(), point.getX(), point.getY());
				l.setVisible(true);
				if (l.isOk) {
					newShape = l.getLine();
				}
				startPoint = null;
			}
			break;
		case "Rectangle":
			DglRectangle r = new DglRectangle();
			r.SetData(point.getX(), point.getY());
			r.setVisible(true);
			if (r.isOk) {
				newShape = r.getRectangle();
			}
			break;
		case "Circle":
			DglCircle c = new DglCircle();
			c.SetData(point.getX(), point.getY());
			c.setVisible(true);
			if (c.isOk) {				
				newShape = c.getCircle();
			}
			break;
		case "Donut":
			DglDonut d = new DglDonut();
			d.SetData(point.getX(), point.getY());
			d.setVisible(true);
			if (d.isOk) {				
				newShape = d.getDonut();
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Please, select what you want to draw!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (newShape != null)
			shapes.add(newShape);

		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);

	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

}
