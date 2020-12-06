package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {
	
	private Game game;
	
	private BufferedImage[] fireballRight = {SpriteSheet.getSprite(0, 1), SpriteSheet.getSprite(1, 1), SpriteSheet.getSprite(2,1), SpriteSheet.getSprite(3, 1)};

	private Animation fireballTravelingState = new Animation(fireballRight, 5, true);

	private Animation animation = fireballTravelingState;
	
	
	public Bullet(double x, double y, Game game) {
		super(x ,y);
		this.game = game;
		animation.start();
	}
	
	public void tick() {
		x += 5;
		animation.update();
		
		if(Physics.Collision(this, game.eb)) {
			System.out.println("Collision Detected");
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), (int) x, (int) y, null);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32); 
	}
	
	public double getX() {
		return x;
	}
	
	public double gety() {
		return y;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}


}
