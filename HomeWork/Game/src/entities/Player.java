package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.PLayerConstants.GetSpriteAmount;
import static utilz.Constants.PLayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{	

	private BufferedImage[][] animation;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private boolean left,up,right,down;
	private boolean moving = false, attacking = false;
	private float playerSpeed = 2.0f;

	public Player(float x, float y) {
		super(x, y);
		loadAnimation();

	}

	public void update() {
		
		updatePos();
		updateAnimation();
		setAnimation();

	}

	public void render(Graphics g) {
		g.drawImage(animation[playerAction][aniIndex], (int) x, (int) y, 256, 160, null);
	}
	
	private void updateAnimation() {

		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}
	}
	
	private void setAnimation() {
		int startAni = playerAction;
		
		if(moving)
			playerAction = RUNNIG;
		else
			playerAction = IDLE;
		
		if(attacking)
			playerAction = ATTACK_1;
		
		if(startAni != playerAction)
			resetAniTick();
		
	}
	

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		
		moving = false;

		if(left && !right) {
			x-=playerSpeed;
			moving = true;
		} else if(right && !left){
			x+= playerSpeed;
			moving = true;
		}
		
		if(up && !down) {
			y-=playerSpeed;
			moving = true;
		} else if(down && !up){
			y+= playerSpeed;
			moving = true;
		}
	}

	private void loadAnimation() {

		InputStream is = getClass().getResourceAsStream("/player_sprites.png");

		try {
			BufferedImage img = ImageIO.read(is);

			animation = new BufferedImage[9][6];

			for(int j = 0; j < animation.length; j++) {
				for(int i = 0; i < animation[j].length; i++) {
					animation[j][i] = img.getSubimage(i*64, j*40, 64, 40);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	
}
