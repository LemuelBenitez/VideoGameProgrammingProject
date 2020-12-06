package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {

	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage[] standing = {SpriteSheet.getSprite(0, 0)};

	private Animation standingState = new Animation(standing, 10, true);

	private Animation animation = standingState;
	
	/*
	 * private Textures tex;
	 * 
	 * public Player(double x, double y, Textures tex) { this.x = x; this.y = y;
	 * this.tex = tex; }
	 */
	
	public Player(double x, double y, Game game) {
		super(x, y);
		
		
		
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
	}
	

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) 
			x = 0;
		if(x >= 640 -18)
			x =640 -18;
		if(y < 0)
			y = 0;
		if(y >= 480 - 32)
			y = 480 -32;
		
		animation.update();

		
	}
	
	public double getX()
	{
		return x;
	}	
	
	public double getY()
	{
		return y;
	}
	
	public void setX(double x) 
	{
		this.x = x;
	}
	
	public void setY(double y) 
	{
		this.y = y;
	}
	
	public void setVelX(double velX) 
	{
		this.velX = velX;
	}
	
	public void setVelY(double velY) 
	{
		this.velY = velY;
	}
	
	
	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), (int) x, (int) y, null);
	}


	public void setShooting(boolean b) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
