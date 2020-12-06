package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

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
	private Controller c;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage SpriteSheet = null;
	private BufferedImage background = null;
	private Boolean is_shooting = false;

	public static enum STATE{
		MENU,
		GAME
	};
	
	public static STATE state  = STATE.MENU;
	private Menu menu;
	
//	private Textures tex;
	
	
	public void Game() {
		
	}
	
	public void init() { // order of initialized variables same as order of declared attribute variables
		// lines 35 to 45 were missing from the code and present in vid 10
		requestFocus();
		/*
		 * BufferedImageLoader loader = new BufferedImageLoader(); try { SpriteSheet =
		 * loader.loadImage("PATH/filename.extension"); Background =
		 * loader.loadImage("/background.png"); }
		 * 
		 * 
		 * catch(IOException e) { e.printStackTrace(); }
		 */
		
		
//		 try {
//		 spriteSheet = loader.loadImage("/sprite_sheet.png");
//		 background = loader.loadImage("/background.png");
//		 } catch(IOException e) {
//		 e.printStackTrace();
//		 }
		
		
		
		menu = new Menu();
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		//tex = new Textures(this);
		
		// S is the scaling factor for the map.

		
//		p1 = new Player(200, 200, tex);
		addKeyListener(new KeyInput(this));
		p = new Player(200, 200, this);
		c = new Controller(this);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		
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
				tick();
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
		if(state == STATE.GAME) {
		p.tick();
		c.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.red);
		g.fillRect(0,0,800,0);
		
		g.drawImage(background,0,0,null);
		
		if(state == STATE.GAME) {
		p.render(g);
		c.render(g);
		}else if(state == STATE.MENU) {
			menu.render(g);
		}
		//////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(state == STATE.GAME) {
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(1);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(-1);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(1);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(-1);
//		} else if(key == KeyEvent.VK_SPACE) {
//			//p.setshooting(true);
//			c.addBullet(new Bullet(p.getX(), p.getY(), this));
		} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
			is_shooting = true;
			c.addEntity(new Bullet(p.getX(),p.getY(),this));
		}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		}	else if( key == KeyEvent.VK_LEFT) { // shouldnt be set to zero - he fixes "later"
			p.setVelX(0);
		}	else if( key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}	else if( key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} 	else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	
	}
	
	public void mousePressed(MouseEvent e) {
		int mx =e.getX();
		int my = e.getY();	

		if(mx >= Game.WIDTH / 2 + 120 && my <= Game.width / 2 + 220) {
			if((my >= 152 &&  my <= 193) && Game.state.equals(Game.STATE.MENU)) {
				Game.state = Game.STATE.GAME;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	    
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
