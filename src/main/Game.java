package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int width = 320;
	public static final int height = width / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Adventure Game";
	
	
	private boolean running = false;
	private Thread thread;
	
	private Player p;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage SpriteSheet = null;
	
	public void Game() {
		
	}
	
	public void init() { // order of initialized variables same as order of declared attribute variables
		System.out.println("init is being called");
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			SpriteSheet = loader.loadImage("/player.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p = new Player(200, 200, this);
		
		
	}
	
	
	private synchronized void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) {
			return;
		}
		
		running = false;
		
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	@Override
	public void run() { // "solid game loop to use in any jave game"
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // variable that calculates the time past to catch up
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				updates++;
				delta --;
				
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) { // to stop it from looping again
				timer+=1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		p.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		p.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[]args) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(width * SCALE, height * SCALE));
		game.setMaximumSize(new Dimension(width * SCALE, height * SCALE));
		game.setMinimumSize(new Dimension(width * SCALE, height * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return SpriteSheet;
	}
   
	
}
