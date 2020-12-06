package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject implements EntityB {
	
	private BufferedImage[] standing = {SpriteSheet.getSprite(0, 2)};

	private Animation standingState = new Animation(standing, 10, true);

	private Animation animation = standingState;
	
	public Enemy(double x, double y)
	{
		super(x, y);
		
		
	}
	
	public void addEnemy(int x, int y) {
		
	}
	
	public void tick()
	{
		//x += -1; // land enemy
		animation.update();
		
//		if(y > (Game.HEIGHT * Game.SCALE) { //Space ship respawn after flying down
//			y = 0;
//		x = r.nextInt(Game.WIDTH * Game.SCALE);
		
	}

	
	
	public void render(Graphics g)
	{
		g.drawImage(animation.getSprite(), (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32); 
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
		
	}
	
	public void setY(int y) {
		this.y = y;
	}

//	public void setX(int x2) {
//		// TODO Auto-generated method stub
//		
//	}
 
}

