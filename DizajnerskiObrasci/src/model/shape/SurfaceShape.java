package model.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class SurfaceShape extends Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Color innerColor;
	
	public abstract double area();
	public abstract void fill(Graphics g);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	@Override
	public String toString() {
		return innerColor.toString();
	}
}
