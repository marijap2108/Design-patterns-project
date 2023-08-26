package adapter;

import java.awt.Graphics;
import java.awt.Color;

public interface IShape {
	void draw(Graphics g);
	void setColor(Color c);
	Color getColor();
	void setInnerColor(Color c);
	Color getInnerColor();
	public void fill(Graphics g);
	boolean contains(int x, int y);
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public int getRadius();
	public void setRadius(int r);
}
