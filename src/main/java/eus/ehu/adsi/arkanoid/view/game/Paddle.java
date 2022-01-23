package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Graphics;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;


public class Paddle extends Rectangle {

	double velocity = 0.0;

	public Paddle(double x, double y) {
		this.x = x;
		this.y = y;
		this.sizeX = Config.PADDLE_WIDTH;
		this.sizeY = Config.PADDLE_HEIGHT;
	}

	public void update(double sizeX) {
		x += velocity * Config.FT_STEP;
		if (sizeX != 0) {
			this.sizeX = sizeX;
		}
	}

	public void stopMove() {
		velocity = 0.0;
	}

	public void moveLeft() {
		if (left() > 0.0) {
			velocity = -Config.PADDLE_VELOCITY;
		} else {
			velocity = 0.0;
		}
	}

	public void moveRight() {
		if (right() < Config.SCREEN_WIDTH) {
			velocity = Config.PADDLE_VELOCITY;
		} else {
			velocity = 0.0;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Config.getPaddleColor());
		g.fillRect((int) (left()), (int) (top()), (int) sizeX, (int) sizeY);
	}

}
