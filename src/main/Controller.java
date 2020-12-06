package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	EntityA enta;
	EntityB entb;
	Entity ent;
	Random r = new Random();
	/*private Textures tex*/
	
	Bullet TempBullet;
	
	Enemy TempEnemy;
	
	Game game;
	
	public Controller(Game game) {
		this.game = game;

		addEnemy(new Enemy(r.nextInt(Game.width * Game.SCALE),0));
	}
	
//	public void createEnemy(int enemy_count) {
//		for(int i = 0; i < enemy_count; i++) {
//			addEntity(new Enemy(r.nextInt(640), -10, tex));
//		}
//	}
	
//	Public Controller(/*Textures tex*/){
//		/*This.tex = tex;*/
//		for (int i =0; i<20; i++)
//	addEntity (new Enemy (r.nextInt(640), 10, /*tex*/));
//	}
	
	public void tick() {
		// A CLASS
	for(int i = 0; i < ea.size(); i++) {
		enta = ea.get(i);
	
		enta.tick(); 
	}
	// B CLASS
	for(int i = 0; i < eb.size(); i++) {
		entb = eb.get(i);
		entb.tick();
	
			//entb.tick();
	}

	
//	public void tick() {
//		for(int i = 0; i < ea.size(); i++) {
//			ent =ea.get(i);
//			ent.tick();
//		}
//			TempBullet = b.get(i);
//
//			if(TempBullet.getX() > (Game.width * Game.SCALE)) {
//				removeBullet(TempBullet);
//			}
//
//			TempBullet.tick(); 
//		}
//		for(int i = 0; i < e.size(); i++){
//			TempEnemy = e.get(i);
//			
//			// this is only the x bound
//			if(TempEnemy.getX() > (Game.width * Game.SCALE)) { 
//				TempEnemy.setX(0);
//			}
//			
//			TempEnemy.tick();
//			}
		}
	
	public void render(Graphics g) {
		// A CLASS
		for (int i=0; i<ea.size();i++){
			enta = ea.get(i);
			
			enta.render(g);
			}
		
		// B CLASS
		for (int i=0; i<eb.size();i++){
			entb = eb.get(i);
			
			entb.render(g);
			}

		
//		for(int i = 0; i < b.size(); i++) {
//			TempBullet = b.get(i);
//			
//			TempBullet.render(g);
//
//		}
//		
//		for(int i = 0; i < e.size(); i++){
//			TempEnemy = e.get(i);
//			TempEnemy.render(g);
//			}
		
	}
	
	public void addEntity(EntityA block){

		ea.add(block);
	}

	public void removeEntity(EntityA block){

		ea.remove(block);
	}
	
	public void addEntity(EntityB block){

		eb.add(block);
	}

	public void removeEntity(EntityB block){

		eb.remove(block);
	}

	
	public void addEnemy(Enemy block) {
		eb.add(block);
	}
	
	public void removeEnemy(Enemy block) {
		eb.remove(block);
	}
	
	public void addBullet(Bullet block) {
		ea.add(block);
	}
	
	public void removeBullet(Bullet block) {
		ea.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
}
