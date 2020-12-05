package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage image;
	//private BufferedImage image;
	private BufferedImage[] idleSprite;

	public SpriteSheet(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}


}
