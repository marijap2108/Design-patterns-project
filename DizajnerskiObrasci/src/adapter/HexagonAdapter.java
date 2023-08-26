package adapter;

import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;
import model.shape.Point;
import model.shape.SurfaceShape;
import prototype.IPrototype;

import java.awt.Color;

public class HexagonAdapter extends SurfaceShape implements IShape, IPrototype, Serializable {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	private Point point;
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Point point, int radius, boolean selected, Color innerColor, Color outlineColor) {
		this.point = point;
		hexagon = new Hexagon(point.getX(), point.getY(), radius);
		hexagon.setSelected(selected);
		hexagon.setAreaColor(innerColor);
		hexagon.setBorderColor(outlineColor);
	}


	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}

	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setRadius(int r) {
		hexagon.setR(r);
	}
	
	public String toString() {
		return "x: " + hexagon.getX() + " y: " + hexagon.getY() + " radius: " + hexagon.getR() + " innerColor: " + hexagon.getAreaColor().getRGB() +  " outlineColor: " + hexagon.getBorderColor().getRGB();
	}
	
	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.hexagon.getX() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - this.hexagon.getR()/2 - 3, (int) (this.hexagon.getY() - this.hexagon.getR()*Math.sqrt(3)/2 - 3), 6, 6);
			g.drawRect(this.hexagon.getX() + this.hexagon.getR()/2 - 3, (int) (this.hexagon.getY() - this.hexagon.getR()*Math.sqrt(3)/2 - 3), 6, 6);
			g.drawRect(this.hexagon.getX() + this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + this.hexagon.getR()/2 - 3, (int) (this.hexagon.getY() + this.hexagon.getR()*Math.sqrt(3)/2 - 3), 6, 6);
			g.drawRect(this.hexagon.getX() - this.hexagon.getR()/2 - 3, (int) (this.hexagon.getY() + this.hexagon.getR()*Math.sqrt(3)/2 - 3), 6, 6);
			g.drawRect(this.hexagon.getX() - this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
		}
	}

	@Override
	public void setColor(Color c) {
		hexagon.setBorderColor(c);
	}

	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	@Override
	public void setInnerColor(Color c) {
		hexagon.setAreaColor(c);
	}

	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public void fill(Graphics g) {
		
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(getX() + byX);
		hexagon.setY(getY() + byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Hexagon) {
			return (hexagon.getR() - ((Hexagon) o).getR());
		}
		return 0;
	}

	@Override
	public double area() {
		return 3*Math.sqrt(3)/2 * hexagon.getR() * hexagon.getR();
	}
	
	@Override
	public HexagonAdapter clone() {
		return new HexagonAdapter(this.point, this.getRadius(), false, this.getInnerColor(), this.getColor());
	}
}
