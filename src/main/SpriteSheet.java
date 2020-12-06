package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;
    
    /*
     * public SpriteSheet(BufferedImage) {
     * this.image = image;
     */

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(String.format("resource/%s.png", file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("player");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
