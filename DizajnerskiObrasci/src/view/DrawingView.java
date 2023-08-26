package view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import model.DrawingModel;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<model.shape.Shape> iterator = model.getShapes().iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);
	}
}
