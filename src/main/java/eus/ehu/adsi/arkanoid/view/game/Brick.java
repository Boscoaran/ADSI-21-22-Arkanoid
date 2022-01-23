package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Graphics;


public class Brick extends Rectangle {

	public boolean destroyed = false;
	private boolean suerte;
	private int id;

	public Brick(double x, double y, boolean pSuerte, int pId) {
		this.x = x;
		this.y = y;
		this.sizeX = Config.BLOCK_WIDTH;
		this.sizeY = Config.BLOCK_HEIGHT;
		suerte = pSuerte;
		id = pId;
	}

	public void draw(Graphics g) {
		if (suerte) g.setColor(Config.BRICK_COLOR_SUERTE);
		else g.setColor(Config.getLadrilloColor());
		g.fillRect((int) left(), (int) top(), (int) sizeX, (int) sizeY);
	}

	public boolean getSuerte() {
		return suerte;
	}

	public double getX () {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getId() {
		return id;
	}
}