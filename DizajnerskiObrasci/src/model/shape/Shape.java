package model.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import prototype.IPrototype;

public abstract class Shape implements Moveable, Comparable, IPrototype, Serializable {
	private static final long serialVersionUID = 1L;
	private transient boolean selected;
	private Color color;
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public Shape clone() {
		try {
			return (Shape) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		return color.toString();
	}
}
