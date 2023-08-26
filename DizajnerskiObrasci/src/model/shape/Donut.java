package model.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;


public class Donut extends Circle implements Serializable {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color innerColor, Color outlineColor) {
		this(center, radius, innerRadius, selected);
		setInnerColor(innerColor);
		setColor(outlineColor);
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return super.toString() + " innerRadius: " + this.innerRadius;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return getCenter().distance(x, y) <= getRadius() && getCenter().distance(x, y) >= innerRadius; 
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2D=(Graphics2D)g;
		Shape outer = new Ellipse2D.Double(getCenter().getX() - this.getRadius(), getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		Shape inner = new Ellipse2D.Double(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
		Area circle=new Area(outer);
		circle.subtract(new Area(inner));
		g2D.setColor(this.getInnerColor());
		g2D.fill(circle);
		g2D.setColor(getColor());
		g2D.draw(circle);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + this.getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - this.getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX()- 3, getCenter().getY() + this.getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - this.getRadius() - 3, 6, 6);
		};
	}
	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.BLUE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}

	@Override
	public Donut clone() {
		return new Donut(this.getCenter().clone(), this.getRadius(), this.getInnerRadius(), false, this.getInnerColor(), this.getColor());
	}
}
