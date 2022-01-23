package eus.ehu.adsi.arkanoid.view.game.core;

import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Arkanoid;
import eus.ehu.adsi.arkanoid.view.game.Ball;
import eus.ehu.adsi.arkanoid.view.game.Brick;
import eus.ehu.adsi.arkanoid.view.game.Config;
import eus.ehu.adsi.arkanoid.view.game.GameObject;
import eus.ehu.adsi.arkanoid.view.game.Paddle;
import eus.ehu.adsi.arkanoid.view.game.ScoreBoard;


public class Game {
	private boolean running;
	private boolean tryAgain;

	public Game () {
		this.setRunning(false);
		this.setTryAgain(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isTryAgain() {
		return tryAgain;
	}

	public void setTryAgain(boolean tryAgain) {
		this.tryAgain = tryAgain;
	}

	public static void testCollision(Paddle mPaddle, Ball mBall, int nivel) {
		if (!Game.isIntersecting(mPaddle, mBall))
			return;
		mBall.velocityY = -Config.getBallVelocity(nivel);
		if (mBall.x < mPaddle.x)
			mBall.velocityX = -Config.getBallVelocity(nivel);
		else
			mBall.velocityX = Config.getBallVelocity(nivel);
	}

	public static void testCollision(Brick mBrick, Ball mBall, ScoreBoard scoreboard, int nivel, Arkanoid a, JSONObject pr, String pNombre) {
		if (!Game.isIntersecting(mBrick, mBall))
			return;

		mBrick.destroyed = true;

		String descrip = null;

		if (mBrick.getSuerte()) {
			JSONObject j = null;
			if (pr == null) j = ArkanoidFrontera.getArkanoidFrontera().darVentaja(pNombre); //TO DO: Gestión de usuarios
			else j = pr;
			descrip = j.getString("descrip");

			if (!j.isNull("vidas")) {
				int vidas = j.getInt("vidas");
				scoreboard.updateLives(vidas);
			} else if (!j.isNull("bola")) {
				a.duplicarBola();
			} else if (!j.isNull("paddle")) {
				a.aumentarPaddle(100.0);
			} else if (!j.isNull("ladrillos")) {
				int ladrillos = j.getInt("ladrillos");
				a.eliminarLadrillos(ladrillos, mBrick);
			}
		}

		scoreboard.increaseScore(nivel, descrip);

		double overlapLeft = mBall.right() - mBrick.left();
		double overlapRight = mBrick.right() - mBall.left();
		double overlapTop = mBall.bottom() - mBrick.top();
		double overlapBottom = mBrick.bottom() - mBall.top();

		boolean ballFromLeft = overlapLeft < overlapRight;
		boolean ballFromTop = overlapTop < overlapBottom;

		double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
		double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

		if (minOverlapX < minOverlapY) {
			mBall.velocityX = ballFromLeft ? -Config.getBallVelocity(nivel) : Config.getBallVelocity(nivel);
		} else {
			mBall.velocityY = ballFromTop ? -Config.getBallVelocity(nivel) : Config.getBallVelocity(nivel);
		}
	}

	public static boolean isIntersecting(GameObject mA, GameObject mB) {
		return mA.right() >= mB.left() && mA.left() <= mB.right()
				&& mA.bottom() >= mB.top() && mA.top() <= mB.bottom();
	}

	public static List<Brick> initializeBricks(List<Brick> bricks, int nivel, int pr) {
		double ladrillosD = Config.getCountBlocksX(nivel)*Config.getCountBlocksY(nivel);
		int ladrillos = (int)ladrillosD;
		int aparece = 0;
		if (pr == 1) {
			aparece = 0;
		} else if (pr == 2) {
			aparece = 1;
		} else {
			aparece = ArkanoidFrontera.getArkanoidFrontera().generarNumeroAleatorio(4,0);
		}
		int cant = ArkanoidFrontera.getArkanoidFrontera().generarNumeroAleatorio(ladrillos,1)-1;

		if (aparece == 0) cant = -1;
		boolean suerte = false;
		bricks.clear();
		int i = 0;
		for (int iX = 0; iX < Config.getCountBlocksX(nivel); ++iX) {
			for (int iY = 0; iY < Config.getCountBlocksY(nivel); ++iY) {
				if (cant == 0) {
					suerte = true;
				}
				bricks.add(new Brick(
						(iX + 1) * (Config.BLOCK_WIDTH + 3) + 22,
						(iY + 2) * (Config.BLOCK_HEIGHT + 3) + 50,
						suerte, i)
				);
				i++;
				cant--;
				suerte = false;
			}
		}
		return bricks;
	}

}


