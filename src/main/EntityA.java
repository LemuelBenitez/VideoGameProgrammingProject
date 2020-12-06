package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {

	public void tick(); 
	public void render(Graphics g);

	public double getX();
	public double getY();
	public Rectangle getBounds();



}
