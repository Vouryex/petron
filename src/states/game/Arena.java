package states.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import states.game.interfaces.Paintable;
import engine.utilities.Animation;

public class Arena implements Paintable {
	private int upBound;
	private int rightBound;
	private int downBound;
	private int leftBound;
	private int width;
	private int height;
	private int horizontalCenter;
	private int verticalCenter;
	private BufferedImage arenaSpritesheet;
	private BufferedImage[] arenaSprites;
	private Animation arenaAnimation;
	private VirtualBorder virtualBorder;

	public Arena() {
		initDimensions();
		initImage();
		initSprite();
		initAnimation();
		virtualBorder = new VirtualBorder(this);
	}

	private void initDimensions() {
		upBound = (Game.getFrameHeight() / 8) - 22;
		rightBound = Game.getFrameWidth();
		downBound = Game.getFrameHeight();
		leftBound = 0;
		width = rightBound - leftBound;
		height = downBound - upBound;
		horizontalCenter = (width / 2) + leftBound;
		verticalCenter = (height / 2) + upBound;
	}

	private void initImage() {
		try {
			arenaSpritesheet = ImageIO.read((getClass().getResourceAsStream(
					"/images/game/Arena Spritesheet.png")));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initSprite() {
		try {
			arenaSprites = new BufferedImage[20];
			for(int i = 0; i < 20; i++) {
				arenaSprites[i] = arenaSpritesheet.getSubimage(
						i * width, 0, Game.getFrameWidth(),
						Game.getFrameHeight());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initAnimation() {
		arenaAnimation = new Animation();
		arenaAnimation.setFrames(arenaSprites);
		arenaAnimation.setDelay(100);
	}

	public void paint(Graphics2D g2d) {
		g2d.drawImage(arenaAnimation.getImage(), 0, 0,
				Game.getFrameWidth(), Game.getFrameHeight(), null);
		virtualBorder.paint(g2d);
		arenaAnimation.update();
	}

	public int getUpBound() {
		return upBound;
	}
	
	public int getRightBound() {
		return rightBound;
	}
	
	public int getDownBound() {
		return downBound;
	}
	
	public int getLeftBound() {
		return leftBound;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getHorizontalCenter() {
		return horizontalCenter;
	}
	
	public int getVerticalCenter() {
		return verticalCenter;
	}
	
	public VirtualBorder getVirtualBorder() {
		return virtualBorder;
	}
}
